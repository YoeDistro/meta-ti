SUMMARY = "Diagnostic tool for TI OMAP processors"
HOMEPAGE = "https://github.com/omapconf/omapconf"

LICENSE = "GPL-2.0-only | BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=205c83c4e2242a765acb923fc766e914"

PV = "1.75+git"

COMPATIBLE_MACHINE = "ti33x|ti43x|am57xx"

BRANCH ?= "master"
SRCREV = "ff07b6992bacb1e1586c72b7d2be469caee4a347"

SRC_URI = "git://github.com/omapconf/omapconf.git;protocol=https;branch=${BRANCH}"

SRC_URI += "file://0001-Use-proper-definition-of-boolean-type.patch"

do_compile () {
    oe_runmake CC="${CC}" all
}

do_install () {
    oe_runmake DESTDIR=${D}${bindir} install
}
