
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

require u-boot-ti.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

PR = "r34"

BRANCH = "ti-u-boot-2021.01"

SRCREV = "3a5205e9803809f18edc59a40fd9df470f98a00f"

SRC_URI += "file://0001-configs-Update-to-support-oe-core-fitImage-naming.patch"

