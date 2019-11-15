DESCRIPTION = "TI Resource Manager Low Level Driver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/rm/COPYING.txt;md5=dc61631b65360e6beb73b6c337800afc"

inherit ti-pdk-fetch

TI_PDK_COMP = "ti.drv.rm"

PE = "1"

INC_PR = "r0"

COMPATIBLE_MACHINE = "keystone"

BASEDIR = "${WORKDIR}/git"
