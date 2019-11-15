SUMMARY = "TI RTOS driver for FAT filesystem"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.fs.fatfs"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://FATFS.h;beginline=1;endline=32;md5=6619832755598d1cc2b01f2e6a1801d6"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g|omapl1|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

DEPENDS_append = " mmcsd-lld-rtos \
                   osal-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_FATFS_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "FATFS Library"
