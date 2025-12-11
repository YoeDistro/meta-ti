# NOTE: This .inc file with customizations only gets included for K3 platforms

FILESEXTRAPATHS:prepend := "${THISDIR}/trusted-firmware-a:"

PV = "2.13+git"

LIC_FILES_CHKSUM = "file://docs/license.rst;md5=6ed7bace7b0bc63021c6eba7b524039e"
SRCREV_tfa = "e0c4d3903b382bf34f552af53e6d955fae5283ab"
SRC_URI_TRUSTED_FIRMWARE_A = "git://git.trustedfirmware.org/TF-A/trusted-firmware-a.git;protocol=https"
SRCBRANCH = "master"

LIC_FILES_CHKSUM:am62lxx = "file://docs/license.rst;md5=1118e32884721c0be33267bd7ae11130"
SRCREV_tfa:am62lxx = "7252a99fb781075db1987d8ce2c7e37324297427"
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

# LPM support patches for Jacinto platforms (J7200, J742S2, J784S4)
TFA_JACINTO_LPM_PATCHES = " \
    file://0001-feat-k3-choose-cluster_start_id-depending-on-the-soc.patch \
    file://0002-feat-ti-add-message-to-encrypt-tfa-during-suspend.patch \
    file://0003-feat-k3-handle-suspend-in-case-of-LPM_BOARDCFG_MANAG.patch \
"

SRC_URI:append:j7200 = " ${TFA_JACINTO_LPM_PATCHES}"
SRC_URI:append:j742s2 = " ${TFA_JACINTO_LPM_PATCHES}"
SRC_URI:append:j784s4 = " ${TFA_JACINTO_LPM_PATCHES}"
