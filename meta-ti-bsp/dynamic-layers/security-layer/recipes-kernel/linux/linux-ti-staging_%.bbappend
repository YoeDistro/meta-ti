# Enable the dm-verity kernel config fragment via KERNEL_CONFIG_FRAGMENTS.
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "${@'file://dm-verity.cfg' if d.getVar('DM_VERITY_IMAGE') else ''}"
KERNEL_CONFIG_FRAGMENTS += "${@'${UNPACKDIR}/dm-verity.cfg' if d.getVar('DM_VERITY_IMAGE') else ''}"
