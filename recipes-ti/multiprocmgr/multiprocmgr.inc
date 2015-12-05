HOMEPAGE = "http://git.ti.com/cgit/cgit.cgi/keystone-linux/multi-proc-manager.git"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fce208c498eb9669223724dc9c1d8fe4"
SECTION = "console"
COMPATIBLE_MACHINE = "keystone"

BRANCH = "linux41"
# The following commits correspond to DEV.MPM-3.00.00.01_ENG
SRCREV = "c1c245814b835509b99d144b52e00316665ac477"
PV = "3.0.0.1"

SRC_URI = "git://git.ti.com/keystone-linux/multi-proc-manager.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
