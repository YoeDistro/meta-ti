SUMMARY = "TI RTOS low level driver for IO-Link Master"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.iolink"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://IOLINK.h;beginline=1;endline=32;md5=da669339a4b8594ed3edbe7ca30c726e"

COMPATIBLE_MACHINE = "ti43x"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r2"

DEPENDS_append = " osal-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
                         pruss-lld-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"


export PDK_IOLINK_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

INSANE_SKIP_${PN} = "arch"

# HTML doc link params
PDK_COMP_LINK_TEXT = "IOLINK LLD"
