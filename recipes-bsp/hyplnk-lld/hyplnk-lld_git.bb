include hyplnk-lld.inc

DEPENDS = "common-csl-ip"

CHOICELIST = " yes \
               no  \
"
do_compile () {
	make -f makefile_armv7 clean PDK_INSTALL_PATH="${STAGING_INCDIR}" \
		HYPLNK_SRC_DIR="${S}"
	for device in ${DEVICELIST}
	do
		make -f makefile_armv7 lib PDK_INSTALL_PATH="${STAGING_INCDIR}"\
			DEVICE="$device" HYPLNK_SRC_DIR="${S}"
	done
}

do_install () {
	make -f makefile_armv7 install PDK_INSTALL_PATH="${STAGING_INCDIR}" \
		INSTALL_INC_BASE_DIR="${D}/${includedir}" \
		INSTALL_LIB_BASE_DIR="${D}${libdir}" HYPLNK_SRC_DIR="${S}"
}
