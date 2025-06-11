# NOTE: This .inc file with customizations only gets included for K3 platforms

PV = "2.12+git"
SRCREV_tfa = "39fe4a856d3576646642c2516b7a99de63eca74e"
SRC_URI_TRUSTED_FIRMWARE_A = "git://github.com/TexasInstruments/arm-trusted-firmware.git;protocol=https"
SRCBRANCH = "ti-master"
SRC_URI = "${SRC_URI_TRUSTED_FIRMWARE_A};name=tfa;branch=${SRCBRANCH}"

LIC_FILES_CHKSUM = "file://docs/license.rst;md5=1118e32884721c0be33267bd7ae11130"
COMPATIBLE_MACHINE = "k3"

TFA_BUILD_TARGET = "all"
TFA_INSTALL_TARGET = "bl31"
TFA_SPD = "opteed"

# For am62lxx, install bl1 & bl31 using TFA_INSTALL_TARGET
TFA_INSTALL_TARGET:am62lxx = "bl31 bl1"
TFA_SPD:am62lxx = ""

EXTRA_OEMAKE += "${@ 'K3_USART=' + d.getVar('TFA_K3_USART') if d.getVar('TFA_K3_USART') else ''}"
EXTRA_OEMAKE += "${@ 'K3_PM_SYSTEM_SUSPEND=' + d.getVar('TFA_K3_SYSTEM_SUSPEND') if d.getVar('TFA_K3_SYSTEM_SUSPEND') else ''}"
EXTRA_OEMAKE:append:ti-falcon = " PRELOADED_BL33_BASE=0x82000000 K3_HW_CONFIG_BASE=0x88000000"
