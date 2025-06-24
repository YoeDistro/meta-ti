SUMMARY = "Configuration utility for TI CC33xx wireless devices"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d5fc448a36efe573623542dcb989afc4"

SRCREV = "a2f84140b8d67cfc94472cf92676a409d1e47d86"
SRC_URI = "git://git.ti.com/git/cc33xx-wlan/cc33xx-utils.git;branch=master;protocol=https"

PV = "1.7.0.120"

S:append = "/cc33conf"

EXTRA_OEMAKE = "CC="${CC}""

do_install() {
	install -d ${D}${sbindir}
	install -d ${D}${sbindir}/cc33conf/

	install -m 0755 cc33xxconf ${D}${sbindir}/cc33conf/
	install -m 0755 dictionary.txt ${D}${sbindir}/cc33conf/
	install -m 0755 default.conf ${D}${sbindir}/cc33conf/
	install -m 0755 README ${D}${sbindir}/cc33conf/
	install -m 0755 cc33xx-conf.conf ${D}${sbindir}/cc33conf/
	install -m 0755 cc33xx-conf.ini ${D}${sbindir}/cc33conf/
	install -m 0755 conf.h ${D}${sbindir}/cc33conf/
}

FILES:${PN} += " \
	${sbindir}/cc33conf \
"
