require recipes-ti/bios/ti-sysbios.inc

PV = "6_73_01_01"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=99771475f3621a6bf690df10327eaaa9"

SRC_URI[sysbiosbin.md5sum] = "899ddf89d259e2848b93d6d53902a2d5"
SRC_URI[sysbiosbin.sha256sum] = "005b63d59308e0fe6e8fc4ff9d760c829880fb3e0daf43eb8181680ddf11ac85"

TI_BIN_UNPK_CMDS = ""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"

BINFILE = "bios_${PV}.run"
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/bios/sysbios/${PV}/exports/${BINFILE};name=sysbiosbin"
