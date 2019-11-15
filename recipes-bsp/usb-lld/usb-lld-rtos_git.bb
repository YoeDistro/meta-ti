SUMMARY = "TI RTOS USB low level driver"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.usb"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://USBver.h;beginline=16;endline=46;md5=5394fa32763383d2c8e2aca5a1c67805"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g|omapl1|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

DEPENDS_append = " osal-rtos"
DEPENDS_append_ti33x = " starterware-rtos"
DEPENDS_append_ti43x = " starterware-rtos"
DEPENDS_append_k3 = " sciclient-rtos"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_USB_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "USB LLD"
