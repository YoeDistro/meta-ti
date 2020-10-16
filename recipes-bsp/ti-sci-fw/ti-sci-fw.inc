SUMMARY = "TI SCI firmware (SYSFW)"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "2020.08-rc3"

SRCREV = "8a22979dc944f6f4f867e2ac637c51a317efaacd"
BRANCH ?= "ti-linux-firmware"
SRCREV_imggen = "36fe2d183a734f65b9adbd3097e5d51a6f931521"
SRCREV_FORMAT = "imggen"

SRC_URI = " \
	git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH} \
	git://git.ti.com/k3-image-gen/k3-image-gen.git;protocol=git;branch=master;destsuffix=imggen;name=imggen \
"

S = "${WORKDIR}/git"
