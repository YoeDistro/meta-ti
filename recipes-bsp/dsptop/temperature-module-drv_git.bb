DESCRIPTION = "Smart Reflex Sub-System (SRSS) module driver for Keystone devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=9d4316fe434ba450dca4da25348ca5a3"

# This package builds a kernel module, use kernel PR as base and append a local
MACHINE_KERNEL_PR_append = "a"
PR = "${MACHINE_KERNEL_PR}"
PV_append = "+git${SRCPV}"

S = "${WORKDIR}/git/temperature_module/temperature-mod"

inherit module

EXTRA_OEMAKE = "KVERSION=${KERNEL_VERSION} KERNEL_SRC=${STAGING_KERNEL_DIR}"

COMPATIBLE_MACHINE = "keystone"

include dsptop.inc
