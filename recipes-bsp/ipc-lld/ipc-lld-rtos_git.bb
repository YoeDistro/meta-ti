SUMMARY = "TI RTOS low level driver for IPC"

require ipc-lld.inc

DEPENDS += " osal-rtos common-csl-ip-rtos sciclient-rtos"

do_configure() {
    # remove any previous package
    rm -rf ${IPCLLD_PACKAGE_BASE}
    cd ${S}

    mkdir -p ${IPCLLD_ROOTPATH}

    for board in ${TI_PDK_LIMIT_BOARDS}
    do
        # make the release package before building it
        oe_runmake package BOARD=$board DEST_ROOT=${IPCLLD_PACKAGE_BASE} PDK_IPC_COMP_PATH=${S}
    done

}

do_compile() {
    cd ${IPCLLD_ROOTPATH}

    oe_runmake lib LIMIT_BOARDS="${TI_PDK_LIMIT_BOARDS}" PDK_IPC_COMP_PATH=${IPCLLD_ROOTPATH}
}

do_install() {
    cd ${IPCLLD_ROOTPATH}

    #remove prebuilt binaries
    rm -rf examples/echo_test/binaries

    #archive
    tar -cf ipc-lld.tar --exclude='*.tar' ./*

    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/ipc
    find -name "*.tar" -exec tar xf {} --no-same-owner -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/ipc \;
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INSANE_SKIP_${PN} = "arch ldflags file-rdeps"

INSANE_SKIP_${PN}-dbg = "arch"
