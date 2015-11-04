DESCRIPTION = "TI Trace Framework library"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/instrumentation/traceframework/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

TRACEFRAMEWORK_GIT_URI = "git://git.ti.com/keystone-rtos/traceframework.git"
TRACEFRAMEWORK_GIT_PROTOCOL = "git"
TRACEFRAMEWORK_GIT_BRANCH = "master"
TRACEFRAMEWORK_GIT_DESTSUFFIX = "git/ti/instrumentation/traceframework"

# Below commit ID corresponds to DEV.TFWK-01.01.01.07
TRACEFRAMEWORK_SRCREV = "8d9c0e3a5bcb43aad98a794c31f42b652c6b3b53"

BRANCH="${TRACEFRAMEWORK_GIT_BRANCH}"
SRC_URI = "${TRACEFRAMEWORK_GIT_URI};destsuffix=${TRACEFRAMEWORK_GIT_DESTSUFFIX};protocol=${TRACEFRAMEWORK_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${TRACEFRAMEWORK_SRCREV}"

PV = "01.01.01.07"
PR = "r2"

COMPATIBLE_MACHINE = "(tci6614-evm|keystone)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
DEPENDS = "cuia"

DEVICELIST = ""
DEVICELIST_append_tci6614-evm = "c6614"
DEVICELIST_append_k2hk-evm    = "k2k k2h"
DEVICELIST_append_k2e-evm     = "k2e"
DEVICELIST_append_k2l-evm     = "k2l"

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
