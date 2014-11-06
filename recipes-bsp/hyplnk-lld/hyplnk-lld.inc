DESCRIPTION = "TI Hyperlink Low Level Driver"
LICENSE = "BSD-3-Clause"

COMPATIBLE_MACHINE = "keystone"

LLDNAME="hyplnk"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/${LLDNAME}/COPYING.txt;md5=623325cc19e613a4e770fbb749922592"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-rtos/hyplnk-lld.git;destsuffix=git/ti/drv/${LLDNAME};protocol=git;branch=${BRANCH}"
# Following commit corresponds to tag DEV.HYPLNK_LLD.02.01.00.01
SRCREV = "6910da379501984ecf27f8d23ba6fc6310fe387e"

PV = "2.1.0"
PR = "r0"

DEVICELIST = " k2h \
               k2k \
               k2e \
"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/${LLDNAME}"
