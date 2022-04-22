SUMMARY = "Diagnostic tool for TI K3 processors"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://common/k3conf.c;beginline=1;endline=34;md5=7154c0ffcd418064ffa528e34e70ca9d"

PV = "0.2+git${SRCPV}"

COMPATIBLE_MACHINE = "k3"

BRANCH ?= "master"
SRCREV = "966a5695b73bc53039ca60d196b77de0640088d4"

SRC_URI = "git://git.ti.com/k3conf/k3conf.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_compile () {
    oe_runmake CC="${CC}" CROSS_COMPILE=${TARGET_PREFIX} all
}

do_install () {
    install -d ${D}${bindir}
    install ${S}/k3conf ${D}${bindir}
}
