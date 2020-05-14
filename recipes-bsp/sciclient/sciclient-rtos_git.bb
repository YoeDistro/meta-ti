SUMMARY = "TI RTOS low level driver for SCICLIENT"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.sciclient"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=99d7639a81c1ba1f16fd070a928ffddb"

COMPATIBLE_MACHINE = "k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r1"

DEPENDS_append = " osal-rtos common-csl-ip-rtos openssl-native"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

SCICLIENT_PACKAGE_BASE = "${WORKDIR}/sciclient_base"
SCICLIENT_ROOTPATH = "${SCICLIENT_PACKAGE_BASE}/package/all/pdk_/packages/ti/drv/sciclient"

export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"
export PDK_SCICLIENT_ROOT_PATH = "${SCICLIENT_PACKAGE_BASE}/package/all/pdk_/packages"

BUILD_HS = ""
BUILD_HS_am65xx-hs-evm = "yes"
BUILD_HS_j7-hs-evm = "yes"
export BUILD_HS

# Sciclient for am65x supports mcu1_1 in addition to the default cores in TI_PDK_LIMIT_CORES
TI_PDK_LIMIT_CORES_append_am65xx = " mcu1_1"

# HTML doc link params
PDK_COMP_LINK_TEXT = "SCICLIENT"

do_configure() {

    rm -rf ${SCICLIENT_PACKAGE_BASE}
    cd ${S}

    # remove any previous package
    rm -f ${SCICLIENT_PACKAGE_BASE}

    # make the release package before building it
    make package BOARD="${TI_PDK_LIMIT_BOARDS}" DEST_ROOT=${SCICLIENT_PACKAGE_BASE} PDK_SCICLIENT_COMP_PATH=${S}

   # This is to ensure the make package completed successfully
    cat  ${SCICLIENT_ROOTPATH}/makefile
}

do_compile() {

    cd ${SCICLIENT_ROOTPATH}

    # Clean
    # make clean LIMIT_SOCS="${LIMSOCS}" LIMIT_BOARDS="${LIMBOARDS}"

    # Build am65xx libraries
    make lib xdc_meta doxygen LIMIT_SOCS="${TI_PDK_LIMIT_SOCS}" LIMIT_BOARDS="${TI_PDK_LIMIT_BOARDS}" LIMIT_CORES="${TI_PDK_LIMIT_CORES}"

    #archive
    tar -cf sciclient.tar --exclude='*.tar' ./*
}


do_compile_prepend_am65xx-hs-evm() {

    cd ${SCICLIENT_ROOTPATH}

    # Saving the GP firmware to a different GP name
    cp ${CP_ARGS} ./soc/V0/sysfw.bin ./soc/V0/sysfw-gp.bin
    
    cd ${SCICLIENT_ROOTPATH}/tools
    # Create the .bin file for HS
    ${SCICLIENT_ROOTPATH}/tools/firmwareHeaderGen.sh am65x-hs ${PDK_INSTALL_DIR}/packages
    cd -
}

do_compile_prepend_am65xx-evm() {

    cd ${SCICLIENT_ROOTPATH}/tools

    # Create the .bin file for GP, PG1
    ${SCICLIENT_ROOTPATH}/tools/firmwareHeaderGen.sh am65x ${PDK_INSTALL_DIR}/packages
    # Create the .bin file for GP, PG2
    ${SCICLIENT_ROOTPATH}/tools/firmwareHeaderGen.sh am65x_pg2 ${PDK_INSTALL_DIR}/packages
    
    cd -
}


do_compile_prepend_j7-evm() {

    cd ${SCICLIENT_ROOTPATH}/tools
    # Create the .bin file for GP
    ${SCICLIENT_ROOTPATH}/tools/firmwareHeaderGen.sh j721e ${PDK_INSTALL_DIR}/packages
    cd -
}

do_compile_prepend_j7-hs-evm() {

    cd ${SCICLIENT_ROOTPATH}/tools
    # Create the .bin file for HS
    ${SCICLIENT_ROOTPATH}/tools/firmwareHeaderGen.sh j721e-hs ${PDK_INSTALL_DIR}/packages
    cd -
}

do_install() {
    cd ${SCICLIENT_ROOTPATH}
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/sciclient
    find -name "*.tar" -exec tar xf {} --no-same-owner -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/drv/sciclient \;
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INSANE_SKIP_${PN} = "arch ldflags file-rdeps"

INSANE_SKIP_${PN}-dbg = "arch"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
