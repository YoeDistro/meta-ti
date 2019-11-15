SUMMARY = "TI RTOS Trace utility"

DESCRIPTION = "This utility allows for tracing support in PDK drivers"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.utils.trace"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://tracever.h;beginline=19;endline=47;md5=983f531ecfc9fc8c4bc3af9333ab6696"

COMPATIBLE_MACHINE = "k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"


PR = "r0"


# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_TRACE_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "Trace Utility"
