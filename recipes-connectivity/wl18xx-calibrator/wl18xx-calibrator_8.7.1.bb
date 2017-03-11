DESCRIPTION = "The calibrator utility for TI wireless solution based on wl18xx driver"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=4725015cb0be7be389cf06deeae3683d"

DEPENDS = "libnl"
RDEPENDS_${PN} = "wl18xx-fw"

#Tag: R8.7_SP1 (8.7.1)
SRCREV = "39542357111d1f24e866c2857d561a348c04cce4"
SRC_URI = "git://git.ti.com/wilink8-wlan/18xx-ti-utils.git"

S = "${WORKDIR}/git"

export CROSS_COMPILE = "${TARGET_PREFIX}"

EXTRA_OEMAKE = "CFLAGS="${CFLAGS} -I${STAGING_INCDIR}/libnl3/ -DCONFIG_LIBNL32 " \
		LDFLAGS="${LDFLAGS} -L${STAGING_LIBDIR}" \
		CC="${CC}" \
		NLVER=3"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 calibrator ${D}${bindir}/
}
