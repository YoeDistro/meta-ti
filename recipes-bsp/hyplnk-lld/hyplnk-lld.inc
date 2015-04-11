DESCRIPTION = "TI Hyperlink Low Level Driver"
LICENSE = "BSD-3-Clause"

COMPATIBLE_MACHINE = "keystone"

LLDNAME="hyplnk"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/${LLDNAME}/COPYING.txt;md5=623325cc19e613a4e770fbb749922592"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-rtos/hyplnk-lld.git;destsuffix=git/ti/drv/${LLDNAME};protocol=git;branch=${BRANCH}"
# Following commit corresponds to tag DEV.HYPLNK_LLD.02.01.00.02
SRCREV = "8451586025a0cf561ed9bc6ab70a2cad88a7e95e"

PV = "2.1.0.2"
PR = "r1"

DEVICELIST = " k2h \
               k2k \
               k2e \
"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/${LLDNAME}"
