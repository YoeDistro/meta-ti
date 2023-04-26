SUMMARY = "TI SCI firmware (SYSFW)"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "2022.01"
INC_PR = "r3"

# Firmware versions
CORESDK_RTOS_VERSION = "08.02.00.04"
SERDES_FW_VERSION = "3.3.0.2c"
NETCP_SA_FW_VERSION = "1.0.0"
NETCP_PA_FW_VERSION = "3.0.2.3"
QMSS_PDSP_FW_VERSION = "1.0.0.9"
PRUETH_FW_AM65X_VERSION = "08.00.00.20"
PRUETH_FW_AM65X_SR2_VERSION = "02.02.09.07"
GOODIX_FW_VERSION = "1.0.0.0"
CADENCE_MHDP_FW_VERSION = "1.2.17"
IMG_DEC_FW_VERSION = "1.0"
CNM_WAVE521_FW_VERSION = "1.0.00"
TIFS_LPM_STUB_FW_VERSION = "08.03.02"

TI_LINUX_FW_SRCREV ?= "760434de173d1957823042b3c2daf0f45e1191cd"
SRCREV = "${TI_LINUX_FW_SRCREV}"

BRANCH ?= "ti-linux-firmware"

K3_IMAGE_GEN_SRCREV ?= "184ab513e6acb161c5f77a593ef976f3a4d93cda"
SRCREV_imggen = "${K3_IMAGE_GEN_SRCREV}"
SRCREV_FORMAT = "imggen"

K3_IMAGE_GEN_BRANCH ?= "master"

SRC_URI = " \
	git://git.ti.com/git/processor-firmware/ti-linux-firmware.git;protocol=https;branch=${BRANCH} \
	git://git.ti.com/git/k3-image-gen/k3-image-gen.git;protocol=https;branch=${K3_IMAGE_GEN_BRANCH};destsuffix=imggen;name=imggen \
"

S = "${WORKDIR}/git"
