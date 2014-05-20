DESCRIPTION = "TI Inter Process Ccommunication (IPC) Mechanisms (for Uni- and Multi- Processor Configurations)"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ipc/index.html"
SECTION = "devel"
LICENSE = "BSD" 
LIC_FILES_CHKSUM = "file://ipc_${PV}_manifest.html;md5=573f5a5c0448d28bf8db35f438244d7f"


require ../includes/ti-paths.inc
require ../includes/ti-staging.inc
require ../includes/ti-eula-unpack.inc

PV = "1_24_00_16"

S = "${WORKDIR}/ipc_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ipc/${PV}/exports/ipc_setuplinux_${PV}.bin;name=ipcbin \
           file://ipc.pc"

DEPENDS = "ti-sysbios ti-cgt6x ti-xdctools"

BINFILE="ipc_setuplinux_${PV}.bin"
TI_BIN_UNPK_CMDS="Y: q:workdir:Y"

do_configure() {
    sed -i -e s:@VERSION@:${PV}:g ${WORKDIR}/ipc.pc
    
    cp ${WORKDIR}/ipc.pc ${S}
}

do_install() {
    install -d ${D}${IPC_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${IPC_INSTALL_DIR_RECIPE}
   
    install -d ${D}/${libdir}/pkgconfig
    install ${S}/ipc.pc ${D}/${libdir}/pkgconfig/
}

FILES_${PN}-dev = "${libdir}/*"

SRC_URI[ipcbin.md5sum] = "d2f7cc82310d555ca039dcd353bd94c5"
SRC_URI[ipcbin.sha256sum] = "96df7835e920c7dabcd63d30ec8bc2aea7cd0c6197c9fb9ca0f8a9b681aa5396"
