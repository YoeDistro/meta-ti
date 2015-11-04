include hyplnk-lld.inc

PR = "${INC_PR}.0"

DEPENDS = "common-csl-ip"

SRC_URI += "file://init_hyplnk.sh"

inherit update-rc.d

INITSCRIPT_NAME = "init_hyplnk.sh"
INITSCRIPT_PARAMS = "defaults 10"

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
#   Set the generic device library symbolic link to default k2h
	cd ${D}${libdir}
	ln -sf libhyplnk_k2h.so.1.0.0 libhyplnk_device.so.1
	ln -sf libhyplnk_device.so.1 libhyplnk_device.so
#   Copy init scripts
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${WORKDIR}/init_hyplnk.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}

}
