DESCRIPTION = "Firmware for DSP for an example application called copycodectest"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://DSPDCE-${PV}-Manifest.doc;md5=b8feda5a3ed8197d05993c0d0ff5590e"

COMPATIBLE_MACHINE = "dra7xx-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "http://downloads.ti.com/dsps/dsps_public_sw/glsdk/dspdce/1_00_00_04/exports/dspdce-${MACHINE}-1.00.00.04.tar.gz;protocol=ftp"

S = "${WORKDIR}/dspdce-${MACHINE}-${PV}"

SRC_URI[md5sum] = "72722b12e90c26a85f7f1b56d749cad0"
SRC_URI[sha256sum] = "64057db41dbee5a992db88129a9154f27c67cfbba8efdc0f09b7de38df4d58eb"

TARGET = "dra7-dsp1-fw.xe66"

do_install() {
        mkdir -p ${D}${base_libdir}/firmware
        cp ${S}/firmware/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} += "${base_libdir}/firmware/${TARGET}"

INSANE_SKIP_${PN} = "arch"

PR = "r1"
