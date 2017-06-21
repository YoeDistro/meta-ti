LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://Makefile;beginline=1;endline=35;md5=286cbb5dce3e348294b6b025cff969b3"

COMPATIBLE_MACHINE = "ti33x|ti43x"
DEPENDS = "gcc-arm-none-eabi-native"

require recipes-ti/includes/ti-paths.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

STARTERWARE_GIT_URI = "git://git.ti.com/keystone-rtos/starterware.git"
STARTERWARE_GIT_PROTOCOL = "git"
STARTERWARE_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.STARTERWARE.PROCSDK.02.01.01.03A"
STARTERWARE_SRCREV = "08f65ae3d5ccd19fbaf36040a99de971b685d144"

BRANCH = "${STARTERWARE_GIT_BRANCH}"
SRC_URI = "${STARTERWARE_GIT_URI};protocol=${STARTERWARE_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${STARTERWARE_SRCREV}"
PV = "02.01.01.03A"
PR = "r0"

S = "${WORKDIR}/git"

PARTNO_ti33x = "am335x"
PARTNO_ti43x = "am437x"

export TOOLCHAIN_PATH_A8 = "${GCC_ARM_NONE_TOOLCHAIN}"
export TOOLCHAIN_PATH_A9 = "${GCC_ARM_NONE_TOOLCHAIN}"

do_compile() {
    cd build
    ./release_${PARTNO}.sh
}

do_install() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/starterware
    find -name "*.tar" -exec tar xf {} --no-same-owner -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/starterware \;
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "arch staticdev"
