FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://omap-tty.rules \
    file://firmware.rules \
"

PR_append = ".3"

install_ti_rules() {
    install -m 0644 ${WORKDIR}/omap-tty.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/firmware.rules ${D}${sysconfdir}/udev/rules.d/
}

do_install_append_ti33x() {
    install_ti_rules
}

do_install_append_ti43x() {
    install_ti_rules
}

do_install_append_omap-a15() {
    install_ti_rules
}

do_install_append_keystone() {
    install_ti_rules
}
