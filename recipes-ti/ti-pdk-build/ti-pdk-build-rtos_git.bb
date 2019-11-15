DESCRIPTION = "Package containing PDK makefile infrastructure"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;beginline=1;endline=31;md5=92ab6f5e10f1502081c6080207e57ec1"

require recipes-ti/includes/ti-paths.inc

PR = "r0"

inherit ti-pdk-fetch

TI_PDK_COMP = "ti.build"

PE = "1"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PARALLEL_MAKE = ""
CLEANBROKEN = "1"

do_compile() {
    :
}

EXTRA_OEMAKE = "-f make_install LIMIT_SOCS="${TI_PDK_LIMIT_SOCS}" PDK_INSTALL_DIR="${D}${PDK_INSTALL_DIR_RECIPE}""

do_install() {
    oe_runmake
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages/*"
INSANE_SKIP_${PN} += " file-rdeps"
