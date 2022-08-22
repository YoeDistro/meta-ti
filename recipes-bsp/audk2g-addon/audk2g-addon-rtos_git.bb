SUMMARY = "TI RTOS Audio add-on software for k2g"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://audk2gaddonver.h;beginline=8;endline=47;md5=7a3996aaf1a3d3ca87358cf9b89bce3d"

COMPATIBLE_MACHINE = "k2g"
PACKAGE_ARCH = "${MACHINE_ARCH}"

AUDK2G_ADDON_GIT_URI = "git://git.ti.com/git/processor-sdk/audk2g-addon.git"
AUDK2G_ADDON_GIT_PROTOCOL = "https"
AUDK2G_ADDON_GIT_BRANCH = "master"
AUDK2G_ADDON_GIT_DESTSUFFIX = "git/ti/addon/audk2g"

# Below commit ID corresponds to "DEV.AUDK2G.01.04.00.00"
AUDK2G_ADDON_SRCREV = "815180b45ceb1fa3c110f9979106c7f5aa8b3ca2"

BRANCH = "${AUDK2G_ADDON_GIT_BRANCH}"
SRC_URI = "${AUDK2G_ADDON_GIT_URI};destsuffix=${AUDK2G_ADDON_GIT_DESTSUFFIX};protocol=${AUDK2G_ADDON_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${AUDK2G_ADDON_SRCREV}"
PV = "01.04.00.00"
PR = "r0"

S = "${WORKDIR}/git/ti/addon/audk2g"

DEPENDS_append = " ti-sysbios \
            gpio-lld-rtos \
            i2c-lld-rtos \
            board-rtos \
"

export PDK_AUDK2G_ROOT_PATH = "${WORKDIR}/build"
export DEST_ROOT="${S}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"
