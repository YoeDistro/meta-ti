DESCRIPTION =  "Kernel drivers for the PowerVR SGX chipset found in the omap5 SoCs"
HOMEPAGE = "http://git.ti.com"
LICENSE = "MIT | GPLv2"
LIC_FILES_CHKSUM = "file://eurasia_km/README;beginline=13;endline=22;md5=2b841bfc03386bb4d8d9381b79d33898"

inherit module

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15"

MACHINE_KERNEL_PR_append = "h"
PR = "${MACHINE_KERNEL_PR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

BRANCH_omap-a15 = "dra7/k4.1"
BRANCH_ti33x = "am4/k4.1"
BRANCH_ti43x = "am4/k4.1"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-linux.git;protocol=git;branch=${BRANCH}"
S = "${WORKDIR}/git"

SRCREV_omap-a15 = "e06c0a4e11401534b938b9a7b1c3f27a65db871f"
SRCREV_ti33x = "696eaf6cff0e4ab6abedc3ff639ec158f8a6177f"
SRCREV_ti43x = "696eaf6cff0e4ab6abedc3ff639ec158f8a6177f"

PVR_NULLDRM_ti33x = "1"
PVR_NULLDRM_ti43x = "1"
PVR_NULLDRM_omap-a15 = "0"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}" PVR_NULLDRM=${PVR_NULLDRM}'

DEVICE_SUB_DIR_omap-a15 = "omap5430"
DEVICE_SUB_DIR_ti33x = "omap335x"
DEVICE_SUB_DIR_ti43x = "omap437x"

do_compile_prepend() {
    cd ${S}/eurasia_km/eurasiacon/build/linux2/${DEVICE_SUB_DIR}_linux
}

do_install() {
    make -C ${STAGING_KERNEL_DIR} SUBDIRS=${B}/eurasia_km/eurasiacon/binary2_${DEVICE_SUB_DIR}_linux_release/target/kbuild INSTALL_MOD_PATH=${D} PREFIX=${STAGING_DIR_HOST} modules_install
}
