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
