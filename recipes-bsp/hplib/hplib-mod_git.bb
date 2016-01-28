DESCRIPTION = "TI High Performance Library kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/runtime/hplib/module/COPYING.txt;md5=c1c4d3453cddc5b7f0ce84a277e66779"

DEPENDS = "hplib"

include hplib.inc

# This package builds a kernel module, use kernel PR as base and append a local
MACHINE_KERNEL_PR_append = "b"
PR = "${MACHINE_KERNEL_PR}"

S ="${WORKDIR}/git/ti/runtime/hplib/module"
EXTRA_OEMAKE = "KDIR=${STAGING_KERNEL_DIR} PDK_INSTALL_PATH=${STAGING_INCDIR}"

inherit module

do_install () {
	oe_runmake install INSTALL_MOD_PATH="${D}"
}

KERNEL_MODULE_AUTOLOAD += "hplibmod"
