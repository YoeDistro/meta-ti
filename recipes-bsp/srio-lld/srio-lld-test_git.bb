DESCRIPTION = "TI SRIO peripheral low level driver unit test and example binaries"
DEPENDS="common-csl-ip rm-lld cppi-lld qmss-lld srio-lld cmem uio-module-drv"

include srio-lld.inc

PR = "${INC_PR}.0"

CHOICELIST = "yes no"

do_compile () {
#   Now build the lld in the updated directory
	for device in ${DEVICELIST}
	do
		oe_runmake clean DEVICE="$device" SRIO_SRC_DIR=${S}
		for choice in ${CHOICELIST}
		do
			oe_runmake tests DEVICE="$device" SRIO_SRC_DIR=${S} USEDYNAMIC_LIB="$choice"
			oe_runmake examples DEVICE="$device" SRIO_SRC_DIR=${S} USEDYNAMIC_LIB="$choice"
		done
	done
}

do_install () {
	for device in ${DEVICELIST}
	do
		oe_runmake installbin DEVICE="$device" SRIO_SRC_DIR=${S} INSTALL_BIN_BASE_DIR=${D}${bindir}
	done
}
