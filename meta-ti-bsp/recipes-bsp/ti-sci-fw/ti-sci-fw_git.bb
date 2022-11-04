require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

DEPENDS = "openssl-native u-boot-mkimage-native dtc-native"
DEPENDS:append:j7200-evm-k3r5 = " virtual/bootloader"
DEPENDS:append:j7200-hs-evm-k3r5 = " virtual/bootloader"
DEPENDS:append:j721s2-evm-k3r5 = " virtual/bootloader"
DEPENDS:append:j721s2-hs-evm-k3r5 = " virtual/bootloader"
DEPENDS:append:am64xx-evm-k3r5 = " virtual/bootloader"
DEPENDS:append:am64xx-hs-evm-k3r5 = " virtual/bootloader"
DEPENDS:append:am62xx-evm-k3r5 = " virtual/bootloader"

CLEANBROKEN = "1"
PR = "${INC_PR}.2"

# Loaded by R5F core
COMPATIBLE_MACHINE = "k3r5"
COMPATIBLE_MACHINE:aarch64 = "null"

PACKAGE_ARCH = "${MACHINE_ARCH}"

TI_SECURE_DEV_PKG ?= ""
export TI_SECURE_DEV_PKG

SYSFW_SOC ?= "unknown"
SYSFW_CONFIG ?= "unknown"

SYSFW_PREFIX = "ti-sci-firmware"
SYSFW_PREFIX:j721e-evm-k3r5 = "ti-fs-firmware"
SYSFW_PREFIX:j721e-hs-evm-k3r5 = "ti-fs-firmware"
SYSFW_PREFIX:j721e-hs-evm-k3r5-sr1-1 = "ti-fs-firmware"
SYSFW_PREFIX:j7200-evm-k3r5 = "ti-fs-firmware"
SYSFW_PREFIX:j7200-hs-evm-k3r5 = "ti-fs-firmware"
SYSFW_PREFIX:j721s2-evm-k3r5 = "ti-fs-firmware"
SYSFW_PREFIX:j721s2-hs-evm-k3r5 = "ti-fs-firmware"
SYSFW_PREFIX:am62xx-evm-k3r5 = "ti-fs-firmware"

SYSFW_SUFFIX ?= "unknown"

SYSFW_BASE = "${SYSFW_PREFIX}-${SYSFW_SOC}-${SYSFW_SUFFIX}"
SYSFW_BASE:append = "${@['','*']['${SYSFW_SUFFIX}' == 'hs']}"

SYSFW_TISCI = "${S}/ti-sysfw/${SYSFW_BASE}.bin"

SYSFW_BINARY = "sysfw-${SYSFW_SOC}-${SYSFW_CONFIG}.itb"
SYSFW_VBINARY = "sysfw-${PV}-${SYSFW_SOC}-${SYSFW_CONFIG}.itb"
SYSFW_IMAGE = "sysfw-${SYSFW_SOC}-${SYSFW_CONFIG}.itb"
SYSFW_SYMLINK ?= "sysfw.itb"

CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_configure[noexec] = "1"

EXTRA_OEMAKE = "\
    CROSS_COMPILE=${TARGET_PREFIX} SOC=${SYSFW_SOC} SOC_TYPE=${SYSFW_SUFFIX} \
    CONFIG=${SYSFW_CONFIG} SYSFW_DIR="${S}/ti-sysfw" \
"

EXTRA_OEMAKE:append:j7200-evm-k3r5 = " SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""
EXTRA_OEMAKE:append:j7200-hs-evm-k3r5 = " SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""
EXTRA_OEMAKE:append:j721s2-evm-k3r5 = " SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""
EXTRA_OEMAKE:append:j721s2-hs-evm-k3r5 = " SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""
EXTRA_OEMAKE:append:am64xx-evm-k3r5 = " SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""
EXTRA_OEMAKE:append:am64xx-hs-evm-k3r5 = " SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""
EXTRA_OEMAKE:append:am62xx-evm-k3r5 = " SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""

do_compile() {
	cd ${WORKDIR}/imggen/
	oe_runmake
}

do_install() {
	install -d ${D}/boot

	if [ -f "${WORKDIR}/imggen/${SYSFW_BINARY}" ]; then
		install -m 644 ${WORKDIR}/imggen/${SYSFW_BINARY} ${D}/boot/${SYSFW_VBINARY}
		ln -sf ${SYSFW_VBINARY} ${D}/boot/${SYSFW_IMAGE}
		if [ ! -z "${SYSFW_SYMLINK}" ]; then
			ln -sf ${SYSFW_VBINARY} ${D}/boot/${SYSFW_SYMLINK}
		fi
	fi

	if [ -f "${WORKDIR}/imggen/${UBOOT_BINARY}" ]; then
		install -m 644 ${WORKDIR}/imggen/${UBOOT_BINARY} ${D}/boot/${UBOOT_IMAGE}
		ln -sf ${UBOOT_IMAGE} ${D}/boot/${UBOOT_SYMLINK}
		ln -sf ${UBOOT_IMAGE} ${D}/boot/${UBOOT_BINARY}
	fi
}

FILES:${PN} = "/boot"

inherit deploy

do_deploy () {
	install -d ${DEPLOYDIR}

	if [ -f "${WORKDIR}/imggen/${SYSFW_BINARY}" ]; then
		install -m 644 ${WORKDIR}/imggen/${SYSFW_BINARY} ${DEPLOYDIR}/${SYSFW_VBINARY}
		ln -sf ${SYSFW_VBINARY} ${DEPLOYDIR}/${SYSFW_IMAGE}
		if [ ! -z "${SYSFW_SYMLINK}" ]; then
			ln -sf ${SYSFW_VBINARY} ${DEPLOYDIR}/${SYSFW_SYMLINK}
		fi
	fi

	if [ -f "${WORKDIR}/imggen/${UBOOT_BINARY}" ]; then
		install -m 644 ${WORKDIR}/imggen/${UBOOT_BINARY} ${DEPLOYDIR}/${UBOOT_IMAGE}
		ln -sf ${UBOOT_IMAGE} ${DEPLOYDIR}/${UBOOT_SYMLINK}
		ln -sf ${UBOOT_IMAGE} ${DEPLOYDIR}/${UBOOT_BINARY}
	fi

	install -m 644 ${SYSFW_TISCI} ${DEPLOYDIR}/
}

addtask deploy before do_build after do_compile
