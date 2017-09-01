require starterware.inc

PR = "${INC_PR}.0"

ALLOW_EMPTY_${PN} = "1"

CLEANBROKEN = "1"

do_compile() {
    :
}

do_install () {
    install -d ${D}${includedir}/ti/starterware
    find . -name "*.h" -type f | xargs -I {} cp --parents --no-preserve=ownership {} ${D}${includedir}/ti/starterware
}
