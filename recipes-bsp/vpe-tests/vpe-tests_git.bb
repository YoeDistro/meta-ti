DESCRIPTION = "VPE test program"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=74d2f71d8898c54e3d1c9d0058c484aa"

DEPENDS = "virtual/kernel vpdma-fw"

PR = "r3"

COMPATIBLE_MACHINE = "dra7xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "e3d8db1aa935775f9d196ad7428e0cd9864a36ca"
BRANCH ?= "master"

SRC_URI = "git://git.ti.com/vpe_tests/vpe_tests.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

FLOATABI = "${@bb.utils.contains("TUNE_FEATURES", "vfp", bb.utils.contains("TUNE_FEATURES", "callconvention-hard", " -mfloat-abi=hard", " -mfloat-abi=softfp", d), "" ,d)}"

# The test application needs additional include headers from the kernel
EXTRA_OEMAKE = 'CROSS_COMPILE="${TARGET_PREFIX}" CC="${TARGET_PREFIX}gcc ${FLOATABI}" KDIR="${STAGING_KERNEL_DIR}/include/uapi -I${STAGING_KERNEL_DIR}/include"'

do_install() {
    oe_runmake DESTDIR="${D}" install
    rm -rf ${D}${base_libdir}/firmware/vpdma-*.bin
    rmdir -p ${D}${base_libdir}/firmware || true
}
