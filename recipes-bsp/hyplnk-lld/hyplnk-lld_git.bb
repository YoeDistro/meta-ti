include hyplnk-lld.inc

PR = "${INC_PR}.1"

DEPENDS = "common-csl-ip"

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
	chown -R root:root ${D}

	# Set the generic device library symbolic link to default k2h
	cd ${D}${libdir}

	# Link only the first device in the list
	for device in ${DEVICELIST}
	do
		ln -sf libhyplnk_${device}.so.1.0.0 libhyplnk_device.so.1
		break
	done
	ln -sf libhyplnk_device.so.1 libhyplnk_device.so
}

INHIBIT_PACKAGE_STRIP_FILES_k2hk = "${PKGD}${libdir}/libhyplnk_k2h.a ${PKGD}${libdir}/libhyplnk_k2k.a"
INHIBIT_PACKAGE_STRIP_FILES_k2e = "${PKGD}${libdir}/libhyplnk_k2e.a"
