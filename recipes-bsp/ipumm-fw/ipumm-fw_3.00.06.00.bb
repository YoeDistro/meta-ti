python __anonymous() {
    features = bb.data.getVar("MACHINE_FEATURES", d, 1)
    if not features:
        return
    if "mmip" not in features:
        raise bb.parse.SkipPackage('ipumm-fw does not apply to systems without the "mmip" flag in MACHINE_FEATURES')
}

DESCRIPTION = "Firmware for IPU for supporting Accelerated MM decode and encode"
LICENSE = "TI-TSPA"

LIC_FILES_CHKSUM_dra7xx = "file://MMIP-${PV}-Manifest.doc;md5=8206ccaf28c2932afb5afd9c9575287c"
LIC_FILES_CHKSUM_omap5-evm = "file://MMIP-${PV}-Manifest.doc;md5=39a593dd8fc2a9654c74f679ed329c45"

COMPATIBLE_MACHINE = "dra7xx|omap5-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI_dra7xx = "http://downloads.ti.com/dsps/dsps_public_sw/glsdk/ipumm/3_00_06_00/exports/ipumm-dra7xx-evm-3.00.06.00.tar.gz;protocol=ftp;name=dra7xx-evm"

SRC_URI_omap5-evm  = "http://downloads.ti.com/dsps/dsps_public_sw/glsdk/ipumm/3_00_04_02/exports/ipumm-${MACHINE}-3.00.04.02.tar.gz;protocol=http;name=${MACHINE}"

# DRA7xx checksums
SRC_URI[dra7xx-evm.md5sum] = "8e1ae40eb10d631318d001463fa11d79"
SRC_URI[dra7xx-evm.sha256sum] = "30c275146c34d11dbc2dfd568f0bbc3098a13cd981eee8a7a9766cb07d2f1ca5"

# OMAP5 checksums
SRC_URI[omap5-evm.md5sum] = "1572073ebd4f6d127c4e8cf47d270b6a"
SRC_URI[omap5-evm.sha256sum] = "2fdb469f19e3c9984854f5bc82444fca4d7da869f5e645da22ec0d5beaa5cc65"

S_dra7xx = "${WORKDIR}/ipumm-dra7xx-evm-${PV}"
S_omap5-evm = "${WORKDIR}/ipumm-${MACHINE}-${PV}"

TARGET_dra7xx = "dra7-ipu2-fw.xem4"
TARGET_omap5-evm = "ducati-m3-core0.xem3"

do_install() {
        mkdir -p ${D}${base_libdir}/firmware
        cp ${S}/firmware/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} += "${base_libdir}/firmware/${TARGET}"

PR = "r1"
