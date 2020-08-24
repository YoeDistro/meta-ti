require ti-ndk.inc

PE = "1"
PV = "3_61_01_01"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_ndk_${PV}.html;md5=871c716abcf52bac5a7c23154eaba3b6"

SRC_URI[ndkzip.md5sum] = "87f0494c9faee344fe93ae68e71ca189"
SRC_URI[ndkzip.sha256sum] = "3bfca50367432603ebc1d53d9185302bb4fa4558ac766696f086dc7a5a2ccd29"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"
