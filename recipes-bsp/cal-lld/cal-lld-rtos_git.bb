SUMMARY = "TI RTOS low level driver for CAL"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.cal"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://cal.h;beginline=1;endline=31;md5=83d177cf3df55c16b27ae4102b6ade9a"

COMPATIBLE_MACHINE = "k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"


PR = "r0"

DEPENDS_append = " osal-rtos \
                   fvid2-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

CAL_PACKAGE_BASE   = "${WORKDIR}/cal_base"
CAL_LLD_ROOTPATH = "${CAL_PACKAGE_BASE}/package/all/pdk_/packages/ti/drv/cal"

export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"
export PDK_CAL_ROOT_PATH = "${CAL_PACKAGE_BASE}/package/all/pdk_/packages"

LIMSOCS = ""
LIMSOCS_am65xx-evm = "am65xx"
LIMSOCS_am65xx-hs-evm = "am65xx"

LIMBOARDS = ""
LIMBOARDS_am65xx-evm = "am65xx_evm"
LIMBOARDS_am65xx-hs-evm = "am65xx_evm"

BOARD_PACKAGE = ""
BOARD_PACKAGE_am65xx-evm = "am65xx_evm"
BOARD_PACKAGE_am65xx-hs-evm = "am65xx_evm"

# HTML doc link params
PDK_COMP_LINK_TEXT = "CAL"

do_configure() {
    rm -rf ${CAL_PACKAGE_BASE}
    cd ${S}

    # remove any previous package
    rm -f ${CAL_PACKAGE_BASE}

    # make the release package before building it
    make package BOARD=${BOARD_PACKAGE} DEST_ROOT=${CAL_PACKAGE_BASE} PDK_CAL_COMP_PATH=${S}
}

do_compile() {
    echo "cal lld root path is ${CAL_LLD_ROOTPATH}"
    cd ${CAL_LLD_ROOTPATH}

    # Build am65xx libraries
    make clean lib xdc_meta doxygen LIMIT_SOCS="${LIMSOCS}" LIMIT_BOARDS="${LIMBOARDS}"

    #archive
    tar -cf cal_lld.tar --exclude='*.tar' ./*
}

do_install() {
    cd ${CAL_LLD_ROOTPATH}
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/cal
    find -name "*.tar" -exec tar xf {} --no-same-owner -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/cal \;
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INSANE_SKIP_${PN} = "arch ldflags"
