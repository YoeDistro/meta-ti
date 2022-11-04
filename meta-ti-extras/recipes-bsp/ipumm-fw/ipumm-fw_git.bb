SUMMARY = "Firmware for IPU to suppor Accelerated MM decode and encode"
LICENSE = "TI-TSPA"

LIC_FILES_CHKSUM = "file://Texas_Instruments_ipumm_Manifest.pdf;md5=5cc572579f07af266ab57fc17d762c7f"

COMPATIBLE_MACHINE = "dra7xx"

inherit features_check

REQUIRED_MACHINE_FEATURES = "mmip"

RDEPENDS:${PN} = "libdce"

SRC_URI = "git://git.ti.com/git/ivimm/ipumm.git;protocol=https;branch=master"

SRCREV = "df4c50aecc9aad7ab3eb1ca9ebacfe473fcad7c5"

S = "${WORKDIR}/git"

PV = "3.00.15.00"
PR = "r7"

require recipes-ti/includes/ti-paths.inc

inherit update-alternatives

DEPENDS = "ti-xdctools-native ti-sysbios ti-codec-engine ti-framework-components ti-xdais ti-cgt-arm-native ti-ipc-rtos"

export HWVERSION="ES10"
export BIOSTOOLSROOT="${STAGING_DIR_TARGET}/usr/share/ti"

export XDCVERSION="ti-xdctools-tree"
export BIOSVERSION="ti-sysbios-tree"
export IPCVERSION="ti-ipc-tree"
export CEVERSION="ti-codec-engine-tree"
export FCVERSION="ti-framework-components-tree"
export XDAISVERSION="ti-xdais-tree"

export TMS470CGTOOLPATH="${M4_TOOLCHAIN_INSTALL_DIR}"
export IPCSRC="${STAGING_DIR_TARGET}/usr/share/ti/ti-ipc-tree"

EXTRA_OEMAKE += "XDCDIST_TREE=${STAGING_DIR_NATIVE}/usr/share/ti/${XDCVERSION}"

do_configure() {
    oe_runmake unconfig
    oe_runmake vayu_smp_config
}

do_compile() {
    oe_runmake
}

TARGET = "dra7-ipu2-fw.xem4"
TARGET_MAP = "platform/ti/dce/baseimage/package/cfg/out/ipu/release/ipu.xem4.map"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 0644 ${S}/${TARGET} ${D}${nonarch_base_libdir}/firmware/${TARGET}.${BPN}
    install -m 0644 ${S}/${TARGET_MAP} ${D}${nonarch_base_libdir}/firmware/${TARGET}.map
}

ALTERNATIVE:${PN} = "dra7-ipu2-fw.xem4"
ALTERNATIVE_LINK_NAME[dra7-ipu2-fw.xem4] = "${nonarch_base_libdir}/firmware/${TARGET}"
ALTERNATIVE_TARGET[dra7-ipu2-fw.xem4] = "${nonarch_base_libdir}/firmware/${TARGET}.${BPN}"
ALTERNATIVE_PRIORITY = "20"

FILES:${PN} += "${nonarch_base_libdir}/firmware/*"
