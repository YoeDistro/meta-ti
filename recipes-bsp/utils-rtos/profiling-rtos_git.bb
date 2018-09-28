SUMMARY = "TI RTOS profiling utilities"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://profilingver.h;beginline=1;endline=46;md5=974494ad60f33cfc0340e421c5a5a7a0"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PROFILING_GIT_URI = "git://git.ti.com/keystone-rtos/utils.git"
PROFILING_GIT_PROTOCOL = "git"
PROFILING_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.UTILS.01.00.00.07"
PROFILING_SRCREV = "1f956c44ea369c9b138859cea619459a312681ea"

BRANCH = "${PROFILING_GIT_BRANCH}"
SRC_URI = "${PROFILING_GIT_URI};protocol=${PROFILING_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${PROFILING_SRCREV}"
PV = "01.00.00.07"
PR = "r0"

S = "${WORKDIR}/git/profiling"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_PROFILING_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "Profiling Utility Library"
