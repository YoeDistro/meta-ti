SUMMARY = "Scripts and configuration files for TI wireless drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=21;md5=b8d6a0865f50159bf5c0d175d1f4a705"

# Tag: R8.8.1
SRCREV = "8bcee075061527d61f26d6d506aebdfd496037d9"
BRANCH = "sitara-scripts"
SRC_URI = "git://git.ti.com/git/wilink8-wlan/wl18xx-target-scripts.git;protocol=https;branch=${BRANCH} \
file://0001-print_stat.sh-replace-system-bin-sh-with-bin-sh.patch \
"

PR = "r1"

FILES:${PN} += "${datadir}/wl18xx/"

do_install() {
	install -d ${D}${datadir}/wl18xx/

	scripts=`find ./* -type f -name "*.*"`
	for s in $scripts
	do
		install -m 0755 $s ${D}${datadir}/wl18xx/
	done
}
