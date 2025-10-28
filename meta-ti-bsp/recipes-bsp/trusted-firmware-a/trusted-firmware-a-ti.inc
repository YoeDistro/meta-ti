# NOTE: This .inc file with customizations only gets included for K3 platforms

PV = "2.13+git"

LIC_FILES_CHKSUM = "file://docs/license.rst;md5=6ed7bace7b0bc63021c6eba7b524039e"
SRCREV_tfa = "e0c4d3903b382bf34f552af53e6d955fae5283ab"
SRC_URI_TRUSTED_FIRMWARE_A = "git://git.trustedfirmware.org/TF-A/trusted-firmware-a.git;protocol=https"
SRCBRANCH = "master"

LIC_FILES_CHKSUM:am62lxx = "file://docs/license.rst;md5=1118e32884721c0be33267bd7ae11130"
SRCREV_tfa:am62lxx = "9be79f82555404fc89455fe10d7c1bafc182e833"
SRC_URI_TRUSTED_FIRMWARE_A:am62lxx = "git://github.com/TexasInstruments/arm-trusted-firmware.git;protocol=https"
SRCBRANCH:am62lxx = "ti-master"

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
EXTRA_OEMAKE += "${@ 'K3_PM_SYSTEM_SUSPEND=' + d.getVar('TFA_K3_SYSTEM_SUSPEND') if d.getVar('TFA_K3_SYSTEM_SUSPEND') else ''}"
EXTRA_OEMAKE:append:ti-falcon = " PRELOADED_BL33_BASE=0x82000000 K3_HW_CONFIG_BASE=0x88000000"
