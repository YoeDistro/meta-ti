SUMMARY = "TI SCI firmware (SYSFW)"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "2020.07-rc3"

SRCREV = "f5438e998a7a8d23722fa4dcd1cdb62dec7a41d2"
BRANCH ?= "ti-linux-firmware"
SRCREV_imggen = "09256d5c21e6281d224646c1d4163d4dd0925238"
SRCREV_FORMAT = "imggen"

SRC_URI = " \
	git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH} \
	git://git.ti.com/k3-image-gen/k3-image-gen.git;protocol=git;branch=master;destsuffix=imggen;name=imggen \
"

S = "${WORKDIR}/git"
