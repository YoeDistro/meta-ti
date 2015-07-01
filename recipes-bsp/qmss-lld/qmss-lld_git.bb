DESCRIPTION = "TI QMSS low level driver library"
COMPATIBLE_MACHINE = "keystone"

DEPENDS="common-csl-ip rm-lld"

include qmss-lld.inc


SRC_URI += "file://init_qmss.sh"

inherit update-rc.d

INITSCRIPT_NAME = "init_qmss.sh"
INITSCRIPT_PARAMS = "defaults 10"

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

#   Set the generic device library symbolic link to default k2h
    cd ${D}${libdir}
    ln -sf libqmss_k2h.so.1.0.0 libqmss_device.so.1
    ln -sf libqmss_device.so.1 libqmss_device.so
#   Copy init scripts
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${WORKDIR}/init_qmss.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}
