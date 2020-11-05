SUMMARY = "Diagnostic tool for TI K3 processors"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://common/k3conf.c;beginline=1;endline=34;md5=37f4e460bd8501c6f02ce71f02bc7ccf"

PV = "0.1+git${SRCPV}"

COMPATIBLE_MACHINE = "k3"

BRANCH ?= "master"
SRCREV = "246eb4092d7d111ce10e0a310b9bde678b97ab36"

SRC_URI = "git://git.ti.com/k3conf/k3conf.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_compile () {
    oe_runmake CC="${CC}" CROSS_COMPILE=${TARGET_PREFIX} all
}

do_install () {
    install -d ${D}${bindir}
    install ${S}/k3conf ${D}${bindir}
}
