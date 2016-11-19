SUMMARY = "A small tool to read/write memory"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "54904b8873b265d8f1faf82617c2f04814c102c6"
SRC_URI = "git://github.com/tomba/rwmem.git"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 rwmem ${D}${bindir}
}
