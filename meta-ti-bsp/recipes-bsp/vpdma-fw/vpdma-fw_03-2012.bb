SUMMARY = "VPDMA firmware for Video Input Port and Video Processing Engine"

LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://COPYING;md5=fd463c9500441ed91d07a0331baa635c"

COMPATIBLE_MACHINE = "dra7xx"

SRC_URI = "http://downloads.ti.com/dsps/dsps_public_sw/glsdk/vpdma-fw/03-2012/exports/vpdma-fw_03-2012.tar.gz;protocol=http;name=dra7xx-evm"
SRC_URI[dra7xx-evm.md5sum] = "80176df1350c21d9efa90171789c546e"
SRC_URI[dra7xx-evm.sha256sum] = "a0b254ffd0c7f481cb3989e632088f5e4a233c73a1c676faa3061721ea60dc90"

S = "${WORKDIR}/vpdma-fw-${PV}"
TARGET = "vpdma-1b8.bin"

do_install() {
    mkdir -p ${D}${nonarch_base_libdir}/firmware
    cp ${S}/${TARGET} ${D}${nonarch_base_libdir}/firmware/${TARGET}
}

FILES:${PN} += "${nonarch_base_libdir}/firmware/${TARGET}"

PR = "r1"
