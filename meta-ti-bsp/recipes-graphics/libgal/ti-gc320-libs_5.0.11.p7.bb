SUMMARY = "Userspace libraries for GC320 chipset on TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/ti-gc320-libs"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://Manifest.html;md5=a9121e8936ace09820d23f7626daaca5"

inherit features_check

REQUIRED_MACHINE_FEATURES = "gc320"

COMPATIBLE_MACHINE = "am57xx"

CLEANBROKEN = "1"

BRANCH = "ti-${PV}"

SRC_URI = "git://git.ti.com/git/graphics/ti-gc320-libs.git;protocol=https;branch=${BRANCH}"
SRCREV = "85c175e8425c33dda6e272aeb45afe0f69a79096"

RRECOMMENDS:${PN} = "ti-gc320-driver"

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

TARGET_PRODUCT = "jacinto6evm"

PR = "r3"

EXTRA_OEMAKE += "DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT} LIBDIR=${libdir}"

do_install() {
    oe_runmake install
    chown -R root:root ${D}
}

PACKAGES = "${PN} ${PN}-dev"
FILES:${PN} += "${libdir}/libGAL.so"
FILES:${PN}-dev = "/usr/include"

INSANE_SKIP:${PN} += "ldflags already-stripped dev-so"
