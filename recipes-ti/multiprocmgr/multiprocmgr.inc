HOMEPAGE = "http://git.ti.com/cgit/cgit.cgi/keystone-linux/multi-proc-manager.git"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fce208c498eb9669223724dc9c1d8fe4"
SECTION = "console"
COMPATIBLE_MACHINE = "keystone"

BRANCH = "master"
# The following commits correspond to DEV.MPM-2.00.01.07
SRCREV = "b79e1cdaed78014569d82cf2f748d3f5f91623ee"
PV = "2.0.1.7"

SRC_URI = "git://git.ti.com/keystone-linux/multi-proc-manager.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
