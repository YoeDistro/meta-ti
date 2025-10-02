SUMMARY = "Debug Sub-System (DebugSS) driver for Keystone and DRA7xx devices"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=9d4316fe434ba450dca4da25348ca5a3"

PV:append = "+git"

S = "${WORKDIR}/git/debugss_module/debugss-mod"

inherit module

COMPATIBLE_MACHINE = "omap-a15"

EXTRA_OEMAKE = "PLATFORM=DRA7xx_PLATFORM KVERSION=${KERNEL_VERSION} KERNEL_SRC=${STAGING_KERNEL_DIR}"

include dsptop.inc

SRC_URI += "\
    file://0001-debugss_kmodule-Add-include-for-mod_devicetable.h.patch \
    file://0002-debugss_kmodule-kernel-6.11-changed-return-value-for.patch \
"
