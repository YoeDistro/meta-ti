DESCRIPTION = "QMSS PDSP firmware"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENCE.ti-keystone;md5=3a86335d32864b0bef996bee26cc0f2c"

PV = "1.0.0.9"
PR = "r0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "keystone"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "474abaf61ef4778df84b74c4a50f740149b38115"
BRANCH ?= "master"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
ORIGIN = "ks2_qmss_pdsp_acc48_k2_le_1_0_0_9.bin"
TARGET = "ks2_qmss_pdsp_acc48.bin"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 ${S}/ti-keystone/${ORIGIN} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} = "${base_libdir}/firmware"
