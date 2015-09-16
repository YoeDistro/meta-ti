require ti-sysbios.inc

PV = "6_42_02_29"
PR = "r0"

LIC_FILES_CHKSUM = "file://bios_${PV}_manifest.html;md5=ab905941085d55ea207fcb4cdab4f9c3"

SRC_URI[sysbiosbin.md5sum] = "ba470b69d1ffa4a34fd05645f240216c"
SRC_URI[sysbiosbin.sha256sum] = "eb070642e8ee8e742525599f53ef1547d6f5dded557987550c710ad193d0de31"

TI_BIN_UNPK_CMDS=""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"
