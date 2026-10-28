PACKAGES += " \
    ${PN}-iwlwifi-ty \
    ${PN}-iwlwifi-misc-minus-ty \
"

LICENSE:${PN}-iwlwifi-ty    = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-misc-minus-ty = "Firmware-iwlwifi_firmware"

FILES:${PN}-iwlwifi-ty    = " \
    ${nonarch_base_libdir}/firmware/iwlwifi-ty-*.ucode* ${nonarch_base_libdir}/firmware/intel/iwlwifi/iwlwifi-ty-*.ucode* \
    ${nonarch_base_libdir}/firmware/iwlwifi-ty-*.pnvm* ${nonarch_base_libdir}/firmware/intel/iwlwifi/iwlwifi-ty-*.pnvm* \
"
FILES:${PN}-iwlwifi-misc  = ""
FILES:${PN}-iwlwifi-misc-minus-ty = " \
    ${nonarch_base_libdir}/firmware/iwlwifi-*.ucode* ${nonarch_base_libdir}/firmware/intel/iwlwifi/iwlwifi-*.ucode* \
    ${nonarch_base_libdir}/firmware/iwlwifi-*.pnvm* ${nonarch_base_libdir}/firmware/intel/iwlwifi/iwlwifi-*.pnvm* \
"

RDEPENDS:${PN}-iwlwifi-ty    = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-misc += "${PN}-iwlwifi-misc-minus-ty"
