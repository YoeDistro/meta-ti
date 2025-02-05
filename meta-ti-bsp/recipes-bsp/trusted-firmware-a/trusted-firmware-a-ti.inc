PV:k3 = "2.12+git"
SRCREV_tfa:k3 = "5227171c079d0c7d22a6d37b5cec079c8c5bb4b1"
LIC_FILES_CHKSUM:k3 = "file://docs/license.rst;md5=1118e32884721c0be33267bd7ae11130"
SRC_URI:k3 = "git://github.com/TexasInstruments/arm-trusted-firmware.git;protocol=https;name=tfa;branch=ti-master"
COMPATIBLE_MACHINE:k3 = "k3"
TFA_BUILD_TARGET:k3 = "all"
TFA_INSTALL_TARGET:k3 = "bl31"
TFA_SPD:k3 = "opteed"

# For am62lxx, install bl1 & bl31 using TFA_INSTALL_TARGET
TFA_INSTALL_TARGET:am62lxx = "bl31 bl1"

EXTRA_OEMAKE:append:k3 = "${@ ' K3_USART=' + d.getVar('TFA_K3_USART') if d.getVar('TFA_K3_USART') else ''}"
EXTRA_OEMAKE:append:k3 = "${@ ' K3_PM_SYSTEM_SUSPEND=' + d.getVar('TFA_K3_SYSTEM_SUSPEND') if d.getVar('TFA_K3_SYSTEM_SUSPEND') else ''}"
