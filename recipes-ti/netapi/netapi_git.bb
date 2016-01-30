DESCRIPTION = "Provides NETAPI module: TI user space network library"

DEPENDS = "common-csl-ip rm-lld qmss-lld cppi-lld sa-lld hplib pktlib nwal-lld"

include netapi.inc

PR = "${INC_PR}.1"

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR}"

do_compile () {
#   Now build the netapi
	for device in ${DEVICELIST}
	do
		for choice in ${CHOICELIST}
		do
			oe_runmake  clean NETAPI_SRC_DIR=${S} NETAPI_INC_DIR=${BASEDIR} \
				USEDYNAMIC_LIB="$choice" DEVICE="$device"
			oe_runmake lib NETAPI_SRC_DIR=${S} NETAPI_INC_DIR=${BASEDIR} \
				USEDYNAMIC_LIB="$choice" DEVICE="$device"
		done
	done
}

do_install () {
	for device in ${DEVICELIST}
	do
		oe_runmake install INSTALL_INC_BASE_DIR=${D}${includedir} \
			INSTALL_LIB_BASE_DIR=${D}${libdir} INSTALL_BIN_BASE_DIR=${D}${bindir} \
			SYSCONFDIR=${D}${sysconfdir} DEVICE="$device"
	done
}
