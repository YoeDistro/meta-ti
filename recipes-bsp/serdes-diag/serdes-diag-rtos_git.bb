SUMMARY = "TI RTOS library for SERDES diagnostics"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://serdes_diag.h;beginline=1;endline=42;;md5=68e7ce6fdc0e9328fa7d0ec41c54420c"

COMPATIBLE_MACHINE = "keystone|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SERDES_DIAG_GIT_URI = "git://git.ti.com/keystone-rtos/serdes_diag.git"
SERDES_DIAG_GIT_PROTOCOL = "git"
SERDES_DIAG_GIT_BRANCH = "master"
SERDES_DIAG_GIT_DESTSUFFIX = "git/ti/diag/serdes_diag"

# Below commit ID corresponds to "DEV.DIAG_SERDES_SB.01.00.00.12A"
SERDES_DIAG_SRCREV = "121c3bf0a20493a32f3f0297a370f2994cd288ff"

BRANCH = "${SERDES_DIAG_GIT_BRANCH}"
SRC_URI = "${SERDES_DIAG_GIT_URI};destsuffix=${SERDES_DIAG_GIT_DESTSUFFIX};protocol=${SERDES_DIAG_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${SERDES_DIAG_SRCREV}"
PV = "01.00.00.12A"
PR = "r0"

S = "${WORKDIR}/${SERDES_DIAG_GIT_DESTSUFFIX}"


# Build with make instead of XDC for k3
TI_PDK_XDCMAKE_k3 = "0"

# HTML doc link params
PDK_COMP_LINK_TEXT = "SERDES Diagnostics"

INSANE_SKIP_${PN} = "arch"
