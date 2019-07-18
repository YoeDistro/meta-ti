SUMMARY = "TI RTOS Trace utility"

DESCRIPTION = "This utility allows for tracing support in PDK drivers"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://tracever.h;beginline=19;endline=47;md5=983f531ecfc9fc8c4bc3af9333ab6696"

COMPATIBLE_MACHINE = "k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

TRACE_GIT_URI = "git://git.ti.com/keystone-rtos/utils.git"
TRACE_GIT_PROTOCOL = "git"
TRACE_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.UTILS.01.00.00.09"
TRACE_SRCREV = "84802d7a787a53f33a24254069c90208c5d87d1e"

BRANCH = "${TRACE_GIT_BRANCH}"
SRC_URI = "${TRACE_GIT_URI};protocol=${TRACE_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${TRACE_SRCREV}"
PV = "01.00.00.09"
PR = "r0"

S = "${WORKDIR}/git/trace"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_TRACE_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "Trace Utility"
