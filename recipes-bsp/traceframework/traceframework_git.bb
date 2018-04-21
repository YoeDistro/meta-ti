DESCRIPTION = "TI Trace Framework library"

DEPENDS = "cuia"

include traceframework.inc

PR = "${INC_PR}.0"

BASEDIR = "${WORKDIR}/git"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} \
                 CUIA_INSTALL_DIR=${STAGING_INCDIR} CUIA_LIB_DIR=${STAGING_LIBDIR}"

do_compile () {
#   making the library
	oe_runmake clean
	oe_runmake lib

#   make the test application
	for device in ${DEVICELIST}
	do
		oe_runmake tests DEVICE="$device" TFW_INC_DIR=${BASEDIR} TFW_SRC_DIR=${S} \
			TARGET_ROOT_DIR=${D}
	done
}

do_install() {
	for device in ${DEVICELIST}
	do
		oe_runmake install DEVICE="$device" TFW_SRC_DIR=${S} TARGET_ROOT_DIR=${D}
	done
	chown -R root:root ${D}
}

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${bindir}/tfw*.out \
                    ${bindir}/*.txt"
