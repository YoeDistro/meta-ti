SUMMARY = "ION Userspace Memory Allocator Library"
HOMEPAGE = "https://github.com/glneo/libion"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PV = "1.0.0"

BRANCH = "master"
SRC_URI = "git://github.com/glneo/libion.git;protocol=git;branch=${BRANCH} \
           file://0001-cmake-Use-GNUInstallDirs-module-to-detect-install-pa.patch \
          "
SRCREV = "813bd63e2f2118e17cb1c5d38505a1e572a2381c"

S = "${WORKDIR}/git"

inherit cmake pkgconfig
