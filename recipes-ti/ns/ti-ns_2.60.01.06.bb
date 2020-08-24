require ti-ns.inc

PE = "1"
PV = "2_60_01_06"
PR = "r0"
SRC_URI = "http://software-dl.ti.com/targetcontent/ns/${PV}/exports/ns_${PV}.zip;name=nszip"

LIC_FILES_CHKSUM = "file://manifest_ns_${PV}.html;md5=e15dc261b0ef14c6fbabba24abcde4f0"

SRC_URI[nszip.md5sum] = "b78d86423e9c4d148be5fba5903cc2c7"
SRC_URI[nszip.sha256sum] = "f66b2fda6ab02585020e67d84897305209f3e141847581a214ccf123c3f15f00"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
