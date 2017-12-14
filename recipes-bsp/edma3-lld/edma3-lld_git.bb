require edma3-lld.inc

PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "keystone"

DEPENDS = "common-csl-ip"

PLATFORMLIST = "tci6636k2h-evm \
	tci6636k2h-evm \
	tci6638k2k-evm \
	tci6630k2l-evm \
	c66ak2e-evm \
	tci66ak2g02-evm \
	"

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${bindir}/*"

S = "${WORKDIR}/git"

do_compile () {
	cd ${S}/packages
	for platform in ${PLATFORMLIST}
	do
		ROOTDIR=${S} CROSSCC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" CROSSAR="${TARGET_PREFIX}ar" \
		CROSSLNK="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" INTERNAL_SW_ROOT="${S}" make \
		PLATFORM="$platform" TARGET=a15 TOOLCHAIN_a15=GCC FORMAT=ELF \
		SONAME=libedma3.so all
	done
}

do_install () {
	CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

	install -d ${D}/${libdir}

	# Static Libraries
	cp ${CP_ARGS} ${S}/packages/ti/sdo/edma3/drv/lib/a15/release/edma3_lld_drv.aa15fg \
		${D}/${libdir}/libedma3.a
	cp ${CP_ARGS} ${S}/packages/ti/sdo/edma3/rm/lib/tci6636k2h-evm/a15/release/edma3_lld_rm.aa15fg \
		${D}/${libdir}/libedma3rm.a

	# Shared Libraries
	cp ${CP_ARGS} ${S}/packages/ti/sdo/edma3/drv/lib/a15/release/libedma* ${D}/${libdir}
	cp ${CP_ARGS} ${S}/packages/ti/sdo/edma3/rm/lib/tci6636k2h-evm/a15/release/libedma* ${D}/${libdir}

	# Copy Headers
	install -d ${D}/${includedir}/ti/sdo/edma3/drv/
	install -d ${D}/${includedir}/ti/sdo/edma3/rm
	cp ${CP_ARGS} ${S}/packages/ti/sdo/edma3/drv/*.h ${D}/${includedir}/ti/sdo/edma3/drv/
	cp ${CP_ARGS} ${S}/packages/ti/sdo/edma3/rm/*.h ${D}/${includedir}/ti/sdo/edma3/rm/

	# Copy Sample Config
	install -d ${D}/${includedir}/ti/sdo/edma3/drv/sample/src/platforms
	cp ${CP_ARGS} ${S}/examples/edma3_user_space_driver/evmTCI6636K2H/evmTCI6636K2HSample.c \
		${D}/${includedir}/ti/sdo/edma3/drv/sample/src/platforms
	cp ${CP_ARGS} ${S}/examples/edma3_user_space_driver/evmTCI6638K2K/evmTCI6638K2KSample.c \
		${D}/${includedir}/ti/sdo/edma3/drv/sample/src/platforms
	cp ${CP_ARGS} ${S}/examples/edma3_user_space_driver/evmTCI6630K2L/evmTCI6630K2LSample.c \
		${D}/${includedir}/ti/sdo/edma3/drv/sample/src/platforms
	cp ${CP_ARGS} ${S}/examples/edma3_user_space_driver/evmC66AK2E/evmC66AK2ESample.c \
		${D}/${includedir}/ti/sdo/edma3/drv/sample/src/platforms

	install -d ${D}/${bindir}
	install -c -m 755 ${S}/examples/edma3_user_space_driver/evmTCI6636K2H/bin/tci6636k2h-evm/edma3_drv_6636k2h_a15_sample_a15host_release.xa15fg \
		${D}/${bindir}/edma3_drv_6636k2h_a15_sample_a15host_release.xa15fg
	install -c -m 755 ${S}/examples/edma3_user_space_driver/evmTCI6638K2K/bin/tci6638k2k-evm/edma3_drv_6638k2k_a15_sample_a15host_release.xa15fg \
		${D}/${bindir}/edma3_drv_6638k2k_a15_sample_a15host_release.xa15fg
	install -c -m 755 ${S}/examples/edma3_user_space_driver/evmTCI6630K2L/bin/tci6630k2l-evm/edma3_drv_6630k2l_a15_sample_a15host_release.xa15fg \
		${D}/${bindir}/edma3_drv_6630k2l_a15_sample_a15host_release.xa15fg
	install -c -m 755 ${S}/examples/edma3_user_space_driver/evmC66AK2E/bin/c66ak2e-evm/edma3_drv_c66ak2e_a15_sample_a15host_release.xa15fg \
		${D}/${bindir}/edma3_drv_c66ak2e_a15_sample_a15host_release.xa15fg
}
