FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_ti-soc = " \
    file://omap-tty.rules \
    file://firmware.rules \
"

PR_append_ti-soc = ".3"

do_install_append_ti-soc() {
    install -m 0644 ${WORKDIR}/omap-tty.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/firmware.rules ${D}${sysconfdir}/udev/rules.d/
}
