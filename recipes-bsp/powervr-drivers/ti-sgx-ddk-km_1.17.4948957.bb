DESCRIPTION =  "Kernel drivers for the PowerVR SGX chipset found in the TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/omap5-sgx-ddk-linux"
LICENSE = "MIT | GPLv2"
LIC_FILES_CHKSUM = "file://eurasia_km/README;beginline=13;endline=22;md5=74506d9b8e5edbce66c2747c50fcef12"

inherit module

COMPATIBLE_MACHINE = "k3"

MACHINE_KERNEL_PR_append = "o"
PR = "${MACHINE_KERNEL_PR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "virtual/kernel"

PROVIDES = "omapdrm-pvr"

RPROVIDES_${PN} = "omapdrm-pvr"
RREPLACES_${PN} = "omapdrm-pvr"
RCONFLICTS_${PN} = "omapdrm-pvr"

BRANCH = "ti-img-sgx/${PV}/k4.14"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-linux.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

SRCREV = "b630d462f5fbb86e5f98965ba1af35da1207822f"

TARGET_PRODUCT_k3 = "ti654x"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}" TARGET_PRODUCT=${TARGET_PRODUCT} WINDOW_SYSTEM=nulldrmws'

do_compile_prepend() {
    cd ${S}/eurasia_km/eurasiacon/build/linux2/omap_linux
}

do_install() {
    make -C ${STAGING_KERNEL_DIR} SUBDIRS=${B}/eurasia_km/eurasiacon/binary_omap_linux_nulldrmws_release/target_aarch64/kbuild INSTALL_MOD_PATH=${D} PREFIX=${STAGING_DIR_HOST} modules_install
}
