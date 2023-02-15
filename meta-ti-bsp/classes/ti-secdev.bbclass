# Helper class to prepare correct environment for signing with TI Security Development Tools

# K3 SECDEV scripts use OpenSSL
DEPENDS:append:k3 = " openssl-native"
DEPENDS:append:k3r5 = " openssl-native"

# Use package version of TI SECDEV for K3 if one is not provided through the environment
DEPENDS:append:k3 = "${@ '' if d.getVar('TI_SECURE_DEV_PKG_K3') else ' ti-k3-secdev-native' }"
DEPENDS:append:k3r5 = "${@ '' if d.getVar('TI_SECURE_DEV_PKG_K3') else ' ti-k3-secdev-native' }"
TI_K3_SECDEV_INSTALL_DIR = "${STAGING_DIR_NATIVE}${datadir}/ti/ti-k3-secdev"
TI_SECURE_DEV_PKG:k3 = "${@ d.getVar('TI_SECURE_DEV_PKG_K3') or d.getVar('TI_K3_SECDEV_INSTALL_DIR') }"
TI_SECURE_DEV_PKG:k3r5 = "${@ d.getVar('TI_SECURE_DEV_PKG_K3') or d.getVar('TI_K3_SECDEV_INSTALL_DIR') }"

# For non-K3 we require the SECDEV tools be provided through the environment with the following vars
TI_SECURE_DEV_PKG:ti33x = "${TI_SECURE_DEV_PKG_CAT}/am3x"
TI_SECURE_DEV_PKG:ti43x = "${TI_SECURE_DEV_PKG_CAT}/am4x"
TI_SECURE_DEV_PKG:am57xx = "${TI_SECURE_DEV_PKG_AUTO}/am5x"
TI_SECURE_DEV_PKG:dra7xx = "${TI_SECURE_DEV_PKG_AUTO}/dra7"

# The SECDEV scripts may need their own location provided through the environment
export TI_SECURE_DEV_PKG
