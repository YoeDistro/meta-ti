HOMEPAGE = "http://git.ti.com/cgit/cgit.cgi/keystone-linux/multi-proc-manager.git"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fce208c498eb9669223724dc9c1d8fe4"
SECTION = "console"
COMPATIBLE_MACHINE = "keystone"

MULTIPROCMGR_GIT_URI = "git://git.ti.com/keystone-linux/multi-proc-manager.git"
MULTIPROCMGR_GIT_PROTOCOL = "git"
MULTIPROCMGR_GIT_BRANCH = "master"

# The following commits correspond to DEV.MPM-3.00.01.00
MULTIPROCMGR_SRCREV = "6c2c0f7d14d34eb9074c6d6a6bd112da3751fb7e"

PV = "3.0.1.0"
INC_PR = "r0"

BRANCH = "${MULTIPROCMGR_GIT_BRANCH}"
SRC_URI = "${MULTIPROCMGR_GIT_URI};protocol=${MULTIPROCMGR_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${MULTIPROCMGR_SRCREV}"

S = "${WORKDIR}/git"
