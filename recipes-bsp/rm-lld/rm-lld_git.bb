require rm-lld.inc

PR = "${INC_PR}.0"

DEPENDS = "ti-ipc libdaemon"

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${bindir}/rmDspClientTest.out \
                    ${bindir}/rmLinuxClientTest.out \
                    ${bindir}/rmLinuxMtClientTest.out \
                    ${bindir}/ti/drv/rm/test/dts_files/*.dtb"

CHOICELIST = "yes no"

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
    chown -R root:root ${D}
}

INHIBIT_PACKAGE_STRIP_FILES = "${PKGD}${libdir}/librm.a"
