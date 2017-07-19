SUMMARY = "TI RTOS USB low level driver"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://USBver.h;beginline=16;endline=46;md5=5394fa32763383d2c8e2aca5a1c67805"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g"
PACKAGE_ARCH = "${MACHINE_ARCH}"

USB_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/usb.git"
USB_LLD_GIT_PROTOCOL = "git"
USB_LLD_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.USB_LLD.01.00.00.09"
USB_LLD_SRCREV = "6c2b21e5f6f8b34313f8d30a1de9b3e2fc301a73"

BRANCH = "${USB_LLD_GIT_BRANCH}"
SRC_URI = "${USB_LLD_GIT_URI};protocol=${USB_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${USB_LLD_SRCREV}"
PV = "01.00.00.09"
PR = "r0"

DEPENDS_append = " osal-rtos"
DEPENDS_append_ti33x = " starterware-rtos"
DEPENDS_append_ti43x = " starterware-rtos"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_USB_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "USB LLD"
