FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://omap-tty.rules \
    file://firmware.rules \
"

PR_append = ".2"

install_omap_rules() {
    install -m 0644 ${WORKDIR}/omap-tty.rules ${D}${sysconfdir}/udev/rules.d/
}

do_install_append_ti33x() {
    install_omap_rules
}

do_install_append_ti43x() {
    install_omap_rules
}

do_install_append_omap-a15() {
    install_omap_rules
}

do_install_append_keystone() {
    install_omap_rules
}
