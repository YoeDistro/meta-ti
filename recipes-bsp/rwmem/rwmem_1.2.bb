SUMMARY = "A small tool to read/write memory"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS += "python3"

PV .= "+git${SRCPV}"

SRCREV_rwmem = "cc296c5366a35a4c4271cfa3cc9831f6f34bfd9d"
SRCREV_inih = "4b10c654051a86556dfdb634c891b6c3224c4109"
SRCREV_pybind11 = "9a19306fbf30642ca331d0ec88e7da54a96860f9"

SRCREV_FORMAT = "rwmem_inih_pybind11"

SRC_URI = "git://github.com/tomba/rwmem.git;protocol=https;name=rwmem \
           git://github.com/benhoyt/inih.git;protocol=https;name=inih;nobranch=1;destsuffix=git/ext/inih \
           git://github.com/pybind/pybind11.git;protocol=https;name=pybind11;nobranch=1;destsuffix=git/ext/pybind11 \
           "

S = "${WORKDIR}/git"

inherit cmake pkgconfig

do_install() {
	install -D -m 0755 ${B}/bin/rwmem ${D}${bindir}/rwmem
	install -D -m 0644 ${B}/lib/librwmem.a ${D}${libdir}/librwmem.a
}
