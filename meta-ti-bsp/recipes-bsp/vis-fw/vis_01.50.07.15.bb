SUMMARY = "Radio Application Firmware"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://${S}/J6_VIS_DEMO_LINUX_BINARY_01.50.07.15-Manifest.html;md5=a59aa54b9470f555cf086b91dca0afa3"

COMPATIBLE_MACHINE = "dra7xx"

PR = "r1"

SRC_URI = "http://downloads.ti.com/dsps/dsps_public_sw/glsdk/vis/01_50_07_15/exports/vis-dra7xx-evm-01.50.07.15.tar.gz;protocol=http"

SRC_URI[md5sum] = "fe8b00e398fb3b7ada0c15b601867acb"
SRC_URI[sha256sum] = "6e2aa47ae892910616ebdc96646af778af9a59ca0657716ab4fa4b24a9afae69"

S = "${UNPACKDIR}"

DSPAPP = "dra7-dsp1-fw-radio.xe66"
GPPAPP = "RadioApp"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware
    install -d ${D}${bindir}
    install ${S}/prebuilt/${DSPAPP} ${D}${nonarch_base_libdir}/firmware/${DSPAPP}
    install -m 0755 ${S}/prebuilt/${GPPAPP} ${D}${bindir}/${GPPAPP}
}

PACKAGES += "${PN}-fw"
RDEPENDS:${PN} += "${PN}-fw"

FILES:${PN}-fw += "${nonarch_base_libdir}/firmware/${DSPAPP}"

INSANE_SKIP:${PN} = "ldflags"
INSANE_SKIP:${PN}-fw = "arch"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
