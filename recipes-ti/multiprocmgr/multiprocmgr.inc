HOMEPAGE = "http://git.ti.com/cgit/cgit.cgi/keystone-linux/multi-proc-manager.git"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fce208c498eb9669223724dc9c1d8fe4"
SECTION = "console"
COMPATIBLE_MACHINE = "keystone"

BRANCH = "master"
# The following commits correspond to DEV.MPM-3.00.00.02
SRCREV = "319c6cfe15054df08eb70cbedc99a88dd5a06cda"
PV = "3.0.0.2"

SRC_URI = "git://git.ti.com/keystone-linux/multi-proc-manager.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
