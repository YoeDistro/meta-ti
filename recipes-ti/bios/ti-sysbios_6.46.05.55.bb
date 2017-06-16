require recipes-ti/bios/ti-sysbios.inc

PV = "6_46_05_55"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=1c88b63f0ce751d88354de0727623eba"

SRC_URI[sysbiosbin.md5sum] = "8182effcbeea90778e393818ec4d65e8"
SRC_URI[sysbiosbin.sha256sum] = "2ad3432cafd1a93dd8cd1e56b722c979852d5c72f5defc29843d507cdd7b667a"

TI_BIN_UNPK_CMDS=""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"
