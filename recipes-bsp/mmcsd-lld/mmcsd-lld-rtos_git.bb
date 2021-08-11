SUMMARY = "TI Multimedia card(MMC)/Secure Digital(SD) low level driver for RTOS "

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.mmcsd"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://MMCSD.h;beginline=1;endline=32;md5=f74069541d4d165a000a66b4043cb065"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g|omapl1|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"


PR = "r0"


DEPENDS:append = " edma3-lld-rtos \
                   osal-rtos \
"
DEPENDS:append:ti33x = " starterware-rtos \
"
DEPENDS:append:ti43x = " starterware-rtos \
"
DEPENDS:remove:k3 = "edma3-lld-rtos "

DEPENDS:append:k3 = " sciclient-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_MMCSD_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH:append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "MMCSD LLD"
