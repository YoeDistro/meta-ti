SUMMARY = "Diagnostic tool for TI K3 processors"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://common/k3conf.c;beginline=1;endline=34;md5=7154c0ffcd418064ffa528e34e70ca9d"

PV = "0.2+git${SRCPV}"

COMPATIBLE_MACHINE = "k3"

BRANCH ?= "master"
SRCREV = "79f007cd462384ce22e750e9002b714878f56892"

SRC_URI = "git://git.ti.com/k3conf/k3conf.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_compile () {
    oe_runmake CC="${CC}" CROSS_COMPILE=${TARGET_PREFIX} all
}

do_install () {
    install -d ${D}${bindir}
    install ${S}/k3conf ${D}${bindir}
}
