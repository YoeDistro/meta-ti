SUMMARY = "TI RTOS driver for Power Management module (PM)"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://Power.h;beginline=1;endline=31;md5=527b91fdcd26cd19ac07a754f45dedbe"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"


PM_LLD_GIT_URI = "git://git.ti.com/git/keystone-rtos/pm-lld.git"
PM_LLD_GIT_PROTOCOL = "https"

PM_LLD_GIT_BRANCH = "master"

PM_LLD_GIT_BRANCH_ti33x = "int_pm_am335x"
PM_LLD_GIT_BRANCH_ti43x = "int_pm_am335x"
PM_LLD_GIT_BRANCH_k2g = "int_pm_am335x"

PM_LLD_GIT_DESTSUFFIX = "git/ti/drv/pm"

# Below commit ID corresponds to "DEV.PM_LLD.01.08.00.23A"
PM_LLD_SRCREV = "59abc6ffb890d24b82de0248d204ad10c1bfc2af"

# Below commit ID corresponds to "DEV.PM_LLD.01.04.00.05C"
PM_LLD_SRCREV_ti33x = "dd1f8486e7738956631b7f4829c8238be2e49dd6"
PM_LLD_SRCREV_ti43x = "dd1f8486e7738956631b7f4829c8238be2e49dd6"
PM_LLD_SRCREV_k2g = "dd1f8486e7738956631b7f4829c8238be2e49dd6"

BRANCH = "${PM_LLD_GIT_BRANCH}"
SRC_URI = "${PM_LLD_GIT_URI};destsuffix=${PM_LLD_GIT_DESTSUFFIX};protocol=${PM_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${PM_LLD_SRCREV}"
PV = "01.08.00.23A"
PR = "r0"

S = "${WORKDIR}/${PM_LLD_GIT_DESTSUFFIX}"

DEPENDS_append = " ti-sysbios \
            osal-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"

DEPENDS_append_k3 = " sciclient-rtos \
"

export PDK_PM_ROOT_PATH = "${WORKDIR}/build"
export DEST_ROOT="${S}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

do_compile_append() {
    # Delete archive created by XDC release command since it does not contain all content
    find -name "*.tar" -exec rm -f {} \;

    # Archive from build/ to capture ti/drv/pm/ in archive
    cd ${B}
    tar -cf pm_lld.tar --exclude='*.tar' ./*
}


# Workaround: dra7xx build requires am57xx pm libraries for opencl-monitor
TI_PDK_LIMIT_SOCS_append_dra7xx = " am571x am572x am574x"
TI_PDK_LIMIT_BOARDS_append_dra7xx = " evmAM571x evmAM572x idkAM574x"

INSANE_SKIP_${PN} = "arch staticdev"
