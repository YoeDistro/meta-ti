require recipes-ti/bios/ti-sysbios.inc

PV = "6_82_01_19"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=4b72066ed4cb1dd73ff2f585c428105a"

SRC_URI[sysbiosbin.sha256sum] = "4d7c6cac993e6bc601083a41a5c52089d4852544b4c38563e4320310a54882b2"

TI_BIN_UNPK_CMDS = ""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"

BINFILE = "bios_${PV}.run"
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/bios/sysbios/${PV}/exports/${BINFILE};name=sysbiosbin"
