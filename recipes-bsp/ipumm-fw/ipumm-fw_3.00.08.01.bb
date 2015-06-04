python __anonymous() {
    features = bb.data.getVar("MACHINE_FEATURES", d, 1)
    if not features:
        return
    if "mmip" not in features:
        raise bb.parse.SkipPackage('ipumm-fw does not apply to systems without the "mmip" flag in MACHINE_FEATURES')
}

DESCRIPTION = "Firmware for IPU for supporting Accelerated MM decode and encode"
LICENSE = "TI-TSPA"

LIC_FILES_CHKSUM = "file://MMIP-${PV}-Manifest.doc;md5=caa45d993ac010abe2fd319f6613bc26"

COMPATIBLE_MACHINE = "dra7xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = "libdce"

SRC_URI = "http://arago-project.org/files/short-term/misc/ipumm-dra7xx-evm-${PV}.tar.gz;protocol=http"

SRC_URI[md5sum] = "e770dce63fdf218bb19ceb748d9812c0"
SRC_URI[sha256sum] = "044d49b77560660bdfc29894e669fe8f70dc467c66b4acb49b24722a0799baa1"

S = "${WORKDIR}/ipumm-dra7xx-evm-${PV}"

TARGET = "dra7-ipu2-fw.xem4"

do_install() {
        mkdir -p ${D}${base_libdir}/firmware
        cp ${S}/firmware/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} += "${base_libdir}/firmware/${TARGET}"
