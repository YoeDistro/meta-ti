DESCRIPTION = "TI gdb-server to be used with c6xgdb."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://../debian/copyright;md5=82c616b6c8f9c11c46feaaf8f9a11495"

DEPENDS = "ti-xdais ti-framework-components ti-cgt6x-native ti-sysbios ti-xdctools-native"

PR = "${INC_PR}.0"

S = "${WORKDIR}/git/gdbserver-c6x/src"

PLATFORM = ""
PLATFORM:dra7xx = "DRA7xx_PLATFORM"

EXTRA_OEMAKE = "PLATFORM=${PLATFORM}"

export XDAIS_DIR = "${XDAIS_INSTALL_DIR}"
export FC_DIR = "${FC_INSTALL_DIR}"
export TI_CGT_INSTALL = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export BIOS_DIR = "${SYSBIOS_INSTALL_DIR}" 
export XDC_DIR = "${XDC_INSTALL_DIR}"

# The following is to prevent an unused configure.ac from erroneously
# triggering the QA check for gettext.
EXTRA_OECONF = "--disable-nls"
do_configure() {
    :
}

do_install() {
    install -d ${D}${datadir}/ti/gdbc6x/include
    install -d ${D}${datadir}/ti/gdbc6x/lib
    cp -f ../include/* ${D}${datadir}/ti/gdbc6x/include
    cp -f ../lib/* ${D}${datadir}/ti/gdbc6x/lib
}

COMPATIBLE_MACHINE = "dra7xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN}-dev += "\
    ${datadir}/ti/gdbc6x \
"

include gdbc6x.inc

require recipes-ti/includes/ti-paths.inc

ALLOW_EMPTY:${PN} = "1"

PARALLEL_MAKE = ""

# Disable the "buildpaths" check while we figure out how we are
# going to address this issue.
#
# The ti-cgt6x compiler is a custom TI compiler for the TI C6000
# Digital Signal Processor(DSP) platform.  It does not currently
# support reproducible builds and is provided via a binary blob
# download that we cannot patch in the recipe to address the
# issue.
INSANE_SKIP:${PN}-dev += "buildpaths"
