PV:k3 = "2.11+git"
SRCREV_tfa:k3 = "58b25570c9ef91753b14c2103f45f4be9dddb696"
LIC_FILES_CHKSUM:k3 = "file://docs/license.rst;md5=83b7626b8c7a37263c6a58af8d19bee1"
SRC_URI:k3 = "git://git.trustedfirmware.org/TF-A/trusted-firmware-a.git;protocol=https;name=tfa;branch=master"
COMPATIBLE_MACHINE:k3 = "k3"
TFA_BUILD_TARGET:k3 = "all"
TFA_INSTALL_TARGET:k3 = "bl31"
TFA_SPD:k3 = "opteed"

EXTRA_OEMAKE:append:k3 = "${@ ' K3_USART=' + d.getVar('TFA_K3_USART') if d.getVar('TFA_K3_USART') else ''}"
EXTRA_OEMAKE:append:k3 = "${@ ' K3_PM_SYSTEM_SUSPEND=' + d.getVar('TFA_K3_SYSTEM_SUSPEND') if d.getVar('TFA_K3_SYSTEM_SUSPEND') else ''}"
