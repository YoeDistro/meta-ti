DESCRIPTION = "TI Smart Reflex temperature control application for KeyStone II"
HOMEPAGE = "http://git.ti.com/cgit/cgit.cgi/keystone-linux/srss-tc.git"
LICENSE = "BSD & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2c17cd1dc60dc06a4c4f2a2c45472a51"
COMPATIBLE_MACHINE = "keystone"

PV = "1.0.0.1"

BRANCH = "master"
# This commit corresponds to tag DEV.SRSS-TC-01.00.00.01A
SRCREV = "107cd1433ef5785e5647ef086a4cc77566506604"

SRC_URI = "git://git.ti.com/keystone-linux/srss-tc.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

SRSS_BIN_NAME = "srss_tc.out"
INITSCRIPT_NAME = "run_srss_tc.sh"
INITSCRIPT_PARAMS = "defaults 10"

inherit update-rc.d
LDFLAGS += "-lrt"
CFLAGS += "${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/src/${SRSS_BIN_NAME} ${D}${bindir}/${SRSS_BIN_NAME}

	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${S}/scripts/${INITSCRIPT_NAME} ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}
