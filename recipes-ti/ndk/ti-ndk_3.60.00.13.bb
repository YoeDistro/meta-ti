require ti-ndk.inc

PV = "3_60_00_13"
PR = "r0"

LIC_FILES_CHKSUM = "file://manifest_ndk_${PV}.html;md5=6d1d16547344a4418e565586fa45b458"

SRC_URI[ndkzip.md5sum] = "cc1bbe67b46bd02c4a423814360a4e00"
SRC_URI[ndkzip.sha256sum] = "86407d4b423419e020b38a44d1a61705fdec0ae0d5e35eb07b99fcfe5ceee7dd"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"
