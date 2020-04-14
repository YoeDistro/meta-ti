DESCRIPTION = "TI QMSS low level driver library"

DEPENDS = "common-csl-ip rm-lld"

include qmss-lld.inc

PR = "${INC_PR}.1"

do_compile () {
#   Now build the lld
    make -f makefile_armv7 clean PDK_INSTALL_PATH=${STAGING_INCDIR}
	for device in ${DEVICELIST}
	do
		make -f makefile_armv7 lib PDK_INSTALL_PATH=${STAGING_INCDIR}  DEVICE="${device}"
	done
}

do_install () {
    make -f makefile_armv7 install PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_INC_BASE_DIR=${D}${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
    chown -R root:root ${D}

    # Set the generic device library symbolic link to default k2h
    cd ${D}${libdir}

    # Link only the first device in the list
    for device in ${DEVICELIST}
    do
        ln -sf libqmss_${device}.so.1.0.0 libqmss_device.so.1
        break
    done
    ln -sf libqmss_device.so.1 libqmss_device.so
}

INHIBIT_PACKAGE_STRIP_FILES_k2hk = "${PKGD}${libdir}/libqmss_k2h.a ${PKGD}${libdir}/libqmss_k2k.a"
INHIBIT_PACKAGE_STRIP_FILES_k2e = "${PKGD}${libdir}/libqmss_k2e.a"
INHIBIT_PACKAGE_STRIP_FILES_k2l = "${PKGD}${libdir}/libqmss_k2l.a"
INHIBIT_PACKAGE_STRIP_FILES_k2g = "${PKGD}${libdir}/libqmss_k2h.a"
INHIBIT_PACKAGE_STRIP_FILES_append = " ${PKGD}${libdir}/libqmss.a"
