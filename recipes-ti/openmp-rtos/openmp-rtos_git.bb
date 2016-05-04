DESCRIPTION = "TI OpenMP RTOS"
HOMEPAGE = "git://git.ti.com/openmp/ti-openmp-dsp-runtime.git"
LICENSE = "BSD"

require recipes-ti/includes/ti-paths.inc

PV = "2_02_01_03"
PR = "r1"

OPENMP_RTOS_GIT_URI = "git://git.ti.com/openmp/ti-openmp-dsp-runtime.git"
OPENMP_RTOS_GIT_PROTOCOL = "git"
OPENMP_RTOS_GIT_BRANCH = "master"

BRANCH = "${OPENMP_RTOS_GIT_BRANCH}"
SRC_URI = "${OPENMP_RTOS_GIT_URI};protocol=${OPENMP_RTOS_GIT_PROTOCOL};branch=${BRANCH} \
"

SRCREV = "9bf371e9618297f8783113bff05995800535c27d"

LIC_FILES_CHKSUM = "file://docs/license/omp_manifest_template.html;md5=61a6972303c0447b7c056195d7ebafee"

DEPENDS = "common-csl-ip-rtos ti-xdctools ti-ipc-rtos ti-sysbios ti-cgt6x-native libulm zip-native"
DEPENDS_append_k2hk-evm = " qmss-lld-rtos cppi-lld-rtos"
DEPENDS_append_k2e-evm = " qmss-lld-rtos cppi-lld-rtos"
DEPENDS_append_k2l-evm = " qmss-lld-rtos cppi-lld-rtos"

COMPATIBLE_MACHINE = "keystone|omap-a15"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
export IPC_DIR = "${IPC_INSTALL_DIR}"
export XDC_DIR = "${XDC_INSTALL_DIR}"
export BIOS_DIR = "${SYSBIOS_INSTALL_DIR}"
export ULM_DIR ="${STAGING_DIR_TARGET}/usr/share/ti/ulm"
export C6636_PDK_DIR ="${PDK_INSTALL_DIR}"
export AM572_PDK_DIR ="${PDK_INSTALL_DIR}"
export K2G_PDK_DIR ="${PDK_INSTALL_DIR}"
export XDCCGROOT = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"

export BUILD_K2H = "0"
export BUILD_AM572 = "0"
export BUILD_K2G = "0"

BUILD_K2H_keystone = "1"
BUILD_AM572_omap-a15 = "1"
BUILD_K2H_k2g-evm = "0"
BUILD_K2G_k2g-evm = "1"

RELEASE_TARGET = ""
RELEASE_TARGET_keystone = "k2x"
RELEASE_TARGET_omap-a15 = "am57xx"
RELEASE_TARGET_k2g-evm = "k2g"

do_compile() {
    make -f utils/product/Makefile .zipfile
}

do_install() {
    install -d ${D}${OMP_INSTALL_DIR_RECIPE}
    cp -r ${S}/exports/openmp_dsp_${RELEASE_TARGET}_*/. -d  ${D}${OMP_INSTALL_DIR_RECIPE}
}

ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-dev += " \
    ${OMP_INSTALL_DIR_RECIPE}/ \
"

INSANE_SKIP_${PN}-dev = "arch"
