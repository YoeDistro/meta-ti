DESCRIPTION = "Interface for GDB to commincate witha TI C66X DSP"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=75859989545e37968a99b631ef42722e"

# This package builds a kernel module, use kernel PR as base and append a local
MACHINE_KERNEL_PR:append = "b"
PR = "${MACHINE_KERNEL_PR}"
PV:append = "+git${SRCPV}"

SRC_URI:append = " file://0001-Support-Linux-kernels-v5.15.patch;patchdir=../.."

S = "${WORKDIR}/git/kernel_module/gdbproxy-mod"

inherit module

PLATFORM = ""
PLATFORM:dra7xx = "DRA7xx_PLATFORM"

EXTRA_OEMAKE = "PLATFORM=${PLATFORM} KVERSION=${KERNEL_VERSION} KERNEL_SRC=${STAGING_KERNEL_DIR}"

# The following is to prevent an unused configure.ac from erroneously
# triggering the QA check for gettext.
EXTRA_OECONF = "--disable-nls"
do_configure() {
    :
}

COMPATIBLE_MACHINE = "dra7xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

include gdbc6x.inc

KERNEL_MODULE_AUTOLOAD += "gdbserverproxy"
