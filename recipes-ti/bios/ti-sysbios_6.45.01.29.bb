require recipes-ti/bios/ti-sysbios.inc

PV = "6_45_01_29"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=dbcd89745f1745f24ace8171a96ed493"

SRC_URI[sysbiosbin.md5sum] = "16cf6dc3c5cc9d2b877fa7d81dd12150"
SRC_URI[sysbiosbin.sha256sum] = "efaf5f1d725f3fd06e45476109b1124c74c16c39c870d12929e071047c3e5a22"

TI_BIN_UNPK_CMDS=""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"
