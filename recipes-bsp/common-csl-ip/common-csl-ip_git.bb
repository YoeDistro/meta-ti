require common-csl-ip.inc

PR = "${INC_PR}.0"

ALLOW_EMPTY_${PN} = "1"

CLEANBROKEN = "1"

do_compile() {
    :
}

do_install () {
    install -d ${D}${includedir}/ti/csl
    find . -name "*.h" -type f | xargs -I {} cp --parents {} ${D}${includedir}/ti/csl
    find ./src/ip/serdes_sb/V0 -name "*.c" -type f | xargs -I {} cp --parents {} ${D}${includedir}/ti/csl
}
