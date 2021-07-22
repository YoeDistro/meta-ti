SUMMARY = "TI SCI firmware (SYSFW)"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "2021.05"
INC_PR = "r0"
# RTOS firmware version
CORESDK_RTOS_VERSION = "08.00.00.32"

SRCREV = "793309716bce2e443f798dd5c5f0d8dd310970c0"
BRANCH ?= "ti-linux-firmware"
SRCREV_imggen = "b65ae80847f5a0d1885ed6e4de4d9d635544053c"
SRCREV_FORMAT = "imggen"

SRC_URI = " \
	git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH} \
	git://git.ti.com/k3-image-gen/k3-image-gen.git;protocol=git;branch=master;destsuffix=imggen;name=imggen \
"

# This is a protection in case the definition is not present in other distros.
ARAGO_BRAND ?= ""

# Mainline u_boot 2021.07 and upstream kernel v5.13 tag cannot use new SPL address
# in K3-Imagegen
SRC_URI += "\
	${@oe.utils.conditional('ARAGO_BRAND', 'mainline', '\
	file://0001-Revert-soc-am64x-Makefile-Move-the-SPL-Load-address-.patch;patchdir=../imggen \
	', '', d)} \
"

S = "${WORKDIR}/git"
