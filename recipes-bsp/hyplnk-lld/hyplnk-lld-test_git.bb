include hyplnk-lld.inc

DEPENDS = "common-csl-ip hyplnk-lld"

CHOICELIST = " yes \
               no  \
"

do_compile () {
	make -f makefile_armv7 clean PDK_INSTALL_PATH="${STAGING_INCDIR}" \
		HYPLNK_SRC_DIR="${S}"
	for device in ${DEVICELIST}
	do
		for choice in ${CHOICELIST}
		do
			make -f makefile_armv7 tests examples \
				PDK_INSTALL_PATH="${STAGING_INCDIR}" \
				DEVICE="$device" HYPLNK_SRC_DIR="${S}" \
				USEDYNAMIC_LIB="$choice"
		done
	done
}

do_install () {
    for device in ${DEVICELIST}
	do
		make -f makefile_armv7 installbin \
			PDK_INSTALL_PATH="${STAGING_INCDIR}" DEVICE="$device" \
			HYPLNK_SRC_DIR="${S}" \
			INSTALL_BIN_BASE_DIR="${D}/${bindir}"
	done
}
