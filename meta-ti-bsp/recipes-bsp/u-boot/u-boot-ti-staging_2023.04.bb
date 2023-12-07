require u-boot-ti.inc

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2023.04"

SRCREV = "b0d717b732ee28e446baf94522b3491e590f7fbb"

do_install:append:am62xx() {
	install -d ${D}/boot
	install -m 0644 ${S}/tools/logos/ti_logo_414x97_32bpp.bmp.gz ${D}/boot
}

do_deploy:append:am62xx() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${S}/tools/logos/ti_logo_414x97_32bpp.bmp.gz ${DEPLOYDIR}
}
