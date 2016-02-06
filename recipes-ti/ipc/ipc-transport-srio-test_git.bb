include ipc-transport-srio.inc

PR = "${INC_PR}.0"

DEPENDS = "ipc-transport-srio ipc-transport-qmss ti-ipc"

DEVICELIST = "k2h k2k"

CHOICELIST = "yes no"

do_compile () {
#   Now build the test code
	for device in ${DEVICELIST}
	do
		for choice in ${CHOICELIST}
		do
			oe_runmake tests \
				IPC_DEVKIT_INSTALL_PATH=${STAGING_INCDIR} \
				USEDYNAMIC_LIB="$choice" DEVICE="$device"
		done
	done
}

do_install () {

	for device in ${DEVICELIST}
	do
		oe_runmake installbin \
			INSTALL_BIN_BASE_DIR=${D}${bindir} DEVICE="$device"
	done

}
