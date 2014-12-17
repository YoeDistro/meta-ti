DESCRIPTION = "TI QMSS low level driver library"
COMPATIBLE_MACHINE = "keystone"

DEPENDS="common-csl-ip rm-lld"

include qmss-lld.inc

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
}
