DESCRIPTION = "Chip support library low level interface"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5857833e20836213677fac33f9aded21"

COMPATIBLE_MACHINE = "keystone"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-rtos/common-csl-ip.git;protocol=git;branch=${BRANCH}"
# commit ID corresponds to DEV.CSL_KEYSTONE2.02.01.00.07A
SRCREV = "c78867df9165fdf8042fb692fcea776fc0102326"
