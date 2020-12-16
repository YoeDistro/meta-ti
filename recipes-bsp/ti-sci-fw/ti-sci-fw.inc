SUMMARY = "TI SCI firmware (SYSFW)"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "2020.08b"
PV_am64xx-evm-k3r5 = "2020.23"

SRCREV = "078c1bbf3d0a05b4045d4091c32e91ee1ae15ff8"
BRANCH ?= "ti-linux-firmware"
SRCREV_imggen = "6d023674b0655f1a7bba192d2600c08be314e9ef"
SRCREV_FORMAT = "imggen"

SRC_URI = " \
	git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH} \
	git://git.ti.com/k3-image-gen/k3-image-gen.git;protocol=git;branch=master;destsuffix=imggen;name=imggen \
"

S = "${WORKDIR}/git"
