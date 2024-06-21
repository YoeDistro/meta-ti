SUMMARY = "Scripts and configuration files for TI cc33xx wireless drivers"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=904443cf7fae5c09c3d5f83f8557c265"

SRCREV = "4371c93ea01b339f6f73f77d6d6bfcc185def8c0"
SRC_URI = "git://git.ti.com/git/cc33xx-wlan/cc33xx-target-scripts.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

FILES:${PN} += "${datadir}/cc33xx/"

do_install() {
	install -d ${D}${datadir}/cc33xx/

	scripts=`find ./* -type f -name "*.*"`
	for s in $scripts
	do
		install -m 0755 $s ${D}${datadir}/cc33xx/
	done
}
