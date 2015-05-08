require ti-sysbios.inc

PV = "6_41_02_41"
PR = "r0"

LIC_FILES_CHKSUM = "file://bios_${PV}_manifest.html;md5=a4af75ec6d586c5267c71075eebff418"

SRC_URI[sysbiosbin.md5sum] = "65deff27110c2af87a3c71fea3c0345e"
SRC_URI[sysbiosbin.sha256sum] = "2c47b4a3aa9513d10e5811a8ac7ed1d6ce3d13df57a0bffe10d8cabaf228c76f"

TI_BIN_UNPK_CMDS=""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"

INSANE_SKIP_${PN} = "installed-vs-shipped"
