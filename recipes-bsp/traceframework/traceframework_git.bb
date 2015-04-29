DESCRIPTION = "TI Trace Framework library"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/instrumentation/traceframework/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-rtos/traceframework.git;destsuffix=git/ti/instrumentation/traceframework;protocol=git;branch=${BRANCH}"

# Below commit ID corresponds to DEV.TFWK-01.01.01.06B
SRCREV = "6d29bc0d1ab6bb0658876552197dcdc757a25082"
PV = "01.01.01.06"
PR = "r1"

COMPATIBLE_MACHINE = "(tci6614-evm|keystone)"
DEPENDS = "cuia"

DEVICELIST = ""
DEVICELIST_append_tci6614-evm = "c6614"
DEVICELIST_append_keystone    = "k2k k2h k2e k2l"

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${bindir}/tfw*.out \
                    ${bindir}/*.txt"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/instrumentation/traceframework"

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
}
