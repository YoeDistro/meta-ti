DESCRIPTION = "TI PA LLD library"

DEPENDS = "common-csl-ip rm-lld"

include pa-lld.inc

SRC_URI += "file://init_pa.sh"

inherit update-rc.d

INITSCRIPT_NAME = "init_pa.sh"
INITSCRIPT_PARAMS = "defaults 10"

do_compile () {
#   Now build the lld in the updated directory
	make -f makefile_armv7 DEVICE=k2h clean lib PDK_INSTALL_PATH=${STAGING_INCDIR}
	make -f makefile_armv7 DEVICE=k2l lib PDK_INSTALL_PATH=${STAGING_INCDIR}
}

do_install () {
	make -f makefile_armv7 install PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_INC_BASE_DIR=${D}${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
#   Set the generic device library symbolic link
    ln -sf libpa.so.1.0.0 ${D}${libdir}/libpa_device.so.1
    ln -sf libpa_device.so.1 ${D}${libdir}/libpa_device.so
#   Copy init scripts
    install -d ${D}${sysconfdir}/init.d/
    install -c -m 755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}
