DESCRIPTION =  "Kernel drivers for the PowerVR Rogue GPU found in the TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-driver"
LICENSE = "MIT | GPLv2"
LIC_FILES_CHKSUM = "file://README;beginline=15;endline=20;md5=0403c7dea01a2b8232261e805325fac2"

inherit module features_check

REQUIRED_MACHINE_FEATURES = "gpu"

MACHINE_KERNEL_PR_append = "g"
PR = "${MACHINE_KERNEL_PR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "j7"

DEPENDS = "virtual/kernel"

PROVIDES = "virtual/gpudriver"

BRANCH = "linuxws/zeus/k5.4/${PV}"

SRC_URI = "git://git.ti.com/graphics/ti-img-rogue-driver.git;branch=${BRANCH}"

S = "${WORKDIR}/git"

SRCREV = "f58bea1084c5004f129943a53b94ee1446e9c3a7"

PVR_SOC = "j721e_linux"
PVR_BVNC = "22.104.208.318"
PVR_BUILD = "release"
PVR_WS = "wayland"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}" RGX_BVNC=${PVR_BVNC} BUILD=${PVR_BUILD} PVR_BUILD_DIR=${PVR_SOC} WINDOW_SYSTEM=${PVR_WS}'

do_install() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/binary_${PVR_SOC}_${PVR_WS}_${PVR_BUILD}/target_aarch64/kbuild INSTALL_MOD_PATH=${D} PREFIX=${STAGING_DIR_HOST} modules_install
}
