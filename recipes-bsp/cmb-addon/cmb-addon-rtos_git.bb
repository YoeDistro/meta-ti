SUMMARY = "TI RTOS software supporting circular microphone array board"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://cmbaddonver.h;beginline=8;endline=47;md5=d0cb159bf210cfafed63042da01c83e0"

COMPATIBLE_MACHINE = "k2g|omapl137-evm"

PACKAGE_ARCH = "${MACHINE_ARCH}"

CMB_ADDON_GIT_URI = "git://git.ti.com/git/processor-sdk/circmicarray-addon.git"
CMB_ADDON_GIT_PROTOCOL = "https"
CMB_ADDON_GIT_BRANCH = "master"
CMB_ADDON_GIT_DESTSUFFIX = "git/ti/addon"

# Below commit ID corresponds to "DEV.CMB.01.01.00.03A"
CMB_ADDON_SRCREV = "3655aad07f17d3a167ec7dcecb52450c1d2fe8be"

SRCREV = "${CMB_ADDON_SRCREV}"
PV = "01.01.00.03A"
PR = "r0"

BRANCH = "${CMB_ADDON_GIT_BRANCH}"
SRC_URI = "${CMB_ADDON_GIT_URI};destsuffix=${CMB_ADDON_GIT_DESTSUFFIX};protocol=${CMB_ADDON_GIT_PROTOCOL};branch=${BRANCH}"


S = "${WORKDIR}/git/ti/addon/cmb"

DEPENDS_append = " ti-sysbios \
            gpio-lld-rtos \
            i2c-lld-rtos \
            mcasp-lld-rtos \
            board-rtos \
"

export PDK_CMB_ROOT_PATH = "${WORKDIR}/build"
export DEST_ROOT="${S}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"
