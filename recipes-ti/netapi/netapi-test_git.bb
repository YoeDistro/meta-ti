DESCRIPTION = "Provides test and application binaries using NETAPI module"

DEPENDS = "netapi ipsecmgr"

include netapi.inc

PR = "${INC_PR}.1"

EXTRA_OEMAKE = "PDK_INSTALL_PATH=${STAGING_INCDIR}"

do_compile () {
#   Build the netapi binaries
	for device in ${DEVICELIST}
	do
		for choice in ${CHOICELIST}
		do
			oe_runmake -f makefile_armv7 tests NETAPI_SRC_DIR=${S} NETAPI_INC_DIR=${BASEDIR} \
				USEDYNAMIC_LIB="$choice" DEVICE="$device"
		done

#     Now build the netapi appplications
		cd ${S}/applications/ipsec_offload/ipsecmgr/build
		oe_runmake clean NETAPI_SRC_DIR=${S} DEVICE="$device"
		oe_runmake app NETAPI_SRC_DIR=${S} DEVICE="$device"

		cd ${S}/applications/ipsec_offload/config-app/build
		oe_runmake clean DEVICE="$device"
		oe_runmake app DEVICE="$device"
		cd ${S}
	done
}

do_install () {
	for device in ${DEVICELIST}
	do
		oe_runmake -f makefile_armv7 installbin INSTALL_INC_BASE_DIR=${D}/${includedir} \
			INSTALL_LIB_BASE_DIR=${D}${libdir} INSTALL_BIN_BASE_DIR=${D}${bindir} \
			SYSCONFDIR=${D}${sysconfdir} DEVICE="$device"

		cd ${S}/applications/ipsec_offload/ipsecmgr/build
		oe_runmake install \
			INSTALL_BIN_BASE_DIR=${D}${bindir} SYSCONFDIR=${D}${sysconfdir} \
			DEVICE="$device"

		cd ${S}/applications/ipsec_offload/config-app/build
		oe_runmake install \
			INSTALL_BIN_BASE_DIR=${D}${bindir} SYSCONFDIR=${D}${sysconfdir} \
			DEVICE="$device"
		cd ${S}
	done
}
