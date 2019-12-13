SUMMARY = "TI RTOS low level driver for UDMA"
DESCRIPTION = "TI RTOS low level driver for Universal DMA module "

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.udma"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://udma.h;beginline=1;endline=31;md5=83d177cf3df55c16b27ae4102b6ade9a"

COMPATIBLE_MACHINE = "k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r1"

DEPENDS_append = " osal-rtos \
                   sciclient-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

UDMA_PACKAGE_BASE   = "${WORKDIR}/udma_base"
UDMA_LLD_ROOTPATH = "${UDMA_PACKAGE_BASE}/package/all/pdk_/packages/ti/drv/udma"

export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"
export PDK_UDMA_ROOT_PATH = "${UDMA_PACKAGE_BASE}/package/all/pdk_/packages"

TI_PDK_LIMIT_BOARDS_am65xx = "am65xx_evm"


# HTML doc link params
PDK_COMP_LINK_TEXT = "UDMA LLD"

do_configure() {
    # remove any previous package
    rm -rf ${UDMA_PACKAGE_BASE}

    cd ${S}

    # make the release package before building it
    make package BOARD="${TI_PDK_LIMIT_BOARDS}" DEST_ROOT=${UDMA_PACKAGE_BASE} PDK_UDMA_COMP_PATH=${S}
}

do_compile() {
    cd ${UDMA_LLD_ROOTPATH}

    # Build am65xx libraries
    make clean lib xdc_meta doxygen LIMIT_SOCS="${TI_PDK_LIMIT_SOCS}" LIMIT_BOARDS="${TI_PDK_LIMIT_BOARDS}" LIMIT_CORES="${TI_PDK_LIMIT_CORES}"

    #archive
    tar -cf udma_lld.tar --exclude='*.tar' ./*
}

do_install() {
    cd ${UDMA_LLD_ROOTPATH}
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/udma
    find -name "*.tar" -exec tar xf {} --no-same-owner -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/udma \;
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INSANE_SKIP_${PN} = "arch ldflags"
