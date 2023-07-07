require u-boot-ti.inc

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

PR = "r0"

BRANCH = "ti-u-boot-2023.04"

SRCREV = "24098ea90dbaac7b16958e2f7d9f7a412ef1522a"

do_install:append:am62xx() {
	install -d ${D}/boot
	install -m 0644 ${S}/tools/logos/ti_logo_414x97_32bpp.bmp.gz ${D}/boot
}

do_deploy:append:am62xx() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${S}/tools/logos/ti_logo_414x97_32bpp.bmp.gz ${DEPLOYDIR}
}
