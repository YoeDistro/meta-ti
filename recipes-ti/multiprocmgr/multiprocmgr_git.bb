DESCRIPTION = "TI Multiproc Manager for KeyStone II"
HOMEPAGE = "http://gtgit01.gt.design.ti.com/git/?p=projects/multiprocmgr.git;a=summary"
LICENSE = "BSD & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fce208c498eb9669223724dc9c1d8fe4"
SECTION = "console"
COMPATIBLE_MACHINE = "keystone"

PR = "r1"

BRANCH ?= "master"
SRCREV = "8a97fb5c2c06d5f02d30106629f27fe0ca8a4f95"

SRC_URI = "git://gtgit01.gt.design.ti.com/git/projects/multiprocmgr.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/mpmsrv ${D}${bindir}/mpmsrv
}
