SUMMARY = "OpenNTPD is an implementation of the Network Time Protocol."
DESCRIPTION = "OpenNTPD is a FREE, easy to use implementation of the Network Time Protocol."
HOMEPAGE = "http://www.openntpd.org/"
SECTION = "console/network"
LICENSE = "BSD-3-Clause & ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=fe922aad2b6ad1c359cf2adfdaaab1b6"

PR = "r0"

SRC_URI = "https://cdn.openbsd.org/pub/OpenBSD/OpenNTPD/${P}.tar.gz"
SRC_URI[md5sum] = "7c68ce2627b2ea13f2b95db1ef4fcfc5"
SRC_URI[sha256sum] = "8582db838a399153d4a17f2a76518b638cc3020f58028575bf54127518f55a46"

inherit autotools

LDFLAGS += "-lrt"
EXTRA_OECONF += "LDFLAGS="${LDFLAGS}" CFLAGS="${CFLAGS}"\
                 --with-privsep-user=root"

do_install:append() {
    # don't install empty /var/run and /run to avoid conflict with base-files
    rm -rf ${D}${localstatedir}/run
    rm -rf ${D}/run
}
