require common-csl-ip.inc

PR = "r3"

ALLOW_EMPTY_${PN} = "1"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}${includedir}/ti/csl
    find . -name "*.h" -type f | xargs -I {} cp --parents {} ${D}${includedir}/ti/csl
    find ./src/ip/serdes_sb/V0 -name "*.c" -type f | xargs -I {} cp --parents {} ${D}${includedir}/ti/csl
}
