DESCRIPTION = "Firmware for DSP for an example application called copycodectest"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://src/ti/framework/dce/dce.c;startline=1;endline=31;md5=2c6e9aba6ed75f22b1a2b7544b1c809d"

COMPATIBLE_MACHINE = "dra7xx"

SRC_URI = "git://git.ti.com/glsdk/dspdce.git;protocol=git"

SRCREV = "de6e599f067b25c46cc0c8f74a22cc3b8aafbae8"

PV = "1.00.00.07"

S = "${WORKDIR}/git"

require recipes-ti/includes/ti-paths.inc

PR = "r4"
inherit update-alternatives

DEPENDS = "ti-xdctools-native ti-sysbios ti-codec-engine ti-framework-components ti-xdais ti-ipc-rtos ti-osal ti-cgt6x-native"

export HWVERSION = "ES10"
export BIOSTOOLSROOT = "${STAGING_DIR_TARGET}/usr/share/ti"

export XDCVERSION = "ti-xdctools-tree"
export BIOSVERSION = "ti-sysbios-tree"
export IPCVERSION = "ti-ipc-tree"
export CEVERSION = "ti-codec-engine-tree"
export FCVERSION = "ti-framework-components-tree"
export XDAISVERSION = "ti-xdais-tree"
export OSALVERSION = "ti-osal-tree"

export IPCSRC = "${STAGING_DIR_TARGET}/usr/share/ti/ti-ipc-tree"
export C66XCGTOOLSPATH = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"

do_configure() {
    cd ${S}
    make unconfig
    make vayu_config
}

do_compile() {
    cd ${S}
    make dspbin
}

TARGET = "dra7-dsp1-fw.xe66"

do_install() {
    install -d ${D}${base_libdir}/firmware
    install -m 0644 ${S}/dra7xx-c66x-dsp.xe66 ${D}${base_libdir}/firmware/${TARGET}.${BPN}
}

ALTERNATIVE_${PN} = "dra7-dsp1-fw.xe66"
ALTERNATIVE_LINK_NAME[dra7-dsp1-fw.xe66] = "${base_libdir}/firmware/${TARGET}"
ALTERNATIVE_TARGET[dra7-dsp1-fw.xe66] = "${base_libdir}/firmware/${TARGET}.${BPN}"
ALTERNATIVE_PRIORITY = "10"

INSANE_SKIP_${PN} = "arch"

FILES_${PN} += "${base_libdir}/firmware/*"
