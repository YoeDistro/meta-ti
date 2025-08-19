SUMMARY = "TI MessageQ prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am57xx"

inherit update-alternatives
inherit deploy

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${TI_IPC_EXAMPLES_FW_VERSION}"
PR = "${INC_PR}.0"

INSTALL_IPC_FW_DIR = "${nonarch_base_libdir}/firmware/ipc"

IPU_1_FW = "messageq_server_ipu1.xem4"
IPU_2_FW = "messageq_server_ipu2.xem4"
DSP_1_FW = "messageq_server_dsp1.xe66"
DSP_2_FW = "messageq_server_dsp2.xe66"

IPU_1_FW_DIR = "ti_platforms_evmDRA7XX_ipu1"
IPU_2_FW_DIR = "ti_platforms_evmDRA7XX_ipu2"
DSP_1_FW_DIR = "ti_platforms_evmDRA7XX_dsp1"
DSP_2_FW_DIR = "ti_platforms_evmDRA7XX_dsp2"

do_install() {
    # Install Firmware
    install -d ${D}${INSTALL_IPC_FW_DIR}/${IPU_1_FW_DIR}
    install -d ${D}${INSTALL_IPC_FW_DIR}/${IPU_2_FW_DIR}
    install -d ${D}${INSTALL_IPC_FW_DIR}/${DSP_1_FW_DIR}
    install -d ${D}${INSTALL_IPC_FW_DIR}/${DSP_2_FW_DIR}

    install -m 0644 ${S}/ti-ipc/am57xx/${IPU_1_FW} ${D}${INSTALL_IPC_FW_DIR}/${IPU_1_FW_DIR}
    install -m 0644 ${S}/ti-ipc/am57xx/${IPU_2_FW} ${D}${INSTALL_IPC_FW_DIR}/${IPU_2_FW_DIR}
    install -m 0644 ${S}/ti-ipc/am57xx/${DSP_1_FW} ${D}${INSTALL_IPC_FW_DIR}/${DSP_1_FW_DIR}
    install -m 0644 ${S}/ti-ipc/am57xx/${DSP_2_FW} ${D}${INSTALL_IPC_FW_DIR}/${DSP_2_FW_DIR}
}

do_deploy() {
    install -d ${DEPLOYDIR}/ipc
    install -m 0644 ${S}/ti-ipc/am57xx/${IPU_1_FW} ${DEPLOYDIR}/ipc/dra7-ipu1-fw.xem4
}

addtask deploy after do_install

# Set up names for the firmwares

ALTERNATIVE:${PN} = "\
                    dra7-ipu1-fw.xem4 \
                    dra7-ipu2-fw.xem4 \
                    dra7-dsp1-fw.xe66 \
                    dra7-dsp2-fw.xe66 \
                    "

ALTERNATIVE_LINK_NAME[dra7-ipu1-fw.xem4] = "${nonarch_base_libdir}/firmware/dra7-ipu1-fw.xem4"
ALTERNATIVE_LINK_NAME[dra7-ipu2-fw.xem4] = "${nonarch_base_libdir}/firmware/dra7-ipu2-fw.xem4"
ALTERNATIVE_LINK_NAME[dra7-dsp1-fw.xe66] = "${nonarch_base_libdir}/firmware/dra7-dsp1-fw.xe66"
ALTERNATIVE_LINK_NAME[dra7-dsp2-fw.xe66] = "${nonarch_base_libdir}/firmware/dra7-dsp2-fw.xe66"

ALTERNATIVE_TARGET[dra7-ipu1-fw.xem4] = "${INSTALL_IPC_FW_DIR}/${IPU_1_FW_DIR}/${IPU_1_FW}"
ALTERNATIVE_TARGET[dra7-ipu2-fw.xem4] = "${INSTALL_IPC_FW_DIR}/${IPU_2_FW_DIR}/${IPU_2_FW}"
ALTERNATIVE_TARGET[dra7-dsp1-fw.xe66] = "${INSTALL_IPC_FW_DIR}/${DSP_1_FW_DIR}/${DSP_1_FW}"
ALTERNATIVE_TARGET[dra7-dsp2-fw.xe66] = "${INSTALL_IPC_FW_DIR}/${DSP_2_FW_DIR}/${DSP_2_FW}"

ALTERNATIVE_PRIORITY = "30"
