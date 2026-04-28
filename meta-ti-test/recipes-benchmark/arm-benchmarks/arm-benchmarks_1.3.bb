SUMMARY = "ARM Benchmarks"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_benchmarks/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=7aefb5e1cffc7b6a3ef18b803f957922"
SECTION = "system"

PR = "r13"

BRANCH ?= "master"
SRCREV = "fefaf9bdd6f36ab0cebaab8dbf34e5beb50f471c"

SRC_URI = "git://git.ti.com/git/apps/arm_benchmarks.git;protocol=https;branch=${BRANCH}"

do_compile() {
	export CROSS_COMPILE=${TARGET_PREFIX}
	export CFLAGS='${TARGET_CC_ARCH} -std=gnu11'
	# build the release version
	oe_runmake release CC="${CC}"
}
do_install() {

	oe_runmake DESTDIR=${D} install
}
