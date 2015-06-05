require common-csl-ip.inc

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc

PR = "r1"

DEPENDS = "ti-xdctools ti-cgt6x-native ti-sysbios"

S = "${WORKDIR}/ti/csl"

export C6X_GEN_INSTALL_PATH="${STAGING_DIR_NATIVE}/usr"
export XDCCGROOT="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export XDCPATH="${XDCCGROOT}/include;${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages"

do_configure() {
    sed -i "s/\ \"\.\\\\\\\\\"\ +//" src/Module.xs
    find -name "*.xs" -exec sed -i "s/ofd6x\.exe/ofd6x/" {} \;
    find -name "*.xs" -exec sed -i "s/sectti\.exe/sectti/" {} \;
    find -name "*.xs" -exec sed -i "/\.chm/d" {} \;
    find -name "*.xs" -exec sed -i "s/pasm\_dos/pasm\_linux/" {} \;
}

do_compile() {
    ${XDC_INSTALL_DIR}/xdc .make
    ${XDC_INSTALL_DIR}/xdc clean
    ${XDC_INSTALL_DIR}/xdc release
}

do_install () {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages
    find -name "*.tar" -exec tar xf {} -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages \;
}

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${PDK_INSTALL_DIR_RECIPE}/packages"
