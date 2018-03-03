DESCRIPTION = "Multiproc transport layer for KeyStone devices: Provide basic transport layer for moving data between different processing nodes"
HOMEPAGE = "http://git.ti.com/cgit/cgit.cgi/keystone-linux/mpm-transport.git"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=122b7757f366f3f6fe11988715258fc9"
COMPATIBLE_MACHINE = "keystone"

DEPENDS = "common-csl-ip edma3-lld mmap-lld cmem rm-lld qmss-lld cppi-lld uio-module-drv"
DEPENDS_append_k2hk = " hyplnk-lld srio-lld"
DEPENDS_append_k2e = " hyplnk-lld"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://git.ti.com/keystone-linux/mpm-transport.git;protocol=git;branch=${BRANCH}"

BRANCH = "master"
# This commit corresponds to tag DEV.MPM-TRANSPORT-02.00.04.00
SRCREV = "e0e44fe55bef7ddf89f7ff3574f07fa906e72a37"

PV = "2.0.4.0"
PR = "r0"

EXTRA_OEMAKE = "PDK_INSTALL_PATH=${STAGING_INCDIR}"
EXTRA_OEMAKE_append_k2hk += "HYPLNK_TRANSPORT=true SRIO_TRANSPORT=true"
EXTRA_OEMAKE_append_k2e += "HYPLNK_TRANSPORT=true"
INSANE_SKIP_${PN} += "ldflags"

S = "${WORKDIR}/git"

PACKAGES =+ "${PN}-test"
FILES_${PN}-test = "${bindir}/mpm_transport_test.out \
					${bindir}/mpm_transport_hyplnk_loopback.out \
					${bindir}/mpm_transport_hyplnk_remote.out \
					${bindir}/mpm_transport_hyplnk_loopback_dma.out \
					${bindir}/mpm_transport_hyplnk_loopback64.out \
					${bindir}/mpm_transport_qmss_arm_mt.out \
					${bindir}/mpm_transport_srio_arm_mt.out"

do_install() {
	oe_runmake installbin BASE_DIR=${S} INSTALL_BIN_BASE_DIR=${D}/${bindir}

	install -d ${D}${includedir}/
	install -c -m 644 ${S}/include/* ${D}${includedir}/

	install -d ${D}${libdir}/
	cp  -a --preserve=mode,timestamps --no-preserve=ownership ${S}/lib/* ${D}${libdir}/

	install -d ${D}${sysconfdir}/mpm/
	install -c -m 644 ${S}/scripts/mpm_config.json ${D}${sysconfdir}/mpm/mpm_config.json
}
