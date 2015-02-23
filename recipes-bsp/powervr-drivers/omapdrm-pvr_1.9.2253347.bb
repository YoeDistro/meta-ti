DESCRIPTION =  "Kernel drivers for the PowerVR SGX chipset found in the omap5 SoCs"
HOMEPAGE = "http://git.ti.com"
LICENSE = "MIT | GPLv2"
LIC_FILES_CHKSUM = "file://eurasia_km/README;beginline=13;endline=22;md5=2b841bfc03386bb4d8d9381b79d33898"

inherit module

MACHINE_KERNEL_PR_append = "c"
PR = "${MACHINE_KERNEL_PR}"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-linux.git;protocol=git"
S = "${WORKDIR}/git"

SRCREV = "0f3561a47cf7a1b968fb64446a0be10abc9a15d5"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}"'

do_compile_prepend() {
    cd ${S}/eurasia_km/eurasiacon/build/linux2/omap5430_linux
}

do_install() {
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra/
    cp ${S}/eurasia_km/eurasiacon/binary2_omap5430_linux_release/target/kbuild/omapdrm_pvr.ko \
    ${D}/lib/modules/${KERNEL_VERSION}/extra/
}
