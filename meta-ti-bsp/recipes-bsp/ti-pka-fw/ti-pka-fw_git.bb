SUMMARY = "PKA prebuilt binary firmware image"

LICENSE = "TI-TFL & INSIDE-Secure"
LIC_FILES_CHKSUM = "file://LICENCE.pka_fw;md5=dc20391b287874f0dce069cf87917206"

COMPATIBLE_MACHINE = "k3"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${TI_PKA_FW_VERSION}"
PR = "${INC_PR}.0"

PKA_FW_DIR = "ti-pka"

INSTALL_PKA_FW_DIR = "${nonarch_base_libdir}/firmware"

PKA_FW_NAME = "eip29t2_2.1.0.bin"

do_install() {
    # Install Firmware
    install -d ${D}${INSTALL_PKA_FW_DIR}
    install -m 0644 ${S}/${PKA_FW_DIR}/${PKA_FW_NAME} ${D}/${INSTALL_PKA_FW_DIR}/${PKA_FW_NAME}
}
