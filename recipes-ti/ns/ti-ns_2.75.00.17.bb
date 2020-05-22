require ti-ns.inc

PV = "2_75_00_17"

SRC_URI = "http://software-dl.ti.com/targetcontent/ns/${PV}/exports/ns_${PV}.zip;name=nszip"

LIC_FILES_CHKSUM = "file://manifest_ns_${PV}.html;md5=d6a172fb0625f2ddf7d0cbfded273898"

SRC_URI[nszip.sha256sum] = "612cbaf9b91c978b39cf370f7ef721596c36141575ca74da76b025beef4d9a03"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
