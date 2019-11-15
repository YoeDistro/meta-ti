SUMMARY = "TI RTOS low level driver for General Purpose Memory Controller (GPMC)"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.gpmc"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://GPMC.h;beginline=1;endline=31;md5=485ec97c36f809bf92323fde3e988c72"

COMPATIBLE_MACHINE = "ti33x|ti43x"
PACKAGE_ARCH = "${MACHINE_ARCH}"


PR = "r0"

DEPENDS_append = " osal-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

# HTML doc link params
PDK_COMP_LINK_TEXT = "GPMC LLD"

export PDK_GPMC_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"
