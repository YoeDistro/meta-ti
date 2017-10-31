require recipes-ti/bios/ti-sysbios.inc

PV = "6_52_00_12"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=ca549c223d732e6f69efc449795c4eeb"

SRC_URI[sysbiosbin.md5sum] = "3c16af0843b5b6c5ff10512d1b586665"
SRC_URI[sysbiosbin.sha256sum] = "4a2b9b76f52a57f02725337db9361787eae4c917d7cf989a697ed41eb4a1e701"

TI_BIN_UNPK_CMDS = ""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"

BINFILE = "bios_${PV}.run"
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/bios/sysbios/${PV}/exports/${BINFILE};name=sysbiosbin"
