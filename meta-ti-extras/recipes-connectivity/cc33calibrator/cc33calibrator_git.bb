SUMMARY = "The calibrator utility for TI wireless solution based on cc33xx driver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d5fc448a36efe573623542dcb989afc4"

SRCREV = "89d7e55b40972cbfc84f0734c35a7a6b4f7f5b31"
SRC_URI = "git://git.ti.com/git/cc33xx-wlan/cc33xx-utils.git;branch=master;protocol=https"

PV = "1.0.0.50"

DEPENDS = "libnl"

S = "${WORKDIR}/git/cc33calibrator"

export CROSS_COMPILE = "${TARGET_PREFIX}"

EXTRA_OEMAKE = "CFLAGS="${CFLAGS} -I${STAGING_INCDIR}/libnl3/ -DCONFIG_LIBNL32 " \
		LDFLAGS="${LDFLAGS} -L${STAGING_LIBDIR}" \
		CC="${CC}" \
		NLVER=3"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 cc33calibrator ${D}${bindir}/
}
