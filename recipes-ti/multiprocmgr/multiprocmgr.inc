HOMEPAGE = "http://git.ti.com/cgit/cgit.cgi/keystone-linux/multi-proc-manager.git"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fce208c498eb9669223724dc9c1d8fe4"
SECTION = "console"
COMPATIBLE_MACHINE = "keystone"

MULTIPROCMGR_GIT_URI = "git://git.ti.com/git/keystone-linux/multi-proc-manager.git"
MULTIPROCMGR_GIT_PROTOCOL = "https"
MULTIPROCMGR_GIT_BRANCH = "master"

# The following commits correspond to DEV.MPM-3.00.05.00
MULTIPROCMGR_SRCREV = "810d8a31c797d07abe808cb3fa89465fa934d4dd"

PV = "3.0.5.0"
INC_PR = "r1"

BRANCH = "${MULTIPROCMGR_GIT_BRANCH}"
SRC_URI = "${MULTIPROCMGR_GIT_URI};protocol=${MULTIPROCMGR_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${MULTIPROCMGR_SRCREV}"

S = "${WORKDIR}/git"
