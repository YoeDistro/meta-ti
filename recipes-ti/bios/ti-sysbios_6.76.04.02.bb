require recipes-ti/bios/ti-sysbios.inc

PV = "6_76_04_02"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=5cf40699c51b26d36fc01647c0b27fb1"

SRC_URI[sysbiosbin.md5sum] = "9766b12ea6dd4b3a035cdb6be54c5071"
SRC_URI[sysbiosbin.sha256sum] = "d68ca3ba862f8f83a5bf7084af326ffa9fcb64e39c866f98384d87c0ceca29be"

TI_BIN_UNPK_CMDS = ""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"

BINFILE = "bios_${PV}.run"
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/bios/sysbios/${PV}/exports/${BINFILE};name=sysbiosbin"
