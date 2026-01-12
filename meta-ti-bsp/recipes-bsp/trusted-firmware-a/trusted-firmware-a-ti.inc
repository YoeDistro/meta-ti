# NOTE: This .inc file with customizations only gets included for K3 platforms

FILESEXTRAPATHS:prepend := "${THISDIR}/trusted-firmware-a:"

PV = "2.14+git"

LIC_FILES_CHKSUM = "file://docs/license.rst;md5=6ed7bace7b0bc63021c6eba7b524039e"
SRCREV_tfa = "76500ceaeefcda967d8a1f4e30bb04f9fe0425a2"
SRC_URI_TRUSTED_FIRMWARE_A = "git://git.trustedfirmware.org/TF-A/trusted-firmware-a.git;protocol=https"
SRCBRANCH = "master"

LIC_FILES_CHKSUM:am62lxx = "file://docs/license.rst;md5=6ed7bace7b0bc63021c6eba7b524039e"
SRCREV_tfa:am62lxx = "17d2997c0e7d4549720a5d176916f5ea0f63b009"
SRC_URI_TRUSTED_FIRMWARE_A:am62lxx = "git://github.com/TexasInstruments/arm-trusted-firmware.git;protocol=https"
SRCBRANCH:am62lxx = "ti-tfa-2.14.y"

SRC_URI = "${SRC_URI_TRUSTED_FIRMWARE_A};name=tfa;branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "k3"

TFA_BUILD_TARGET = "all"
TFA_INSTALL_TARGET = "bl31"
TFA_SPD = "opteed"

# For am62lxx, install bl1 & bl31 using TFA_INSTALL_TARGET
TFA_INSTALL_TARGET:am62lxx = "bl31 bl1"

EXTRA_OEMAKE += "${@ 'K3_USART=' + d.getVar('TFA_K3_USART') if d.getVar('TFA_K3_USART') else ''}"
EXTRA_OEMAKE += "${@ 'BL32_BASE=' + d.getVar('TFA_K3_BL32_BASE') if d.getVar('TFA_K3_BL32_BASE') else ''}"
EXTRA_OEMAKE += "${@ 'PRELOADED_BL33_BASE=' + d.getVar('TFA_K3_PRELOADED_BL33') if d.getVar('TFA_K3_PRELOADED_BL33') else ''}"
EXTRA_OEMAKE += "${@ 'K3_HW_CONFIG_BASE=' + d.getVar('TFA_K3_HW_CONFIG_BASE') if d.getVar('TFA_K3_HW_CONFIG_BASE') else ''}"
EXTRA_OEMAKE += "${@ 'K3_PM_SYSTEM_SUSPEND=' + d.getVar('TFA_K3_SYSTEM_SUSPEND') if d.getVar('TFA_K3_SYSTEM_SUSPEND') else ''}"
