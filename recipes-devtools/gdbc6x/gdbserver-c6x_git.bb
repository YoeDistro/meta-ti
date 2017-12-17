DESCRIPTION = "TI gdb-server to be used with c6xgdb."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://../debian/copyright;md5=82c616b6c8f9c11c46feaaf8f9a11495"

DEPENDS = "ti-xdais ti-framework-components ti-cgt6x-native ti-sysbios ti-xdctools-native"

PR = "${INC_PR}.0"

S = "${WORKDIR}/git/gdbserver-c6x/src"

PLATFORM = ""
PLATFORM_dra7xx = "DRA7xx_PLATFORM"
PLATFORM_keystone = "KEYSTONE_PLATFORM"

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

COMPATIBLE_MACHINE = "dra7xx|keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN}-dev += "\
    ${datadir}/ti/gdbc6x \
"

include gdbc6x.inc

require recipes-ti/includes/ti-paths.inc

ALLOW_EMPTY_${PN} = "1"

PARALLEL_MAKE = ""
