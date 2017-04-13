DESCRIPTION = "Kernel drivers for the Vivante GC320 chipset found in TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/ti-gc320-driver"
LICENSE = "MIT | GPLv2"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=78d9818a51b9a8e9bb89dea418bac297"

inherit module

MACHINE_KERNEL_PR_append = "d"
PR = "${MACHINE_KERNEL_PR}"

# Need to branch out with ${PV} var
BRANCH = "ti-${PV}-k4.9"

SRCREV = "295443bf56f2100b7b2714c2c30b31ba86e96a0c"

SRC_URI = "git://git.ti.com/graphics/ti-gc320-driver.git;protocol=git;branch=${BRANCH}"
S = "${WORKDIR}/git/src"

EXTRA_OEMAKE += "-f Kbuild AQROOT=${S} KERNEL_DIR=${STAGING_KERNEL_DIR} TOOLCHAIN_PATH=${TOOLCHAIN_PATH} CROSS_COMPILE=${TARGET_PREFIX} ARCH_TYPE=${TARGET_ARCH}"

do_install() {
    install -d ${D}/${base_libdir}/modules/${KERNEL_VERSION}/extra
    install -m 644 ${S}/galcore.ko ${D}/${base_libdir}/modules/${KERNEL_VERSION}/extra
}
