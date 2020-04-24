SUMMARY = "TI RTOS Video Driver Interface"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.fvid2"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://fvid2.h;beginline=1;endline=31;md5=83d177cf3df55c16b27ae4102b6ade9a"

COMPATIBLE_MACHINE = "k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

DEPENDS_append = " osal-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

FVID2_PACKAGE_BASE   = "${WORKDIR}/fvid2_base"
FVID2_ROOTPATH = "${FVID2_PACKAGE_BASE}/package/all/pdk_/packages/ti/drv/fvid2"

export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"
export PDK_FVID2_ROOT_PATH = "${FVID2_PACKAGE_BASE}/package/all/pdk_/packages"


BOARD_PACKAGE = ""
BOARD_PACKAGE_am65xx = "am65xx_evm"
BOARD_PACKAGE_j7 = "j721e_evm"

# HTML doc link params
PDK_COMP_LINK_TEXT = "FVID2"

do_configure() {
    rm -rf ${FVID2_PACKAGE_BASE}
    cd ${S}

    # remove any previous package
    rm -f ${FVID2_PACKAGE_BASE}

    # make the release package before building it
    make package BOARD=${BOARD_PACKAGE} DEST_ROOT=${FVID2_PACKAGE_BASE} PDK_FVID2_COMP_PATH=${S}
}

do_compile() {
    echo "fvid2 root path is ${FVID2_ROOTPATH}"
    cd ${FVID2_ROOTPATH}

    # Build am65xx libraries
    make clean lib xdc_meta LIMIT_SOCS="${TI_PDK_LIMIT_SOCS}" LIMIT_BOARDS="${TI_PDK_LIMIT_BOARDS}"  LIMIT_CORES="${TI_PDK_LIMIT_CORES}"

    #archive
    tar -cf fvid2.tar --exclude='*.tar' ./*
}

do_install() {
    cd ${FVID2_ROOTPATH}
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/fvid2
    find -name "*.tar" -exec tar xf {} --no-same-owner -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/fvid2 \;
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INSANE_SKIP_${PN} = "arch ldflags"
