DESCRIPTION = "TI OpenMP RTOS"
HOMEPAGE = "git://git.ti.com/git/openmp/ti-openmp-dsp-runtime.git"
LICENSE = "BSD-3-Clause"

require recipes-ti/includes/ti-paths.inc

inherit features_check

REQUIRED_MACHINE_FEATURES = "dsp"
REQUIRED_DISTRO_FEATURES = "openmp"

PV = "2_06_03_00"
PR = "r0"

OPENMP_RTOS_GIT_URI = "git://git.ti.com/git/openmp/ti-openmp-dsp-runtime.git"
OPENMP_RTOS_GIT_PROTOCOL = "https"
OPENMP_RTOS_GIT_BRANCH = "master"

BRANCH = "${OPENMP_RTOS_GIT_BRANCH}"
SRC_URI = "${OPENMP_RTOS_GIT_URI};protocol=${OPENMP_RTOS_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "c090eb664d9815a36ead0e14f31e102590680fb8"

LIC_FILES_CHKSUM = "file://docs/license/omp_manifest_template.html;md5=61a6972303c0447b7c056195d7ebafee"

DEPENDS = "common-csl-ip-rtos doxygen-native libulm ti-xdctools-native ti-ipc-rtos ti-sysbios ti-cgt6x-native zip-native"

COMPATIBLE_MACHINE = "omap-a15"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
export IPC_DIR = "${IPC_INSTALL_DIR}"
export XDC_DIR = "${XDC_INSTALL_DIR}"
export BIOS_DIR = "${SYSBIOS_INSTALL_DIR}"
export ULM_DIR ="${STAGING_DIR_TARGET}/usr/share/ti/ulm"
export C6636_PDK_DIR ="${PDK_INSTALL_DIR}"
export AM572_PDK_DIR ="${PDK_INSTALL_DIR}"
export XDCCGROOT = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"

export BUILD_AM572 = "0"

BUILD_AM572:omap-a15 = "1"

RELEASE_TARGET = ""
RELEASE_TARGET:omap-a15 = "am57xx"

do_compile() {
    make -f utils/product/Makefile .zipfile
}

do_install() {
    install -d ${D}${OMP_INSTALL_DIR_RECIPE}
    cp -r ${S}/exports/openmp_dsp_${RELEASE_TARGET}_*/. -d  ${D}${OMP_INSTALL_DIR_RECIPE}
}

ALLOW_EMPTY:${PN} = "1"

FILES:${PN}-dev += " \
    ${OMP_INSTALL_DIR_RECIPE}/ \
"

INSANE_SKIP:${PN}-dev = "arch"
