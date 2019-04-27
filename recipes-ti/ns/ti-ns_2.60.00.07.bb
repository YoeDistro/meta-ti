require ti-ns.inc

PV = "2_60_00_07"
PR = "r0"
SRC_URI = "http://software-dl.ti.com/targetcontent/ns/${PV}/exports/ns_${PV}.zip;name=nszip"

LIC_FILES_CHKSUM = "file://manifest_ns_${PV}.html;md5=e15dc261b0ef14c6fbabba24abcde4f0"

SRC_URI[nszip.md5sum] = "7ad9f9fc79b4718bfa9b0042cb28d06f"
SRC_URI[nszip.sha256sum] = "8d2901e49ee72dde2e88370981e931c656e89efc7e0d5e3f775dde7d720e2b19"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
