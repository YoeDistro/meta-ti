DESCRIPTION = "Firmware for DSP "
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://DSPDCE-${PV}-Manifest.doc;md5=587a7b9fdd99f98fbe99af4a4ba3362a"

COMPATIBLE_MACHINE = "dra7xx-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "http://downloads.ti.com/dsps/dsps_public_sw/glsdk/dspdce/1_00_00_02/exports/dspdce-${MACHINE}-1.00.00.02.tar.gz;protocol=http"

S = "${WORKDIR}/dspdce-${MACHINE}-${PV}"

SRC_URI[md5sum] = "df19baf16f828b5d32b4eed50f0364f5"
SRC_URI[sha256sum] = "0c0f34674110d1ef0300b61b572a326daffb95a31cc9d45888b2e50a83c6f555"

TARGET = "dra7-dsp1-fw.xe66"

do_install() {
        mkdir -p ${D}${base_libdir}/firmware
        cp ${S}/firmware/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} += "${base_libdir}/firmware/${TARGET}"

INSANE_SKIP_${PN} = "arch"
