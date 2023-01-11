DESCRIPTION =  "Kernel drivers for the PowerVR Rogue GPU found in the TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-driver"
LICENSE = "MIT | GPLv2"
LIC_FILES_CHKSUM = "file://README;beginline=14;endline=19;md5=0403c7dea01a2b8232261e805325fac2"

inherit module features_check

REQUIRED_MACHINE_FEATURES = "gpu"

MACHINE_KERNEL_PR_append = "b"
PR = "${MACHINE_KERNEL_PR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "j7-evm|j7-hs-evm|j721s2-evm|j721s2-hs-evm|j784s4-evm|j784s4-hs-evm|am62xx"

DEPENDS = "virtual/kernel"

PROVIDES = "virtual/gpudriver"

BRANCH = "linuxws/dunfell/k5.10/${PV}_unified_fw_pagesize"

SRC_URI = " \
    git://git.ti.com/git/graphics/ti-img-rogue-driver.git;protocol=https;branch=${BRANCH} \
    file://0001-compiler-support-OpenEmbedded-nodistro-internal-aarc.patch \
"

S = "${WORKDIR}/git"

SRCREV = "1dd6291a5cad4f2b909fc2a14bd717a3bc5f0bb2"

TARGET_PRODUCT_j7-evm = "j721e_linux"
TARGET_PRODUCT_j7-hs-evm = "j721e_linux"
TARGET_PRODUCT_j721s2-evm = "j721s2_linux"
TARGET_PRODUCT_j721s2-hs-evm = "j721s2_linux"
TARGET_PRODUCT_j784s4-evm = "j784s4_linux"
TARGET_PRODUCT_j784s4-hs-evm = "j784s4_linux"
TARGET_PRODUCT_am62xx = "am62_linux"
PVR_BUILD = "release"
PVR_WS = "wayland"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}" BUILD=${PVR_BUILD} PVR_BUILD_DIR=${TARGET_PRODUCT} WINDOW_SYSTEM=${PVR_WS}'

do_install() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/binary_${TARGET_PRODUCT}_${PVR_WS}_${PVR_BUILD}/target_aarch64/kbuild INSTALL_MOD_PATH=${D}${root_prefix} PREFIX=${STAGING_DIR_HOST} modules_install
}
