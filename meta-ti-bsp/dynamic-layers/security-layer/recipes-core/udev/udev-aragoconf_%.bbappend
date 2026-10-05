do_install:append() {
    if ${@'true' if d.getVar('DM_VERITY_IMAGE') else 'false'}; then
        : > ${D}${libdir}/udev/rules.d/50-arago.rules
    fi
}
