DESCRIPTION = "Debug Sub-System (DebugSS) driver for Keystone and DRA7xx devices"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=9d4316fe434ba450dca4da25348ca5a3"

# This package builds a kernel module, use kernel PR as base and append a local
MACHINE_KERNEL_PR:append = "d"
PR = "${MACHINE_KERNEL_PR}"
PV:append = "+git${SRCPV}"

S = "${WORKDIR}/git/debugss_module/debugss-mod"

inherit module

PLATFORM = ""
PLATFORM:dra7xx = "DRA7xx_PLATFORM"

EXTRA_OEMAKE = "'PLATFORM=${PLATFORM}' KVERSION=${KERNEL_VERSION} KERNEL_SRC=${STAGING_KERNEL_DIR}"

COMPATIBLE_MACHINE = "dra7xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

include dsptop.inc
