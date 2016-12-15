require recipes-ti/bios/ti-sysbios.inc

PV = "6_46_01_38"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=da86dca468e5dd80484dc83473ea32e3"

SRC_URI[sysbiosbin.md5sum] = "5aee070f73eb6f3d9aab2ff9c483f396"
SRC_URI[sysbiosbin.sha256sum] = "3a04a584c3ee3221997b7d616ba45bbcc21ad241f4a4ab5c0f28c465d538af90"

TI_BIN_UNPK_CMDS=""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"
