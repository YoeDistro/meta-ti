DESCRIPTION = "TI Resource Manager Low Level Driver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/rm/COPYING.txt;md5=dc61631b65360e6beb73b6c337800afc"

RM_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/rm-lld.git"
RM_LLD_GIT_PROTOCOL = "git"
RM_LLD_GIT_BRANCH = "master"
RM_LLD_GIT_DESTSUFFIX = "git/ti/drv/rm"

# Below commit ID corresponds to DEV.RM_LLD.02.01.02.00
RM_LLD_SRCREV = "b3d711203c6b4cf99fb2f95dc2f4728dfd1b2639"

BRANCH = "${RM_LLD_GIT_BRANCH}"
SRC_URI = "${RM_LLD_GIT_URI};destsuffix=${RM_LLD_GIT_DESTSUFFIX};protocol=${RM_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${RM_LLD_SRCREV}"

PV = "02.01.02.00"
PR = "r2"

COMPATIBLE_MACHINE = "keystone"

DEPENDS = "ti-ipc libdaemon"

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${bindir}/rmDspClientTest.out \
                    ${bindir}/rmLinuxClientTest.out \
                    ${bindir}/rmLinuxMtClientTest.out \
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
