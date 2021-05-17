require recipes-bsp/ti-sci-fw/ti-sci-fw.inc

DEPENDS = "openssl-native u-boot-mkimage-native dtc-native"
DEPENDS_append_j7200-evm-k3r5 = " virtual/bootloader"
DEPENDS_append_am64xx-evm-k3r5 = " virtual/bootloader"

CLEANBROKEN = "1"
PR = "r1"

# Loaded by R5F core
COMPATIBLE_MACHINE = "k3r5"
COMPATIBLE_MACHINE_aarch64 = "null"

PACKAGE_ARCH = "${MACHINE_ARCH}"

TI_SECURE_DEV_PKG ?= ""
export TI_SECURE_DEV_PKG

SYSFW_SOC ?= "unknown"
SYSFW_CONFIG ?= "unknown"

SYSFW_PREFIX = "ti-sci-firmware"
SYSFW_PREFIX_j7-evm-k3r5 = "ti-fs-firmware"
SYSFW_PREFIX_j7200-evm-k3r5 = "ti-fs-firmware"
SYSFW_PREFIX_j7-hs-evm-k3r5 = "ti-fs-firmware"

SYSFW_SUFFIX ?= "unknown"

SYSFW_BASE = "${SYSFW_PREFIX}-${SYSFW_SOC}-${SYSFW_SUFFIX}"

SYSFW_TISCI = "${S}/ti-sysfw/${SYSFW_BASE}*.bin"

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
    CROSS_COMPILE=${TARGET_PREFIX} SYSFW_DL_URL='' SYSFW_HS_DL_URL='' SYSFW_HS_INNER_CERT_DL_URL='' \
    SYSFW_PATH="${SYSFW_TISCI}" SOC=${SYSFW_SOC} CONFIG=${SYSFW_CONFIG} \
"
EXTRA_OEMAKE_HS = " \
    HS=1 SYSFW_HS_PATH="${S}/ti-sysfw/${SYSFW_BASE}-enc.bin" SYSFW_HS_INNER_CERT_PATH="${S}/ti-sysfw/${SYSFW_BASE}-cert.bin" \
"
EXTRA_OEMAKE_append = "${@['',' ${EXTRA_OEMAKE_HS}']['${SYSFW_SUFFIX}' == 'hs']}"

EXTRA_OEMAKE_append_j7200-evm-k3r5 = " SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""
EXTRA_OEMAKE_append_am64xx-evm-k3r5 = " SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""

do_compile() {
	cd ${WORKDIR}/imggen/
	oe_runmake
}

do_install() {
	install -d ${D}/boot
	install -m 644 ${WORKDIR}/imggen/${SYSFW_BINARY} ${D}/boot/${SYSFW_VBINARY}
	ln -sf ${SYSFW_VBINARY} ${D}/boot/${SYSFW_IMAGE}
	if [ ! -z "${SYSFW_SYMLINK}" ]; then
		ln -sf ${SYSFW_VBINARY} ${D}/boot/${SYSFW_SYMLINK}
	fi
}

FILES_${PN} = "/boot"

inherit deploy

do_deploy () {
	install -d ${DEPLOYDIR}
	install -m 644 ${WORKDIR}/imggen/${SYSFW_BINARY} ${DEPLOYDIR}/${SYSFW_VBINARY}
	rm -f ${DEPLOYDIR}/${SYSFW_IMAGE}
	ln -sf ${SYSFW_VBINARY} ${DEPLOYDIR}/${SYSFW_IMAGE}
	if [ ! -z "${SYSFW_SYMLINK}" ]; then
		rm -f ${DEPLOYDIR}/${SYSFW_SYMLINK}
		ln -sf ${SYSFW_VBINARY} ${DEPLOYDIR}/${SYSFW_SYMLINK}
	fi

	install -m 644 ${SYSFW_TISCI} ${DEPLOYDIR}/
}

do_install_j7200-evm-k3r5() {
	install -d ${D}/boot
	install -m 644 ${WORKDIR}/imggen/${UBOOT_BINARY} ${D}/boot/${UBOOT_IMAGE}
	ln -sf ${UBOOT_IMAGE} ${D}/boot/${UBOOT_SYMLINK}
	ln -sf ${UBOOT_IMAGE} ${D}/boot/${UBOOT_BINARY}
}

do_deploy_j7200-evm-k3r5() {
	install -d ${DEPLOYDIR}
	install -m 644 ${WORKDIR}/imggen/${UBOOT_BINARY} ${DEPLOYDIR}/${UBOOT_IMAGE}
	ln -sf ${UBOOT_IMAGE} ${DEPLOYDIR}/${UBOOT_SYMLINK}
	ln -sf ${UBOOT_IMAGE} ${DEPLOYDIR}/${UBOOT_BINARY}
	install -m 644 ${SYSFW_TISCI} ${DEPLOYDIR}/
}

do_install_am64xx-evm-k3r5() {
	install -d ${D}/boot
	install -m 644 ${WORKDIR}/imggen/${UBOOT_BINARY} ${D}/boot/${UBOOT_IMAGE}
	ln -sf ${UBOOT_IMAGE} ${D}/boot/${UBOOT_SYMLINK}
	ln -sf ${UBOOT_IMAGE} ${D}/boot/${UBOOT_BINARY}
}

do_deploy_am64xx-evm-k3r5() {
	install -d ${DEPLOYDIR}
	install -m 644 ${WORKDIR}/imggen/${UBOOT_BINARY} ${DEPLOYDIR}/${UBOOT_IMAGE}
	ln -sf ${UBOOT_IMAGE} ${DEPLOYDIR}/${UBOOT_SYMLINK}
	ln -sf ${UBOOT_IMAGE} ${DEPLOYDIR}/${UBOOT_BINARY}
	install -m 644 ${SYSFW_TISCI} ${DEPLOYDIR}/
}

addtask deploy before do_build after do_compile
