PV_k3 = "2.6"
LIC_FILES_CHKSUM_k3 = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"
BRANCH_k3 = "ti-atf"
SRC_URI_k3 = "git://git.ti.com/atf/arm-trusted-firmware.git;branch=${BRANCH};name=tfa"
SRCREV_tfa_k3 = "6541aa24f834daa80da701e0054dfe3a16cda0ce"
COMPATIBLE_MACHINE_k3 = "k3"
TFA_BUILD_TARGET_k3 = "all"
TFA_INSTALL_TARGET_k3 = "bl31"
TFA_SPD_k3 = "opteed"

EXTRA_OEMAKE_append_k3 = "${@ 'K3_USART=' + d.getVar('TFA_K3_USART') if d.getVar('TFA_K3_USART') else ''}"
EXTRA_OEMAKE_append_k3 = "${@ 'K3_PM_SYSTEM_SUSPEND=' + d.getVar('TFA_K3_SYSTEM_SUSPEND') if d.getVar('TFA_K3_SYSTEM_SUSPEND') else ''}"

do_compile_append_am65xx-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${B}/${BUILD_DIR}/release/; \
		mv bl31.bin bl31.bin.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
	)
}

do_compile_append_am64xx-hs-evm() {
        export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
        ( cd ${B}/${BUILD_DIR}/release/; \
                mv bl31.bin bl31.bin.unsigned; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
        )
}

do_compile_append_j7-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${B}/${BUILD_DIR}/release/; \
		mv bl31.bin bl31.bin.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
	)
}

do_compile_append_j7200-hs-evm() {
        export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
        ( cd ${B}/${BUILD_DIR}/release/; \
                mv bl31.bin bl31.bin.unsigned; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
        )
}

do_compile_append_j721s2-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${B}/${BUILD_DIR}/release/; \
		mv bl31.bin bl31.bin.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
	)
}
