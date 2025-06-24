SUMMARY = "Firmware files for use with TI wl18xx"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://LICENCE;md5=4977a0fe767ee17765ae63c435a32a9e"

SRC_URI = "git://git.ti.com/git/wilink8-wlan/wl18xx_fw.git;protocol=https;branch=${BRANCH}"

SRCREV = "5ec05007f2662f460f881c5868311fd3ab7e6e71"
BRANCH = "master"

CLEANBROKEN = "1"

do_compile() {
    :
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/ti-connectivity
    install -m 0644 ${S}/*.bin ${D}${nonarch_base_libdir}/firmware/ti-connectivity/
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/ti-connectivity/*"
