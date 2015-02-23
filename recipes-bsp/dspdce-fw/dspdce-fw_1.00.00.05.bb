DESCRIPTION = "Firmware for DSP for an example application called copycodectest"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://DSPDCE-${PV}-Manifest.doc;md5=386d1802eefc2fcf00ab01a5b4556277"

COMPATIBLE_MACHINE = "dra7xx-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "http://downloads.ti.com/dsps/dsps_public_sw/glsdk/dspdce/1_00_00_05/exports/dspdce-${MACHINE}-1.00.00.05.tar.gz;protocol=ftp"

S = "${WORKDIR}/dspdce-${MACHINE}-${PV}"

SRC_URI[md5sum] = "4f3d5c6ef088019aa4804eaaedc8e949"
SRC_URI[sha256sum] = "8d99c4f149613448a6d72c62a18cc7445c63c687b786658da1762c4e1c03a6a0"

TARGET = "dra7-dsp1-fw.xe66"

do_install() {
        mkdir -p ${D}${base_libdir}/firmware
        cp ${S}/firmware/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} += "${base_libdir}/firmware/${TARGET}"

INSANE_SKIP_${PN} = "arch"

PR = "r1"
