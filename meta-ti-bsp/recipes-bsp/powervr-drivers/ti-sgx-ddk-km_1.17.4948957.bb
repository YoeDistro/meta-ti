SUMMARY =  "Kernel drivers for the PowerVR SGX chipset found in the TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/omap5-sgx-ddk-linux"
LICENSE = "MIT | GPL-2.0-only"
LIC_FILES_CHKSUM = "file://GPL-COPYING;md5=60422928ba677faaa13d6ab5f5baaa1e"

inherit module

PROVIDES = "virtual/gpudriver"

COMPATIBLE_MACHINE = "ti33x|ti43x|am57xx|am65xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "virtual/kernel"

BRANCH = "${PV}/mesa/k6.1"

SRC_URI = "git://git.ti.com/git/graphics/omap5-sgx-ddk-linux.git;protocol=https;branch=${BRANCH}"

SRCREV = "9ae0fa4998b1c624408945e062bf8fb0ea7efb9d"

TARGET_PRODUCT:ti33x = "ti335x_linux"
TARGET_PRODUCT:ti43x = "ti437x_linux"
TARGET_PRODUCT:am57xx = "ti572x_linux"
TARGET_PRODUCT:am65xx = "ti654x_linux"
PVR_BUILD = "release"
PVR_WS = "lws-generic"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}" BUILD=${PVR_BUILD} \
WINDOW_SYSTEM=${PVR_WS} PVR_BUILD_DIR=${TARGET_PRODUCT}'

# There are useful flags here that are interpreted by the final kbuild pass
# These variables are not necessary when compiling outside of Yocto
export KERNEL_CC
export KERNEL_LD
export KERNEL_AR
export KERNEL_OBJCOPY
export KERNEL_STRIP

do_install() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/eurasiacon/binary_${TARGET_PRODUCT}_${PVR_WS}_${PVR_BUILD}/target_armhf/kbuild INSTALL_MOD_PATH=${D}${root_prefix} PREFIX=${STAGING_DIR_HOST} modules_install
}

do_install:am65xx() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/eurasiacon/binary_${TARGET_PRODUCT}_${PVR_WS}_${PVR_BUILD}/target_aarch64/kbuild INSTALL_MOD_PATH=${D}${root_prefix} PREFIX=${STAGING_DIR_HOST} modules_install
}

RRECOMMENDS:${PN} += "ti-sgx-ddk-um"
