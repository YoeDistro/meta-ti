python __anonymous() {
    features = bb.data.getVar("MACHINE_FEATURES", d, 1)
    if not features:
        return
    if "mmip" not in features:
        raise bb.parse.SkipPackage('ipumm-fw does not apply to systems without the "mmip" flag in MACHINE_FEATURES')
}

DESCRIPTION = "Firmware for IPU "

LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM_dra7xx = "file://MMIP-${PV}-Manifest.doc;md5=64ca6f9d7e5243b8a4084eb918a543bc"
LIC_FILES_CHKSUM_omap5-evm = "file://MMIP-${PV}-Manifest.doc;md5=39a593dd8fc2a9654c74f679ed329c45"

COMPATIBLE_MACHINE = "omap-a15"
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Need to set the PLATFORM variable since now multiple MACHINEs are sharing
# a common named tarball
PLATFORM = "UNKNOWN"
PLATFORM_dra7xx = "dra7xx-evm"
PLATFORM_omap5-evm = "omap5-evm"

SRC_URI = "http://downloads.ti.com/dsps/dsps_public_sw/glsdk/ipumm/3_00_04_02/exports/ipumm-${PLATFORM}-3.00.04.02.tar.gz;protocol=http;name=${PLATFORM}"

# DRA7xx checksums
SRC_URI[dra7xx-evm.md5sum] = "da04c03b0fd57901e8283afa80f81379"
SRC_URI[dra7xx-evm.sha256sum] = "0b26fd13f9c318b52c18b9bff56862ef1f58a768b19285b87c035e65bc828ddc"

# OMAP5 checksums
SRC_URI[omap5-evm.md5sum] = "1572073ebd4f6d127c4e8cf47d270b6a"
SRC_URI[omap5-evm.sha256sum] = "2fdb469f19e3c9984854f5bc82444fca4d7da869f5e645da22ec0d5beaa5cc65"

S = "${WORKDIR}/ipumm-${MACHINE}-${PV}"

TARGET_dra7xx = "dra7-ipu2-fw.xem4"
TARGET_omap5-evm = "ducati-m3-core0.xem3"

do_install() {
        mkdir -p ${D}${base_libdir}/firmware
        cp ${S}/firmware/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} += "${base_libdir}/firmware/${TARGET}"
