PV_k3 = "2.8+git${SRCPV}"
LIC_FILES_CHKSUM_k3 = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"
SRCREV_tfa_k3 = "2fcd408bb3a6756767a43c073c597cef06e7f2d5"
COMPATIBLE_MACHINE_k3 = "k3"
TFA_BUILD_TARGET_k3 = "all"
TFA_INSTALL_TARGET_k3 = "bl31"
TFA_SPD_k3 = "opteed"

EXTRA_OEMAKE_append_k3 = "${@ 'K3_USART=' + d.getVar('TFA_K3_USART') if d.getVar('TFA_K3_USART') else ''}"
EXTRA_OEMAKE_append_k3 = "${@ 'K3_PM_SYSTEM_SUSPEND=' + d.getVar('TFA_K3_SYSTEM_SUSPEND') if d.getVar('TFA_K3_SYSTEM_SUSPEND') else ''}"

# Signing procedure for K3 HS devices
tfa_sign_k3hs() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${B}/${BUILD_DIR}/release/; \
		mv bl31.bin bl31.bin.unsigned; \
		if [ -f ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ]; then \
			${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
		else \
			echo "Warning: TI_SECURE_DEV_PKG not set, TF-A not signed."; \
			cp bl31.bin.unsigned bl31.bin; \
		fi; \
	)
}

do_compile_append_am65xx-hs-evm() {
	tfa_sign_k3hs
}

do_compile_append_am64xx-evm() {
	tfa_sign_k3hs
}

do_compile_append_am62xx-evm() {
        tfa_sign_k3hs
}

do_compile_append_am62xx-lp-evm() {
        tfa_sign_k3hs
}

do_compile_append_am62axx-evm() {
        tfa_sign_k3hs
}

do_compile_append_j7-hs-evm() {
	tfa_sign_k3hs
}

do_compile_append_j7200-hs-evm() {
	tfa_sign_k3hs
}

do_compile_append_j721s2-hs-evm() {
	tfa_sign_k3hs
}

do_compile_append_j784s4-hs-evm() {
	tfa_sign_k3hs
}

do_install_append_k3() {
    if [ -f $BUILD_PLAT/bl31.bin.unsigned ]; then
        echo "Install bl31.bin.unsigned"
        install -m 0644 $BUILD_PLAT/bl31.bin.unsigned \
        ${D}/firmware/bl31.bin.unsigned
    else
        echo "Install bl31.bin.unsigned"
        install -m 0644 $BUILD_PLAT/bl31.bin \
        ${D}/firmware/bl31.bin.unsigned
    fi
}

