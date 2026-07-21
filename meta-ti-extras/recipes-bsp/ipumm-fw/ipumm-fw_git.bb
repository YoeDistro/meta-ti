SUMMARY = "Firmware for IPU to support Accelerated MM decode and encode"

LICENSE = "LicenseRef-TI-TSPA"
LIC_FILES_CHKSUM = "file://LICENCE.ti-tspa;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am57xx"

inherit features_check

REQUIRED_MACHINE_FEATURES = "mmip"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${IPUMM_FW_VERSION}"
PR = "r8"

inherit update-alternatives

TARGET = "dra7-ipu2-fw.xem4"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/ti-ce-fw
    install -m 0644 ${S}/ti-ce-fw/${TARGET} ${D}${nonarch_base_libdir}/firmware/ti-ce-fw/${TARGET}
}

ALTERNATIVE:${PN} = "dra7-ipu2-fw.xem4"
ALTERNATIVE_LINK_NAME[dra7-ipu2-fw.xem4] = "${nonarch_base_libdir}/firmware/${TARGET}"
ALTERNATIVE_TARGET[dra7-ipu2-fw.xem4] = "${nonarch_base_libdir}/firmware/ti-ce-fw/${TARGET}"
ALTERNATIVE_PRIORITY = "20"

# Disable the "buildpaths" check while we figure out how we are
# going to address this issue.
#
# The ti-cgt-arm compiler is a custom TI compiler.  It does not
# currently support reproducible builds and is provided via a
# binary blob download that we cannot patch in the recipe to address
# the issue.
INSANE_SKIP:${PN} += "buildpaths"
