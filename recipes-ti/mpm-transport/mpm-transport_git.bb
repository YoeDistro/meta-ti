DESCRIPTION = "Multiproc transport layer for KeyStone devices: Provide basic transport layer for moving data between different processing nodes"
HOMEPAGE = "http://git.ti.com/cgit/cgit.cgi/keystone-linux/mpm-transport.git"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=122b7757f366f3f6fe11988715258fc9"
COMPATIBLE_MACHINE = "keystone"
DEPENDS = "common-csl-ip hyplnk-lld edma3-lld mmap-lld cmem rm-lld qmss-lld cppi-lld srio-lld"

SRC_URI = "git://git.ti.com/keystone-linux/mpm-transport.git;protocol=git;branch=${BRANCH}"

BRANCH = "master"
# This commit corresponds to tag DEV.MPM-TRANSPORT-01.00.08.03
SRCREV = "f6c5426d68c2be832903b6d215370c94e134167f"

PV = "1.0.8.3"
PR = "r1"

S = "${WORKDIR}/git"

PACKAGES =+ "${PN}-test"
FILES_${PN}-test = "${bindir}/mpm_transport_test.out \
					${bindir}/mpm_transport_hyplnk_loopback.out \
					${bindir}/mpm_transport_hyplnk_remote.out \
					${bindir}/mpm_transport_hyplnk_loopback_dma.out \
					${bindir}/mpm_transport_hyplnk_loopback64.out \
					${bindir}/mpm_transport_qmss_arm_mt.out \
					${bindir}/mpm_transport_srio_arm_mt.out"

do_compile () {
	cd ${S}
	make PDK_INSTALL_PATH=${STAGING_INCDIR}
}

do_install() {
	make installbin BASE_DIR=${S} INSTALL_BIN_BASE_DIR=${D}/${bindir}

	install -d ${D}${includedir}/
	install -c -m 755 ${S}/include/* ${D}${includedir}/

	install -d ${D}${libdir}/
	cp  -a ${S}/lib/* ${D}${libdir}/

	install -d ${D}${sysconfdir}/mpm/
	install -c -m 755 ${S}/scripts/mpm_config.json ${D}${sysconfdir}/mpm/mpm_config.json
}
