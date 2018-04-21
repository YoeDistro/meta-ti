DESCRIPTION = "TI PA LLD library"

DEPENDS = "common-csl-ip rm-lld"

include pa-lld.inc

PR = "${INC_PR}.1"

do_compile () {
#   Now build the lld in the updated directory
    for device in ${DEVICELIST}
    do
        make -f makefile_armv7 lib PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device"
    done
}

do_install () {
    make -f makefile_armv7 install PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_INC_BASE_DIR=${D}${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
    chown -R root:root ${D}

#   Set the generic device library symbolic link
    ln -sf ${LIBPA}.so.1.0.0 ${D}${libdir}/libpa_device.so.1
    ln -sf libpa_device.so.1 ${D}${libdir}/libpa_device.so
}
