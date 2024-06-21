SUMMARY = "Firmware files for use with TI cc33xx"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://LICENCE;md5=df68504cbd0a4da1643ebcfd5783dbc9"

SRCREV = "988ded3a7d4d0e46de62e9ebfcb685c502bd9edf"
SRC_URI = "git://git.ti.com/git/cc33xx-wlan/cc33xx-fw.git;protocol=https;branch=master"

PV = "1.7.0.120"

CLEANBROKEN = "1"

S = "${WORKDIR}/git"

do_compile[no_exec] = "1"

EXTRA_OEMAKE = "DEST_DIR=${D} BASE_LIB_DIR=${nonarch_base_libdir}"

do_install() {
    oe_runmake install
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/ti-connectivity/*"
