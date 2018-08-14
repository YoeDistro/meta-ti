require recipes-ti/bios/ti-sysbios.inc

PV = "6_73_00_12"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=99771475f3621a6bf690df10327eaaa9"

SRC_URI[sysbiosbin.md5sum] = "f8ff74bf0cfebdd5689fba843272ac61"
SRC_URI[sysbiosbin.sha256sum] = "4fdf76696fb43984a9cae739fd2ee091ca9d031ad0277d2bb94dbb26eae63f43"

TI_BIN_UNPK_CMDS = ""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"

BINFILE = "bios_${PV}.run"
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/bios/sysbios/${PV}/exports/${BINFILE};name=sysbiosbin"
