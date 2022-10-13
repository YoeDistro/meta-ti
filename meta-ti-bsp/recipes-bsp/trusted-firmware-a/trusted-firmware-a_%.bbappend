PV:k3 = "2.6+2.7-rc0"
SRCREV_tfa:k3 = "453abc80b2eacbf2816c838e47e40f063227d934"
SRC_URI:k3 = "git://git.trustedfirmware.org/TF-A/trusted-firmware-a.git;protocol=https;name=tfa;branch=master"
COMPATIBLE_MACHINE:k3 = "k3"
TFA_BUILD_TARGET:k3 = "all"
TFA_INSTALL_TARGET:k3 = "bl31"
TFA_SPD:k3 = "opteed"

SRC_URI:append:k3 = " file://rwx-segments-ti.patch"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

EXTRA_OEMAKE:append:k3 = "${@ ' K3_USART=' + d.getVar('TFA_K3_USART') if d.getVar('TFA_K3_USART') else ''}"
EXTRA_OEMAKE:append:k3 = "${@ ' K3_PM_SYSTEM_SUSPEND=' + d.getVar('TFA_K3_SYSTEM_SUSPEND') if d.getVar('TFA_K3_SYSTEM_SUSPEND') else ''}"

# Signing procedure for K3 HS devices
tfa_sign_k3hs() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${BUILD_DIR}; \
		mv bl31.bin bl31.bin.unsigned; \
		if [ -f ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ]; then \
			${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
		else \
			echo "Warning: TI_SECURE_DEV_PKG not set, TF-A not signed."; \
			cp bl31.bin.unsigned bl31.bin; \
		fi; \
	)
}

do_compile:append:am65xx-hs-evm() {
	tfa_sign_k3hs
}

do_compile:append:am64xx-hs-evm() {
	tfa_sign_k3hs
}

do_compile:append:j721e-hs-evm() {
	tfa_sign_k3hs
}

do_compile:append:j7200-hs-evm() {
	tfa_sign_k3hs
}

do_compile:append:j721s2-hs-evm() {
	tfa_sign_k3hs
}
