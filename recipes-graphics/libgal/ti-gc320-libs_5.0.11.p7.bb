DESCRIPTION = "Userspace libraries for GC320 chipset on TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/ti-gc320-libs"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://Manifest.html;md5=4d759c4e533af6aaeff2af7f405697c2"

PACKAGE_ARCH = "${MACHINE_ARCH}"

CLEANBROKEN = "1"

BRANCH = "ti-${PV}"

SRC_URI = "git://git.ti.com/graphics/ti-gc320-libs.git;protocol=git;branch=${BRANCH}"
SRCREV = "ab0ca5bff345f6c13807fea3c4acabf5f2b9b10a"

# There's only hardfp version available
python __anonymous() {
    tunes = d.getVar("TUNE_FEATURES", d, 1)
    if not tunes:
        return
    pkgn = d.getVar("PN", d, 1)
    pkgv = d.getVar("PV", d, 1)
    if "callconvention-hard" not in tunes:
        bb.warn("%s-%s ONLY supports hardfp mode for now" % (pkgn, pkgv))
        raise bb.parse.SkipPackage("%s-%s ONLY supports hardfp mode for now" % (pkgn, pkgv))
}

TARGET_PRODUCT_omap-a15 = "jacinto6evm"

PR = "r1"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT} LIBDIR=${libdir}"

do_install() {
    oe_runmake install
}

INSANE_SKIP_${PN} += "ldflags"
