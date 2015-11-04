DESCRIPTION = "TI CPPI low level driver unit test and example binaries"
DEPENDS = "common-csl-ip rm-lld cppi-lld qmss-lld"

include cppi-lld.inc

PR = "${INC_PR}.0"

CHOICELIST = "yes no"

do_compile () {
#   Now build the lld in the updated directory
	for device in ${DEVICELIST}
	do
		make -f makefile_armv7 clean PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device" CPPI_SRC_DIR=${S}
		for choice in ${CHOICELIST}
		do
			make -f makefile_armv7 tests examples PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device" CPPI_SRC_DIR=${S} USEDYNAMIC_LIB="$choice"
		done
	done
}

do_install () {
	for device in ${DEVICELIST}
do
	make -f makefile_armv7 installbin PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device" CPPI_SRC_DIR=${S} INSTALL_BIN_BASE_DIR=${D}${bindir}
	done
}
