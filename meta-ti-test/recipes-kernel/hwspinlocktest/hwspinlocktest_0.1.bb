SUMMARY = "Build hwspinlock test as an external Linux kernel module"
DESCRIPTION = "${SUMMARY}"
LICENSE = "GPL-2.0-only | BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bfa02c83df161e37647ee23a2c7eacd4"

inherit module

SRC_URI = "\
    git://github.com/TexasInstruments/omap-hwspinlock-test;protocol=https;branch=master \
    file://0001-Fix-Makefile-for-to-build-with-yocto.patch \
"

SRCREV = "1d154fbe5b56ef81045763f574c2098a35d6a8b8"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-hwspinlocktest"
