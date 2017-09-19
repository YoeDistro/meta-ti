require ti-ndk.inc

PV = "2_25_01_11"
PR = "r0"

LIC_FILES_CHKSUM = "file://ndk_${PV}_manifest.html;md5=80a8d9b76cf700929d3f5b6df09052d6"

SRC_URI[ndkzip.md5sum] = "e42bfcc8c3a6d183d60f26ec01ccc79b"
SRC_URI[ndkzip.sha256sum] = "a15bc9b570dc0628dd8b8c3665037a162610a6f86c5f17ebb6a329d3db60f071"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
