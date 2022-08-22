DESCRIPTION =  "Kernel drivers for the PowerVR SGX chipset found in the TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/omap5-sgx-ddk-linux"
LICENSE = "MIT | GPLv2"
LIC_FILES_CHKSUM = "file://eurasia_km/README;beginline=13;endline=22;md5=74506d9b8e5edbce66c2747c50fcef12"

inherit module features_check

REQUIRED_MACHINE_FEATURES = "gpu"

COMPATIBLE_MACHINE = "pandaboard|beagleboard|ti33x|ti43x|omap-a15|k3"

MACHINE_KERNEL_PR_append = "y"
PR = "${MACHINE_KERNEL_PR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "virtual/kernel"

PROVIDES = "virtual/gpudriver"

BRANCH = "ti-img-sgx/${PV}/k5.10"

SRC_URI = "git://git.ti.com/git/graphics/omap5-sgx-ddk-linux.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"

SRCREV = "eda7780bfd5277e16913c9bc0b0e6892b4e79063"

TARGET_PRODUCT_omap-a15 = "jacinto6evm"
TARGET_PRODUCT_ti33x = "ti335x"
TARGET_PRODUCT_ti43x = "ti437x"
TARGET_PRODUCT_k3 = "ti654x"
TARGET_PRODUCT_beagleboard = "ti343x"
TARGET_PRODUCT_pandaboard = "ti443x"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}" TARGET_PRODUCT=${TARGET_PRODUCT} WINDOW_SYSTEM=nulldrmws'

do_compile_prepend() {
    cd ${S}/eurasia_km/eurasiacon/build/linux2/omap_linux
}

do_install() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/eurasia_km/eurasiacon/binary_omap_linux_nulldrmws_release/target_armhf/kbuild INSTALL_MOD_PATH=${D}${root_prefix} PREFIX=${STAGING_DIR_HOST} modules_install
}

do_install_k3() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/eurasia_km/eurasiacon/binary_omap_linux_nulldrmws_release/target_aarch64/kbuild INSTALL_MOD_PATH=${D}${root_prefix} PREFIX=${STAGING_DIR_HOST} modules_install
}
