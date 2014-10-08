DESCRIPTION = "TI Resource Manager Low Level Driver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/rm/COPYING.txt;md5=dc61631b65360e6beb73b6c337800afc"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-rtos/rm-lld.git;destsuffix=git/ti/drv/rm;protocol=git;branch=${BRANCH}"
# Below commit ID corresponds to DEV.RM_LLD.02.01.00.06
SRCREV = "dc0dba4a6b0358efcb861d50b3dae21beb5281a8"
PV = "02.01.00.06"
PR = "r0"

COMPATIBLE_MACHINE = "keystone"

DEPENDS = "ti-ipc libdaemon"

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${bindir}/rmDspClientTest_*.out \
                    ${bindir}/rmLinuxClientTest_*.out \
                    ${bindir}/ti/drv/rm/test/dts_files/*.dtb"

CHOICELIST = "yes no"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/rm"

do_compile () {
#   Now build the lld
    make -f makefile_armv7 clean lib PDK_INSTALL_PATH=${STAGING_INCDIR} RM_SRC_DIR=${S}
    for choice in ${CHOICELIST}
    do
      make -f makefile_armv7 tests IPC_DEVKIT_INSTALL_PATH=${STAGING_INCDIR} PDK_INSTALL_PATH=${BASEDIR} USEDYNAMIC_LIB="$choice"
    done
}

do_install () {
    install -d ${D}${includedir}/ti/drv/rm
    install -d ${D}${libdir}
    install -d ${D}${bindir}
    make -f makefile_armv7 install installbin installbin_test INSTALL_INC_BASE_DIR=${D}${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir} INSTALL_BIN_BASE_DIR=${D}${bindir}
}
