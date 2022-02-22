require recipes-ti/bios/ti-sysbios.inc

PE = "1"
PV = "6_76_03_01"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=5cf40699c51b26d36fc01647c0b27fb1"

SRC_URI[sysbiosbin.md5sum] = "647243209d66d5dd35503b0ec5a84ab9"
SRC_URI[sysbiosbin.sha256sum] = "b762b7f2343d26d367f952488e529046785128005158cc921c3baef08e97116a"

TI_BIN_UNPK_CMDS = ""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"

BINFILE = "bios_${PV}.run"
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/bios/sysbios/${PV}/exports/${BINFILE};name=sysbiosbin"
