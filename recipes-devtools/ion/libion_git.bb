SUMMARY = "ION Userspace Memory Allocator Library"
HOMEPAGE = "https://github.com/glneo/libion"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PV = "1.0.0"

BRANCH = "master"
SRC_URI = "git://github.com/glneo/libion.git;protocol=https;branch=${BRANCH}"
SRCREV = "7e138fc31febbb67734792c1a911439bfd6d23eb"

S = "${WORKDIR}/git"

inherit cmake pkgconfig
