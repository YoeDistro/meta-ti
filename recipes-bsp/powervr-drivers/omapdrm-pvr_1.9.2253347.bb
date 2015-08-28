DESCRIPTION =  "Kernel drivers for the PowerVR SGX chipset found in the omap5 SoCs"
HOMEPAGE = "http://git.ti.com"
LICENSE = "MIT | GPLv2"
LIC_FILES_CHKSUM = "file://eurasia_km/README;beginline=13;endline=22;md5=2b841bfc03386bb4d8d9381b79d33898"

inherit module

MACHINE_KERNEL_PR_append = "h"
PR = "${MACHINE_KERNEL_PR}"

BRANCH = "dra7/k4.1"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-linux.git;protocol=git;branch=${BRANCH}"
S = "${WORKDIR}/git"

SRCREV = "e06c0a4e11401534b938b9a7b1c3f27a65db871f"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}"'

do_compile_prepend() {
    cd ${S}/eurasia_km/eurasiacon/build/linux2/omap5430_linux
}

do_install() {
    make -C ${STAGING_KERNEL_DIR} SUBDIRS=${B}/eurasia_km/eurasiacon/binary2_omap5430_linux_release/target/kbuild INSTALL_MOD_PATH=${D} PREFIX=${STAGING_DIR_HOST} modules_install
}
