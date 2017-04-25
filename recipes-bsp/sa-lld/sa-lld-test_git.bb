DESCRIPTION = "TI Security Accelerator LLD (SA LLD) Examples"

DEPENDS = "common-csl-ip cppi-lld qmss-lld pa-lld sa-lld"

include sa-lld.inc

PR = "${INC_PR}.1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEVICELIST_k2hk = "k2h k2k"
DEVICELIST_k2l-evm  = "k2l"
DEVICELIST_k2e  = "k2e"

CHOICELIST = "no yes"

do_compile () {
#   Now build the lld in the updated directory
	for device in ${DEVICELIST}
	do
		make -f makefile_armv7 clean PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device" SA_SRC_DIR=${S}
		for choice in ${CHOICELIST}
		do
			make -f makefile_armv7 examples utils PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device" SA_SRC_DIR=${S} USEDYNAMIC_LIB="$choice"
		done
	done
}

do_install () {
	install -d ${D}${bindir}/ti/drv/sa/example/SaBasicExample/vectors

# copy all the test vectors
	find example/SaBasicExample/vectors -name *.bin -type f | xargs -I {} cp --parents {} ${D}${bindir}/ti/drv/sa

	for device in ${DEVICELIST}
	do
		make -f makefile_armv7 installbin PDK_INSTALL_PATH=${STAGING_INCDIR} DEVICE="$device" SA_SRC_DIR=${S} INSTALL_BIN_BASE_DIR=${D}${bindir}
	done
}
