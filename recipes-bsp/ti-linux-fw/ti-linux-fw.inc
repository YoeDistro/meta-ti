SUMMARY = "TI SCI firmware (SYSFW)"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "2021.09a"
INC_PR = "r1"

# Firmware versions
CORESDK_RTOS_VERSION = "08.01.00.02"
SERDES_FW_VERSION = "3.3.0.2c"
NETCP_SA_FW_VERSION = "1.0.0"
NETCP_PA_FW_VERSION = "3.0.2.3"
QMSS_PDSP_FW_VERSION = "1.0.0.9"
PRUETH_FW_AM65X_VERSION = "08.00.00.20"
PRUETH_FW_AM65X_SR2_VERSION = "02.02.09.07"
GOODIX_FW_VERSION = "1.0.0.0"
CADENCE_MHDP_FW_VERSION = "1.2.17"
IMG_DEC_FW_VERSION = "1.0"

TI_LINUX_FW_SRCREV ?= "c96a734a059889e571dcf30c0638157c690a6e6e"
SRCREV = "${TI_LINUX_FW_SRCREV}"

BRANCH ?= "ti-linux-firmware"

K3_IMAGE_GEN_SRCREV ?= "489c767a153ff26e9230746e04dd4b1ad0809901"
SRCREV_imggen = "${K3_IMAGE_GEN_SRCREV}"
SRCREV_FORMAT = "imggen"

SRC_URI = " \
	git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH} \
	git://git.ti.com/k3-image-gen/k3-image-gen.git;protocol=git;branch=master;destsuffix=imggen;name=imggen \
"

S = "${WORKDIR}/git"
