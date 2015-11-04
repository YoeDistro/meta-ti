# This .inc file allows building and deploying multiple sets of kernel + modules
# with different defconfigs
#
# Note that this include will NOT stage anything nor create packages, since that
# is virtually impossible. Userspace should be built against the 'regular' kernel
#
# The intended usecase is for machines that have mutually exclusive drivers due
# to e.g. pinmuxing issues. For example the LogicPD omap-l138 experimenter board
# can have multiple mutually exclusive expansion boards like lcd, ethernet,
# sound, 20x2 character LCD, etc. To be able to easily test all of those you can
# use this .inc
#
# The function get_kernelversion uses generated header files to extract the
# version number. But those files are not available until do_compile, and
# do_compileconfigs is injected before that, hence the version becomes None
# and breaks in several places. Introduce a task do_preparekernel that calls
# "make prepare" in the kernel tree to generate all the necessary files.

inherit deploy

SRC_URI += " \
           file://configs "

def get_git_revision(p):
    import subprocess

    try:
        return subprocess.Popen("git rev-parse HEAD 2>/dev/null ", cwd=p, shell=True, stdout=subprocess.PIPE).communicate()[0].rstrip()
    except OSError:
        return None

KERNEL_LOCALVERSION = "-g${@get_git_revision('${S}').__str__()[:10]}"

MULTI_CONFIG_BASE_SUFFIX = "multi-config-"

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${KERNEL_VERSION}-${PKGR}-${MACHINE}-${DATETIME}"
MODULE_IMAGE_BASE_NAME = "modules-${KERNEL_VERSION}-${PKGR}-${MACHINE}-${DATETIME}"
CONFIG_IMAGE_BASE_NAME = "config-${KERNEL_VERSION}-${PKGR}-${MACHINE}-${DATETIME}"

EXTRA_OEMAKE = "${PARALLEL_MAKE} "

do_preparekernel () {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
    oe_runmake prepare CC="${KERNEL_CC}" LD="${KERNEL_LD}"
}

addtask preparekernel after do_configure before do_compile

do_compileconfigs () {
    # fixup for fetcher change
    if [ -e ${WORKDIR}/configs/configs ] ; then
        if [ -e ${WORKDIR}/configs/configs/empty ] ; then
            mv ${WORKDIR}/configs/configs/empty ${WORKDIR}/configs/
        fi
        mv ${WORKDIR}/configs/configs/* ${WORKDIR}/configs/
        rm -rf ${WORKDIR}/configs/configs
    fi

    # Compile and Install additional kernel configs if found
    if [ -e ${WORKDIR}/configs/empty ] ; then
        echo "No configs found in configs/ directory, skipping to regular build"
    else
        echo "Multiple configs found, building those first"

        # Context Save the regular 'defconfig'
        cp ${WORKDIR}/defconfig ${WORKDIR}/defconfig.save

        for configname in ${WORKDIR}/configs/* ; do
            # Copy in alternative config
            cd ${B}
            cp $configname ${WORKDIR}/defconfig

            # Build and Install this alternative kernel
            do_configure
            echo ${KERNEL_LOCALVERSION}-$(basename $configname) > ${B}/.scmversion
            echo ${KERNEL_LOCALVERSION}-$(basename $configname) > ${S}/.scmversion
            do_compile
            do_compile_kernelmodules
            # Below lines are needed to satisfy do_install and it's cleanup code, which doesn't know about configname
            install -d "${D}/lib/modules/${KERNEL_VERSION}"
            touch "${D}/lib/modules/${KERNEL_VERSION}/build"
            touch "${D}/lib/modules/${KERNEL_VERSION}/source"
            install -d "${D}/lib/modules/${KERNEL_VERSION}/kernel"
            do_install

            # Remove unused symlinks specific to configname
            rm "${D}/lib/modules/$(cut -d\" -f2 ${B}/include/generated/utsrelease.h)/build"
            rm "${D}/lib/modules/$(cut -d\" -f2 ${B}/include/generated/utsrelease.h)/source"
            # In case no modules were installed, remove empty dirs
            rmdir --ignore-fail-on-non-empty "${D}/lib/modules/$(cut -d\" -f2 ${B}/include/generated/utsrelease.h)/kernel"
            rmdir --ignore-fail-on-non-empty "${D}/lib/modules/$(cut -d\" -f2 ${B}/include/generated/utsrelease.h)"
            # Also remove default location for modules used by do_install if empty
            rmdir --ignore-fail-on-non-empty "${D}/lib/modules/${KERNEL_VERSION}"

            # Drop the resulting images in the deploy dir
            install -d ${DEPLOYDIR}
            install -m 0644 ${KERNEL_OUTPUT} ${DEPLOYDIR}/${KERNEL_IMAGE_BASE_NAME}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).bin

            if [ -d "${D}/lib" ]; then
                tar --owner=root --group=root -cvzf ${DEPLOYDIR}/${MODULE_IMAGE_BASE_NAME}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).tgz -C ${D} lib
            fi

            # Install the final config alongside the images
            cp .config ${DEPLOYDIR}/${CONFIG_IMAGE_BASE_NAME}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).config

            # Create symlinks
            cd ${DEPLOYDIR}
            rm -f ${KERNEL_IMAGE_SYMLINK_NAME}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).bin
            ln -sf ${KERNEL_IMAGE_BASE_NAME}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).bin ${KERNEL_IMAGE_SYMLINK_NAME}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).bin
            rm -f modules-${MACHINE}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).tgz
            ln -sf ${MODULE_IMAGE_BASE_NAME}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).tgz modules-${MACHINE}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).tgz
            rm -f config-${MACHINE}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).config
            ln -sf ${CONFIG_IMAGE_BASE_NAME}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).config config-${MACHINE}.${MULTI_CONFIG_BASE_SUFFIX}$(basename $configname).config
        done

        # Restore the regular 'defconfig'
        cp ${WORKDIR}/defconfig.save ${WORKDIR}/defconfig
        cd ${B}
        do_configure
    fi
}

# For reference, copy .config to deploy image
do_deploy_append () {
    install -d ${DEPLOYDIR}

    # Drop the regular defconfig along side the others for consistency
    cp ${B}/.config ${DEPLOYDIR}/${CONFIG_IMAGE_BASE_NAME}.config

    # add symlink
    cd ${DEPLOYDIR}
    rm -f config-${MACHINE}.config
    ln -s ${CONFIG_IMAGE_BASE_NAME}.config config-${MACHINE}.config

    rm -f modules-${MACHINE}.tgz
    ln -sf ${MODULE_IMAGE_BASE_NAME}.tgz modules-${MACHINE}.tgz
}

do_compileconfigs[depends] += "u-boot-mkimage-native:do_populate_sysroot virtual/${TARGET_PREFIX}gcc:do_populate_sysroot"
addtask compileconfigs after do_preparekernel before do_compile
