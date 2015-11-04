DESCRIPTION = "TI PA LLD unit test and example binaries"

DEPENDS = "common-csl-ip pa-lld cppi-lld qmss-lld sa-lld"

include pa-lld.inc

PR = "${INC_PR}.1"

CHOICELIST = "yes no"

do_compile () {
# Now build the lld unit test examples
	for device in ${DEVICELIST}
	do
	make -f makefile_armv7 clean PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device" PA_SRC_DIR=${S}
		for choice in ${CHOICELIST}
		do
			make -f makefile_armv7 tests examples PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device" PA_SRC_DIR=${S} USEDYNAMIC_LIB="$choice"
		done
	done
}

do_install () {
	for device in ${DEVICELIST}
	do
		make -f makefile_armv7 installbin PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device" PA_SRC_DIR=${S} INSTALL_BIN_BASE_DIR=${D}${bindir}
	done
}
