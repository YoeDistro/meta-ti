HOMEPAGE = "http://git.ti.com/cgit/cgit.cgi/keystone-linux/multi-proc-manager.git"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fce208c498eb9669223724dc9c1d8fe4"
SECTION = "console"
COMPATIBLE_MACHINE = "keystone"

BRANCH = "linux41"
# The following commits correspond to DEV.MPM-3.00.00.00_ENG
SRCREV = "158e7d419662b482a089fb66ecdfced1104654ae"
PV = "3.0.0.0"

SRC_URI = "git://git.ti.com/keystone-linux/multi-proc-manager.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
