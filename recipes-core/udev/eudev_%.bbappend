FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:ti-soc = " \
    file://omap-tty.rules \
    file://firmware.rules \
"

PR:append:ti-soc = ".3"

do_install:append:ti-soc() {
    install -m 0644 ${WORKDIR}/omap-tty.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/firmware.rules ${D}${sysconfdir}/udev/rules.d/
}
