DESCRIPTION = "PMMC firmware"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENCE.ti-keystone;md5=3a86335d32864b0bef996bee26cc0f2c"

PV = "0.1.0.63"
PR = "r0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "k2g-evm"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "474abaf61ef4778df84b74c4a50f740149b38115"
BRANCH ?= "master"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
TARGET = "pmmc-firmware.bin"

do_install() {
	install -d ${D}/boot
	install -m 0644 ${S}/ti-keystone/${TARGET} ${D}/boot/${TARGET}
}

FILES_${PN} = "/boot"

inherit deploy

do_deploy () {
	install -d ${DEPLOYDIR}
	install -m 0644 ${S}/ti-keystone/${TARGET} ${DEPLOYDIR}/${TARGET}
}

addtask deploy before do_build after do_compile
