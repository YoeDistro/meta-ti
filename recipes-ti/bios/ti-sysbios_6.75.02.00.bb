require recipes-ti/bios/ti-sysbios.inc

PV = "6_75_02_00"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_bios_${PV}.html;md5=5cf40699c51b26d36fc01647c0b27fb1"

SRC_URI[sysbiosbin.md5sum] = "8bca751d3054e74b9edb510a0d099095"
SRC_URI[sysbiosbin.sha256sum] = "c92cb72f86656b1e37454e238e9838f301eeeb4d711a0f4a84089ef80ccb6013"

TI_BIN_UNPK_CMDS = ""
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"

BINFILE = "bios_${PV}.run"
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/bios/sysbios/${PV}/exports/${BINFILE};name=sysbiosbin"
