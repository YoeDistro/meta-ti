SUMMARY =  "Kernel drivers for the PowerVR Rogue GPU found in the TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-driver"
LICENSE = "MIT | GPL-2.0-only"
LIC_FILES_CHKSUM = "file://README;beginline=14;endline=19;md5=0403c7dea01a2b8232261e805325fac2"

inherit module

PROVIDES = "virtual/gpudriver"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "j721e|j721s2|j784s4|j742s2|am62xx|am62pxx|j722s"

DEPENDS = "virtual/kernel"

BRANCH = "linuxws/scarthgap/k6.12/${PV}"

SRC_URI = "git://git.ti.com/git/graphics/ti-img-rogue-driver.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"

SRCREV = "a838ac0074db640ebd1b64be6364417b1bbca3cd"

TARGET_PRODUCT:j721e = "j721e_linux"
TARGET_PRODUCT:j721s2 = "j721s2_linux"
TARGET_PRODUCT:j784s4 = "j784s4_linux"
TARGET_PRODUCT:j742s2 = "j784s4_linux"
TARGET_PRODUCT:am62xx = "am62_linux"
TARGET_PRODUCT:am62pxx = "am62p_linux"
TARGET_PRODUCT:j722s = "j722s_linux"
PVR_BUILD = "release"
PVR_WS = "lws-generic"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}" BUILD=${PVR_BUILD} PVR_BUILD_DIR=${TARGET_PRODUCT} WINDOW_SYSTEM=${PVR_WS}'

do_install() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/binary_${TARGET_PRODUCT}_${PVR_WS}_${PVR_BUILD}/target_aarch64/kbuild INSTALL_MOD_PATH=${D}${root_prefix} PREFIX=${STAGING_DIR_HOST} modules_install
}

RRECOMMENDS:${PN} += "ti-img-rogue-umlibs"
