DESCRIPTION =  "Kernel drivers for the PowerVR SGX chipset found in the omap5 SoCs"
HOMEPAGE = "http://git.ti.com"
LICENSE = "MIT | GPLv2"
LIC_FILES_CHKSUM = "file://eurasia_km/README;beginline=13;endline=22;md5=2b841bfc03386bb4d8d9381b79d33898"

inherit module

MACHINE_KERNEL_PR_append = "f"
PR = "${MACHINE_KERNEL_PR}"

BRANCH = "omap5/next"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-linux.git;protocol=git;branch=${BRANCH}"
S = "${WORKDIR}/git"

SRCREV = "285ce496cecfedcc1c1acc653e3ee4be32995281"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}"'

do_compile_prepend() {
    cd ${S}/eurasia_km/eurasiacon/build/linux2/omap5430_linux
}

do_install() {
    make -C ${STAGING_KERNEL_DIR} SUBDIRS=${B}/eurasia_km/eurasiacon/binary2_omap5430_linux_release/target/kbuild INSTALL_MOD_PATH=${D} PREFIX=${STAGING_DIR_HOST} modules_install
}
