SUMMARY = "Test cases for DMA-Heap framework"
HOMEPAGE = "https://github.com/glneo/dma-heap-tests"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4c3b4cb87cfc8ccba69d579fbd7b0fb3"

PV = "1.0"

BRANCH = "master"
SRC_URI = "git://github.com/glneo/dma-heap-tests.git;protocol=https;branch=${BRANCH}"
SRCREV = "230b406e97c12be5f930125a432d0dfa0316aa39"

DEPENDS = "googletest"

inherit cmake pkgconfig
