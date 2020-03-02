LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://osal.h;beginline=1;endline=31;md5=6fbde6922f8bca1e6cd371d3c2faa4f9"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit ti-pdk-fetch

TI_PDK_COMP = "ti.osal"

PE = "1"

INC_PR = "r0"

INHIBIT_PACKAGE_STRIP_FILES = "${PKGD}${libdir}/libosal.a"
