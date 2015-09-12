require u-boot-ti.inc

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native"

DESCRIPTION = "u-boot bootloader for TI devices"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

PR = "r9"
PV_append = "+git${SRCPV}"

SRC_URI = "git://git.ti.com/ti-u-boot/ti-u-boot.git;protocol=git;branch=${BRANCH}"

BRANCH ?= "ti-u-boot-2015.07"

SRCREV = "b5a8694cf6a0f2ac504e16e3068b967f3ecc8700"

SPL_BINARY = "MLO"
SPL_UART_BINARY = "u-boot-spl.bin"

# Keystone specifics
UBOOT_SUFFIX_keystone = "bin"
UBOOT_MAKE_TARGET_keystone = "u-boot-spi.gph u-boot-nand.gph"
SPL_BINARY_keystone = ""
SPL_UART_BINARY_keystone = ""

# SPI NOR Flash binaries
UBOOT_SPI_SPL_BINARY = "u-boot-spl.bin"
UBOOT_SPI_BINARY = "u-boot.img"
UBOOT_SPI_GPH_BINARY = "u-boot-spi.gph"
UBOOT_NAND_GPH_BINARY = "u-boot-nand.gph"

# SPI NOR Flash deployed images
UBOOT_SPI_SPL_IMAGE = "u-boot-spl-${MACHINE}-${PV}-${PR}.bin"
UBOOT_SPI_SPL_SYMLINK = "u-boot-spl-${MACHINE}.bin"
UBOOT_SPI_IMAGE = "u-boot-${MACHINE}-${PV}-${PR}.img"
UBOOT_SPI_SYMLINK = "u-boot-${MACHINE}.img"
UBOOT_SPI_GPH_IMAGE = "u-boot-spi-${MACHINE}-${PV}-${PR}.gph"
UBOOT_SPI_GPH_SYMLINK = "u-boot-spi-${MACHINE}.gph"
UBOOT_NAND_GPH_IMAGE = "u-boot-nand-${MACHINE}-${PV}-${PR}.gph"
UBOOT_NAND_GPH_SYMLINK = "u-boot-nand-${MACHINE}.gph"

do_install_append_keystone () {
	install ${S}/spl/${UBOOT_SPI_SPL_BINARY} ${D}/boot/${UBOOT_SPI_SPL_IMAGE}
	ln -sf ${UBOOT_SPI_SPL_IMAGE} ${D}/boot/${UBOOT_SPI_SPL_BINARY}

	install ${S}/${UBOOT_SPI_BINARY} ${D}/boot/${UBOOT_SPI_IMAGE}
	ln -sf ${UBOOT_SPI_IMAGE} ${D}/boot/${UBOOT_SPI_BINARY}

	install ${S}/${UBOOT_SPI_GPH_BINARY} ${D}/boot/${UBOOT_SPI_GPH_IMAGE}
	ln -sf ${UBOOT_SPI_GPH_IMAGE} ${D}/boot/${UBOOT_SPI_GPH_BINARY}

	install ${S}/${UBOOT_NAND_GPH_BINARY} ${D}/boot/${UBOOT_NAND_GPH_IMAGE}
	ln -sf ${UBOOT_NAND_GPH_IMAGE} ${D}/boot/${UBOOT_NAND_GPH_BINARY}
}

do_deploy_append_keystone () {
	install ${S}/spl/${UBOOT_SPI_SPL_BINARY} ${DEPLOYDIR}/${UBOOT_SPI_SPL_IMAGE}
	rm -f ${UBOOT_SPI_SPL_BINARY} ${UBOOT_SPI_SPL_SYMLINK}
	ln -sf ${UBOOT_SPI_SPL_IMAGE} ${UBOOT_SPI_SPL_SYMLINK}
	ln -sf ${UBOOT_SPI_SPL_IMAGE} ${UBOOT_SPI_SPL_BINARY}

	install ${S}/${UBOOT_SPI_BINARY} ${DEPLOYDIR}/${UBOOT_SPI_IMAGE}
	rm -f ${UBOOT_SPI_BINARY} ${UBOOT_SPI_SYMLINK}
	ln -sf ${UBOOT_SPI_IMAGE} ${UBOOT_SPI_SYMLINK}
	ln -sf ${UBOOT_SPI_IMAGE} ${UBOOT_SPI_BINARY}

	install ${S}/${UBOOT_SPI_GPH_BINARY} ${DEPLOYDIR}/${UBOOT_SPI_GPH_IMAGE}
	rm -f ${UBOOT_SPI_GPH_BINARY} ${UBOOT_SPI_GPH_SYMLINK}
	ln -sf ${UBOOT_SPI_GPH_IMAGE} ${UBOOT_SPI_GPH_SYMLINK}
	ln -sf ${UBOOT_SPI_GPH_IMAGE} ${UBOOT_SPI_GPH_BINARY}

	install ${S}/${UBOOT_NAND_GPH_BINARY} ${DEPLOYDIR}/${UBOOT_NAND_GPH_IMAGE}
	rm -f ${UBOOT_NAND_GPH_BINARY} ${UBOOT_NAND_GPH_SYMLINK}
	ln -sf ${UBOOT_NAND_GPH_IMAGE} ${UBOOT_NAND_GPH_SYMLINK}
	ln -sf ${UBOOT_NAND_GPH_IMAGE} ${UBOOT_NAND_GPH_BINARY}
}
