DESCRIPTION = "Provides zero copy access from user-space to packet dma channels via udma kernel driver"
LICENSE = "BSD-3-Clause"

COMPATIBLE_MACHINE = "keystone"
LIC_FILES_CHKSUM = "file://include/udma.h;startline=1;endline=39;md5=ba3c7f91f970afe073b759ced61d0f27"

BRANCH = "master"
SRC_URI = "git://git.ti.com/git/keystone-linux/udma.git;protocol=https;branch=${BRANCH}"
S = "${WORKDIR}/git"
# Commit corresponds to UDMA.01.00.00.00
SRCREV = "53d09fb0bc98c41c5eb43623097e363c497d6fd8"

PV = "01.00.00.00"

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${bindir}/udma_test"

do_install() {
	install -d ${D}${bindir}/
	install -d ${D}${libdir}/
	install -d ${D}${includedir}/
	install -c -m 755 ${S}/udma_test ${D}${bindir}/
	install -c -m 755 ${S}/libudma.a ${D}${libdir}/
	install -c -m 755 ${S}/libudma.so ${D}${libdir}/libudma.so.1.0.0
	cd ${D}${libdir}/
	ln -sf libudma.so.1.0.0 libudma.so.1
	ln -sf libudma.so.1.0.0 libudma.so
	install -c -m 755 ${S}/include/* ${D}${includedir}/
}

INSANE_SKIP_${PN} += "textrel"
