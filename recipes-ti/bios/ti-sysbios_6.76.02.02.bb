require recipes-ti/bios/ti-sysbios.inc

PV = "6_76_02_02"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=5cf40699c51b26d36fc01647c0b27fb1"

SRC_URI[sysbiosbin.md5sum] = "7ae35f34179492913e40f02ec5ffb7dd"
SRC_URI[sysbiosbin.sha256sum] = "029981f79ce61fe28241944f4f60c37b2c4b8c83fe1ee38ed400d58412b4fdb6"

TI_BIN_UNPK_CMDS = ""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"

BINFILE = "bios_${PV}.run"
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/bios/sysbios/${PV}/exports/${BINFILE};name=sysbiosbin"
