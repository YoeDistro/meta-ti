PV:k3 = "2.5"
LIC_FILES_CHKSUM:k3 = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"
BRANCH:k3 = "ti-atf"
SRC_URI:k3 = "git://git.ti.com/atf/arm-trusted-firmware.git;branch=${BRANCH};name=tfa"
SRCREV_tfa:k3 = "0693f356eb2a25adf8758d98058120bed082cc73"
COMPATIBLE_MACHINE:k3 = "k3"
TFA_BUILD_TARGET:k3 = "all"
TFA_INSTALL_TARGET:k3 = "bl31"
TFA_SPD:k3 = "opteed"

do_compile:append:am65xx-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${BUILD_DIR}; \
		mv bl31.bin bl31.bin.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
	)
}

do_compile:append:am64xx-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${B}/${BUILD_DIR}/release/; \
		mv bl31.bin bl31.bin.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
	)
}

do_compile:append:j7-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${BUILD_DIR}; \
		mv bl31.bin bl31.bin.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
	)
}

do_compile:append:j7200-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${B}/${BUILD_DIR}/release/; \
		mv bl31.bin bl31.bin.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
	)
}
