DESCRIPTION = "Interface for GDB to commincate witha TI C66X DSP"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=75859989545e37968a99b631ef42722e"

# This package builds a kernel module, use kernel PR as base and append a local
MACHINE_KERNEL_PR_append = "a"
PR = "${MACHINE_KERNEL_PR}"
PV_append = "+git${SRCPV}"

S = "${WORKDIR}/git/kernel_module/gdbproxy-mod"

inherit module

PLATFORM = ""
PLATFORM_dra7xx = "DRA7xx_PLATFORM"
PLATFORM_keystone = "KEYSTONE_PLATFORM"

EXTRA_OEMAKE = "PLATFORM=${PLATFORM}"

# The following is to prevent an unused configure.ac from erroneously
# triggering the QA check for gettext.
EXTRA_OECONF = "--disable-nls"
do_configure() {
    :
}

COMPATIBLE_MACHINE = "dra7xx|keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

include gdbc6x.inc

module_autoload_gdbserverproxy = "gdbserverproxy"
