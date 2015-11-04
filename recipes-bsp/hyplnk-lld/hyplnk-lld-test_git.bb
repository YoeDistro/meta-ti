include hyplnk-lld.inc

PR = "${INC_PR}.0"

DEPENDS = "common-csl-ip hyplnk-lld"

do_compile () {
	make -f makefile_armv7 clean PDK_INSTALL_PATH="${STAGING_INCDIR}" \
		HYPLNK_SRC_DIR="${S}"
	for device in ${DEVICELIST}
	do
		make -f makefile_armv7 tests examples \
			PDK_INSTALL_PATH="${STAGING_INCDIR}" \
			DEVICE="$device" HYPLNK_SRC_DIR="${S}"
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
