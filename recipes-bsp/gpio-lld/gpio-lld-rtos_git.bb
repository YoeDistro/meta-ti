SUMMARY = "TI RTOS driver for General Purpose IO (GPIO)"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.gpio"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://GPIO.h;beginline=1;endline=31;md5=8580f9c5c0de8d5d13518cf18a0122b8"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1|c667x-evm|c665x-evm|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"


DEPENDS_append = " osal-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"
DEPENDS_append_k3 = " sciclient-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_GPIO_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "GPIO LLD"
