SUMMARY = "Test cases for ION"
HOMEPAGE = "https://github.com/glneo/ion-tests"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/ion_test.c;beginline=1;endline=15;md5=b225db900869a4cd31461606e55a3ec5"

PV = "1.0"

BRANCH = "master"
SRC_URI = "git://github.com/glneo/ion-tests.git;protocol=git;branch=${BRANCH} \
	file://0001-CMakeLists.txt-disable-phys-addr-for-now.patch \
	file://0001-map_test-Replace-PAGE_SIZE-with-local-variable-name.patch \
"
SRCREV = "70d730cebca29e6fd37b21d9beac82ae645f3900"

DEPENDS = "libion gtest"

S = "${WORKDIR}/git"

inherit cmake pkgconfig
