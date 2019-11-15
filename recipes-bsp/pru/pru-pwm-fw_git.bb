SUMMARY = "Programmable Real-time Unit PWM Firmware"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=32;md5=893d6a0cf1644338ea96642c0db97f59"

require recipes-ti/includes/ti-paths.inc

COMPATIBLE_MACHINE = "am65xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"



inherit ti-pdk-fetch

TI_PDK_COMP = "ti.drv.pruss.example.apps.icssg_pwm.firmware.src"

PE = "1"

DEPENDS = "ti-cgt-pru-native pru-icss common-csl-ip-rtos"


EXTRA_OEMAKE += " \
    PRU_CGT="${TI_CGT_PRU_INSTALL_DIR}" \
    PRU_SSP="${STAGING_DIR_TARGET}/usr" \
    PDK_INSTALL_DIR="${STAGING_DATADIR}/ti/ti-pdk-tree/packages" \
"

do_compile() {
	oe_runmake
}

do_install() {
	install -d ${D}/lib/firmware/ti-pruss
	install -m 0644 ${S}/gen/pwm.out ${D}/lib/firmware/ti-pruss/am65x-pru0-pwm-fw.elf
}

FILES_${PN} = "/lib/firmware"

INSANE_SKIP_${PN} = "arch"
