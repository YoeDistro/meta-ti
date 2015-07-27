python __anonymous() {
    features = bb.data.getVar("MACHINE_FEATURES", d, 1)
    if not features:
        return
    if "mmip" not in features:
        raise bb.parse.SkipPackage('ipumm-fw does not apply to systems without the "mmip" flag in MACHINE_FEATURES')
}

DESCRIPTION = "Firmware for IPU for supporting Accelerated MM decode and encode"
LICENSE = "TI-TSPA"

LIC_FILES_CHKSUM = "file://MMIP-${PV}-Manifest.doc;md5=57d49afcca8f1be47b1c4e79aa06b3ba"

COMPATIBLE_MACHINE = "dra7xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = "libdce"

SRC_URI = "http://downloads.ti.com/dsps/dsps_public_sw/glsdk/ipumm/3_00_08_01/exports/ipumm-dra7xx-evm-${PV}.tar.gz;protocol=http"

SRC_URI[md5sum] = "a1873333e7676ec1b7fcb18fb8b90696"
SRC_URI[sha256sum] = "d583d827191a3772f496145cbf81747e10e7ed553d4653379576ac891019d7fb"

S = "${WORKDIR}/ipumm-dra7xx-evm-${PV}"

TARGET = "dra7-ipu2-fw.xem4"

do_install() {
        mkdir -p ${D}${base_libdir}/firmware
        cp ${S}/firmware/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} += "${base_libdir}/firmware/${TARGET}"
