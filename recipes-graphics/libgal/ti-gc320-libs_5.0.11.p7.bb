DESCRIPTION = "Userspace libraries for GC320 chipset on TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/ti-gc320-libs"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://Manifest.html;md5=a9121e8936ace09820d23f7626daaca5"

PACKAGE_ARCH = "${MACHINE_ARCH}"

CLEANBROKEN = "1"

BRANCH = "ti-${PV}"

SRC_URI = "git://git.ti.com/graphics/ti-gc320-libs.git;protocol=git;branch=${BRANCH}"
SRCREV = "c0afab259de59909cfe74c01f3f7fbaa147f94b5"

# There's only hardfp version available
python __anonymous() {
    tunes = d.getVar("TUNE_FEATURES")
    if not tunes:
        return
    pkgn = d.getVar("PN")
    pkgv = d.getVar("PV")
    if "callconvention-hard" not in tunes:
        bb.warn("%s-%s ONLY supports hardfp mode for now" % (pkgn, pkgv))
        raise bb.parse.SkipPackage("%s-%s ONLY supports hardfp mode for now" % (pkgn, pkgv))
}

TARGET_PRODUCT_omap-a15 = "jacinto6evm"

PR = "r2"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT} LIBDIR=${libdir}"

do_install() {
    oe_runmake install
}

INSANE_SKIP_${PN} += "ldflags"
