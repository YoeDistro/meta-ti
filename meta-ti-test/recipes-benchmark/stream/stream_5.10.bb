SUMMARY = "Stream Benchmark"
HOMEPAGE = "http://www.cs.virginia.edu/stream/"
LICENSE = "Stream_Benchmark_License"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=bca8cbe07976fe64c8946378d08314b0"
SECTION = "system"

PR = "r0"

BRANCH ?= "master"
SRCREV = "b66f2bab5d6d0b35732ef8406ae03873725a3306"

SRC_URI = "git://git.ti.com/git/arago-project-org/stream.git;protocol=https;branch=${BRANCH}"

PACKAGES =+ "${PN}-openmp"

do_compile() {
	oe_runmake CC="${CC}"
}

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/stream_c ${D}/${bindir}/
	install -m 0755 ${S}/stream_c_openmp ${D}/${bindir}/
}

FILES:${PN}-openmp = "${bindir}/stream_c_openmp"
