SUMMARY = "TI RTOS Board Library"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://board.h;beginline=1;endline=32;md5=9bed8e4ac2fb37fc627cefe49eb1c919"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

BOARD_GIT_URI = "git://git.ti.com/keystone-rtos/board.git"
BOARD_GIT_PROTOCOL = "git"
BOARD_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.BOARD.01.00.10.02"
BOARD_SRCREV = "60bde3650d067e4064f93c292c8d9108aa1bf57b"

BRANCH ="${BOARD_GIT_BRANCH}"
SRC_URI = "${BOARD_GIT_URI};protocol=${BOARD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${BOARD_SRCREV}"
PV = "01.00.10.02"
PR = "r0"

DEPENDS_append = " i2c-lld-rtos \
                   spi-lld-rtos \
                   uart-lld-rtos \
"

DEPENDS_append_omap-a15 = " osal-rtos \
                            ti-ndk \
                            mmcsd-lld-rtos \
                            pm-lld-rtos \
"

DEPENDS_append_am57xx-evm = " gpio-lld-rtos \
                              icss-emac-lld-rtos \
                              pruss-lld-rtos \
"


DEPENDS_append_ti33x = " gpio-lld-rtos \
                         gpmc-lld-rtos \
                         icss-emac-lld-rtos \
                         mmcsd-lld-rtos \
                         osal-rtos \
                         pruss-lld-rtos \
                         starterware-rtos \
                         ti-ndk \
"

DEPENDS_append_ti43x = " gpio-lld-rtos \
                         gpmc-lld-rtos \
                         icss-emac-lld-rtos \
                         mmcsd-lld-rtos \
                         osal-rtos \
                         pruss-lld-rtos \
                         starterware-rtos \
                         ti-ndk \
"

DEPENDS_append_dra7xx = " pm-lld-rtos \
                          mmcsd-lld-rtos \
"

DEPENDS_append_k3 = " sciclient-rtos \
                              pm-lld-rtos \
"


# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_STRIP = "1"

export PDK_BOARD_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

XDCPATH_append = ";${PDK_INSTALL_DIR}/packages/ti/csl;${NDK_INSTALL_DIR}/packages"

INSANE_SKIP_${PN} = "arch"
