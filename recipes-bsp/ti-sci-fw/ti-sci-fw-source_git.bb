# ti-sci-fw builds and packages multiple config variants via multiconfig
# Let's take a page from gcc-source as a common recipe for all gcc stages,
# but also to provide a single package for MAINMACHINE of all multiconfigs

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

SUMMARY += "- sources"

COMPATIBLE_MACHINE = "k3"

EXCLUDE_FROM_WORLD = "1"
INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = ""

PACKAGES = "${PN}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

SRCIPK_SRC_DIR = "${WORKDIR}/imggen"
SRCIPK_INSTALL_DIR = "board-support/k3-image-gen-${PV}"
FILES_${PN} = "${SRCIPK_INSTALL_DIR}"

do_install() {
    # Copy sources for packaging
    mkdir -p ${D}/${SRCIPK_INSTALL_DIR}
    if [ -e ${SRCIPK_SRC_DIR} ]; then
        if [ "${SRCIPK_SRC_DIR}" = "${WORKDIR}" ]; then
            excludes='--exclude ./temp --exclude ${D}'
        fi
        tar -C ${SRCIPK_SRC_DIR} -cO $excludes . | tar -C ${D}/${SRCIPK_INSTALL_DIR} -xpf -
    fi

    # Fix up patches/ directory to contain actual patches instead of symlinks
    if [ -e ${D}/${SRCIPK_INSTALL_DIR}/patches ]
    then
        mv ${D}/${SRCIPK_INSTALL_DIR}/patches ${D}/${SRCIPK_INSTALL_DIR}/patches-links
        cp -rL ${D}/${SRCIPK_INSTALL_DIR}/patches-links ${D}/${SRCIPK_INSTALL_DIR}/patches
        rm -rf ${D}/${SRCIPK_INSTALL_DIR}/patches-links
    fi
}

# Do not perform any QA checks on source package
INSANE_SKIP_${PN} += "${ALL_QA}"
