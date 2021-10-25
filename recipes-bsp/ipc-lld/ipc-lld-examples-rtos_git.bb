SUMMARY = "echo_test for IPC-LLD"

require ipc-lld.inc

inherit update-alternatives

DEPENDS += " ipc-lld-rtos common-csl-ip-rtos sciclient-rtos board-rtos"

REMOTE_FW_DIR = "${S}/examples/yocto_bin"
REMOTE_FW_BIN_DIR = "${REMOTE_FW_DIR}/ipc_echo_test/bin"
REMOTE_FWB_BIN_DIR = "${REMOTE_FW_DIR}/ipc_echo_testb/bin"
RTOS_ALL_CORES_BIN_DIR = "${REMOTE_FW_DIR}/ex02_bios_multicore_echo_test/bin"
RTOS_2_CORES_BIN_DIR = "${REMOTE_FW_DIR}/ex01_bios_2core_echo_test/bin"
LINUX_2_CORES_BIN_DIR = "${REMOTE_FW_DIR}/ex03_linux_bios_2core_echo_test/bin"
LINUX_2_CORES_BAREMETAL_BIN_DIR = "${REMOTE_FW_DIR}/ex04_linux_baremetal_2core_echo_test/bin"

DST_BIN_PATH = "${base_libdir}/firmware/pdk-ipc"

TI_PDK_LIMIT_BOARDS_j7 = "j721e_evm"

do_configure[noexec] = "1"

do_compile() {
    cd "${PDK_INSTALL_PATH}/ti/build"

    for board in ${TI_PDK_LIMIT_BOARDS}
    do
        for core in ${TI_PDK_LIMIT_CORES}
        do
            oe_runmake ipc_echo_test BOARD="$board" CORE="$core" DEST_ROOT=${REMOTE_FW_DIR}
            oe_runmake ex02_bios_multicore_echo_test BOARD="$board" CORE="$core" DEST_ROOT=${REMOTE_FW_DIR}
            oe_runmake ex01_bios_2core_echo_test BOARD="$board" CORE="$core" DEST_ROOT=${REMOTE_FW_DIR}
            oe_runmake ex03_linux_bios_2core_echo_test BOARD="$board" CORE="$core" DEST_ROOT=${REMOTE_FW_DIR}
            oe_runmake ex04_linux_baremetal_2core_echo_test BOARD="$board" CORE="$core" DEST_ROOT=${REMOTE_FW_DIR}
            oe_runmake ipc_echo_testb BOARD="$board" CORE="$core" DEST_ROOT=${REMOTE_FW_DIR}
        done

    done
}

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${DST_BIN_PATH}

    cp ${CP_ARGS} ${REMOTE_FW_DIR}/ex02_bios_multicore_echo_test/bin -d ${D}/ex02_bios_multicore_echo_test
    cp ${CP_ARGS} ${REMOTE_FW_DIR}/ex01_bios_2core_echo_test/bin -d ${D}/ex01_bios_2core_echo_test
    cp ${CP_ARGS} ${REMOTE_FW_DIR}/ex03_linux_bios_2core_echo_test/bin -d ${D}/ex03_linux_bios_2core_echo_test

    for board in ${TI_PDK_LIMIT_BOARDS}
    do
        for core in ${TI_PDK_LIMIT_CORES}
        do
            install -m 0644 ${REMOTE_FW_BIN_DIR}/$board/ipc_echo_test_${core}_release* ${D}${DST_BIN_PATH}

            #removing map files copied in previous line
            rm ${D}${DST_BIN_PATH}/*.map
        done

        #remove mpu binarires
        rm ${D}${DST_BIN_PATH}/ipc_echo_test_mpu1_0_release.xa*

    done
}

do_install_append_j7() {
    #copy ipc_echo_testb binaries for J721
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu1_0_release.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu1_1_release.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu2_0_release.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu2_1_release.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu3_0_release.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu3_1_release.xer5f ${D}${DST_BIN_PATH}

    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu1_0_release_strip.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu1_1_release_strip.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu2_0_release_strip.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu2_1_release_strip.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu3_0_release_strip.xer5f ${D}${DST_BIN_PATH}
    install -m 0644 ${REMOTE_FWB_BIN_DIR}/$board/ipc_echo_testb_mcu3_1_release_strip.xer5f ${D}${DST_BIN_PATH}
}

do_install_append_am65xx() {
    cp ${CP_ARGS} ${REMOTE_FW_DIR}/ex04_linux_baremetal_2core_echo_test/bin -d ${D}/ex04_linux_baremetal_2core_echo_test
}

# Set up names for the firmwares
ALTERNATIVE_${PN}_am65xx = "\
                    am65x-mcu-r5f0_0-fw \
                    am65x-mcu-r5f0_1-fw \
                    "
ALTERNATIVE_${PN}_j7 = "\
                    j7-mcu-r5f0_0-fw \
                    j7-mcu-r5f0_1-fw \
                    j7-main-r5f0_0-fw \
                    j7-main-r5f0_1-fw \
                    j7-main-r5f1_0-fw \
                    j7-main-r5f1_1-fw \
                    j7-c66_0-fw \
                    j7-c66_1-fw \
                    j7-c71_0-fw\
                    "

# Set up link names for the firmwares

TARGET_MCU_R5FSS0_0_am65xx = "am65x-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_am65xx = "am65x-mcu-r5f0_1-fw"

TARGET_MCU_R5FSS0_0_j7 = "j7-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_j7 = "j7-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0_j7 = "j7-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1_j7 = "j7-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0_j7 = "j7-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1_j7 = "j7-main-r5f1_1-fw"
TARGET_C66_0_j7 = "j7-c66_0-fw"
TARGET_C66_1_j7 = "j7-c66_1-fw"
TARGET_C7X_0_j7 = "j7-c71_0-fw"

ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"

ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1}"
ALTERNATIVE_LINK_NAME[j7-c66_0-fw] = "${base_libdir}/firmware/${TARGET_C66_0}"
ALTERNATIVE_LINK_NAME[j7-c66_1-fw] = "${base_libdir}/firmware/${TARGET_C66_1}"
ALTERNATIVE_LINK_NAME[j7-c71_0-fw] = "${base_libdir}/firmware/${TARGET_C7X_0}"

# Create the firmware alternatives

ALTERNATIVE_TARGET[am65x-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[am65x-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"

ALTERNATIVE_TARGET[j7-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_testb_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f1_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f1_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-c66_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_1_release_strip.xe66"
ALTERNATIVE_TARGET[j7-c66_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_2_release_strip.xe66"
ALTERNATIVE_TARGET[j7-c71_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_1_release_strip.xe71"

ALTERNATIVE_PRIORITY = "10"

#add source and all rtos binaries package
PACKAGES =+ "${PN}-rtos"

# make sure that lib/firmware, and all its contents are part of the package
FILES_${PN} += "${base_libdir}/firmware"

FILES_${PN}-rtos += "ex02_bios_multicore_echo_test"
FILES_${PN}-rtos += "ex01_bios_2core_echo_test"
FILES_${PN}-rtos += "ex03_linux_bios_2core_echo_test"
FILES_${PN}-rtos += "ex04_linux_baremetal_2core_echo_test"

INSANE_SKIP_${PN} = "arch ldflags file-rdeps"
INSANE_SKIP_${PN}-rtos = "arch ldflags file-rdeps"

INSANE_SKIP_${PN}-dbg = "arch"
