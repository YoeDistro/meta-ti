python __anonymous() {
    features = bb.data.getVar("MACHINE_FEATURES", d, 1)
    if not features:
        return
    if "mmip" not in features:
        raise bb.parse.SkipPackage('ipumm-fw does not apply to systems without the "mmip" flag in MACHINE_FEATURES')
}

DESCRIPTION = "Firmware for IPU for supporting Accelerated MM decode and encode"
LICENSE = "TI-TSPA"

LIC_FILES_CHKSUM = "file://MMIP-${PV}-Manifest.doc;md5=81f4b23808c36c9223523f43e35d291c"

COMPATIBLE_MACHINE = "dra7xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = " libdce"

SRC_URI = "http://downloads.ti.com/infotainment/esd/jacinto6/glsdk/ipumm/3_00_08_02/exports/ipumm-dra7xx-evm-${PV}.tar.gz;protocol=http"

SRC_URI[md5sum] = "53f47909c2e2575b188bba9ddc325437"
SRC_URI[sha256sum] = "4ac7598bbbb5dd0cd6504906afe0e09d76ba612a3c326e45425e67535e5f2d09"

S = "${WORKDIR}/ipumm-dra7xx-evm-${PV}"

TARGET = "dra7-ipu2-fw.xem4"

do_install() {
        mkdir -p ${D}${base_libdir}/firmware
        cp ${S}/firmware/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} += "${base_libdir}/firmware/${TARGET}"

PR = "r2"
