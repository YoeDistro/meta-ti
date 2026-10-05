# Nothing should ever get auto-mounted under dm-verity, so just kill the automounter.
do_install:append() {
    if ${@'true' if d.getVar('DM_VERITY_IMAGE') else 'false'}; then
        : > ${D}${sysconfdir}/udev/rules.d/automount.rules
    fi
}
