DESCRIPTION = "Provides library for handling packet descriptors for keystone devices"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://docs/Doxyfile;md5=91d01dc3788e1f405f000b2089191a0a"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PKTLIB_GIT_URI = "git://git.ti.com/git/keystone-rtos/pktlib.git"
PKTLIB_GIT_PROTOCOL = "https"
PKTLIB_GIT_BRANCH = "master"
PKTLIB_GIT_DESTSUFFIX = "git/ti/runtime/pktlib"

# Below commit ID corresponds to "DEV.PKTLIB.2.1.0.8B"
PKTLIB_SRCREV = "6d3758fda4c8c40fa9bd1194a9c713c9675b5a40"

BRANCH = "${PKTLIB_GIT_BRANCH}"
# Don't use DESTSUFFIX when cloning since repo contains ti/runtime/pktlib directory tree
SRC_URI = "${PKTLIB_GIT_URI};protocol=${PKTLIB_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${PKTLIB_SRCREV}"
PV = "2.1.0.8B"
INC_PR = "r0"

S = "${WORKDIR}/${PKTLIB_GIT_DESTSUFFIX}"
