SUMMARY = "echo_test for IPC-LLD"

require ipc-lld.inc

DEPENDS += " ipc-lld-rtos common-csl-ip-rtos sciclient-rtos board-rtos"

REMOTE_FW_DIR = "${S}/examples/echo_test/yocto_bin"
REMOTE_FW_BIN_DIR = "${REMOTE_FW_DIR}/ipc_echo_test/bin"

DST_BIN_PATH = "${base_libdir}/firmware/pdk-ipc/"

do_configure[noexec] = "1"

do_compile() {
    cd ${S}

    for board in ${TI_PDK_LIMIT_BOARDS}
    do
        for core in ${TI_PDK_LIMIT_CORES}
        do
            oe_runmake examples BOARD="$board" CORE="$core" DEST_ROOT=${REMOTE_FW_DIR}
        done
    done
}

do_install() {
    install -d ${D}${DST_BIN_PATH}

    for board in ${TI_PDK_LIMIT_BOARDS}
    do
        for core in ${TI_PDK_LIMIT_CORES}
        do
            install -m 0644 ${REMOTE_FW_BIN_DIR}/$board/ipc_echo_test_${core}_release.x* ${D}${DST_BIN_PATH}

            #removing map files copied in previous line
            rm ${D}${DST_BIN_PATH}/*.map
        done
    done
}

# make sure that lib/firmware, and all its contents are part of the package
FILES_${PN} += "${base_libdir}/firmware"
FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INSANE_SKIP_${PN} = "arch ldflags file-rdeps"

INSANE_SKIP_${PN}-dbg = "arch"
