include ipc-transport-qmss.inc

DEPENDS = "ipc-transport-qmss ti-ipc"

#FILES_${PN}-test = "${bindir}/multiProcessTest_*.out"

CHOICELIST = "yes no"

DEVICELIST = "k2h k2k k2e k2l"

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
