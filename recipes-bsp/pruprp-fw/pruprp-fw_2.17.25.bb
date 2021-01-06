SUMMARY = "PRU PRP firmware for AM335x/AM437x/AM57xx"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PE = "1"
PR = "r0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "ti33x|ti43x|am57xx-evm|am57xx-hs-evm"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "0038ff0ecda8dae83c65d454c85776c6a4ef8e63"
BRANCH ?= "ti-linux-firmware"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

TARGET = ""
TARGET_ti33x = "am335x-pru0-pruprp-fw.elf am335x-pru1-pruprp-fw.elf"
TARGET_ti43x = "am437x-pru0-pruprp-fw.elf am437x-pru1-pruprp-fw.elf"
TARGET_am57xx-evm = "am57xx-pru0-pruprp-fw.elf am57xx-pru1-pruprp-fw.elf"
TARGET_am57xx-hs-evm = "am57xx-pru0-pruprp-fw.elf am57xx-pru1-pruprp-fw.elf"


do_install() {
	install -d ${D}${base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${base_libdir}/firmware/ti-pruss/$f
	done
}

FILES_${PN} = "${base_libdir}/firmware"

INSANE_SKIP_${PN} = "arch"
