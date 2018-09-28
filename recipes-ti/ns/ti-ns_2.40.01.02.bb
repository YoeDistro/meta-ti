require ti-ns.inc

PV = "2_40_01_02"
PR = "r0"
SRC_URI = "http://software-dl.ti.com/targetcontent/ns/${PV}/exports/ns_${PV}.zip;name=nszip"

LIC_FILES_CHKSUM = "file://manifest_ns_${PV}.html;md5=29e319bb254ad52bdaebf8f6387c2f85"

SRC_URI[nszip.md5sum] = "03146829fc7cf1e7b2acbeb6c1f772e9"
SRC_URI[nszip.sha256sum] = "e41f254d9db341ad3efea7f6f59df46802769eba3e2f03b6d2a53d186de18687"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
