SUMMARY = "TI RTOS profiling utilities"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.utils.profiling"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://profilingver.h;beginline=1;endline=46;md5=974494ad60f33cfc0340e421c5a5a7a0"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"


PR = "r0"


# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_PROFILING_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "Profiling Utility Library"
