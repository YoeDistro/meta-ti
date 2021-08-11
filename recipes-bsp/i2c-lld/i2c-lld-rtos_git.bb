SUMMARY = "TI RTOS low level driver for Inter-IC module (I2C)"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.i2c"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://I2C.h;beginline=1;endline=32;md5=50084375278c1a2779571be134f98f7c"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"


PR = "r0"

DEPENDS:append = " osal-rtos \
"
DEPENDS:append:ti33x = " starterware-rtos \
                         pruss-lld-rtos \
"
DEPENDS:append:ti43x = " starterware-rtos \
                         pruss-lld-rtos \
"
DEPENDS:append:am57xx-evm = " pruss-lld-rtos \
"

DEPENDS:append:k2g = " pruss-lld-rtos \
"

DEPENDS:append:j7 = " sciclient-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"


export PDK_I2C_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

INSANE_SKIP:${PN} = "arch"

# HTML doc link params
PDK_COMP_LINK_TEXT = "I2C LLD"
