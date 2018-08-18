# UBOOT_LOCALVERSION can be set to add a tag to the end of the
# U-boot version string.  such as the commit id
def get_git_revision(p):
    import subprocess

    try:
        return subprocess.Popen("git rev-parse HEAD 2>/dev/null ", cwd=p, shell=True, stdout=subprocess.PIPE, universal_newlines=True).communicate()[0].rstrip()
    except OSError:
        return None

UBOOT_LOCALVERSION = "-g${@get_git_revision('${S}').__str__()[:10]}"

UBOOT_SUFFIX ?= "img"

require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"

SUMMARY = "u-boot bootloader for TI devices"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

BRANCH ?= "master"
UBOOT_GIT_URI = "git://git.ti.com/ti-u-boot/ti-u-boot.git"
UBOOT_GIT_PROTOCOL = "git"
SRC_URI = "${UBOOT_GIT_URI};protocol=${UBOOT_GIT_PROTOCOL};branch=${BRANCH}"

PV_append = "+git${SRCPV}"

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native bc-native lzop-native"

PACKAGECONFIG[atf] = "ATF=${STAGING_DIR_HOST}/boot/bl31.bin,,arm-trusted-firmware"
PACKAGECONFIG[optee] = "TEE=${STAGING_DIR_HOST}/boot/bl32.bin,,optee-os"

# optee-os is not a direct dependency, do not enable optee by default
PACKAGECONFIG_append_k3 = " atf"

EXTRA_OEMAKE += "${PACKAGECONFIG_CONFARGS}"

PROVIDES += "u-boot"
PKG_${PN} = "u-boot"
PKG_${PN}-dev = "u-boot-dev"
PKG_${PN}-dbg = "u-boot-dbg"

S = "${WORKDIR}/git"

# Support for secure devices - detailed info is in doc/README.ti-secure
TI_SECURE_DEV_PKG ?= ""
export TI_SECURE_DEV_PKG

# SPL (Second Program Loader)
SPL_BINARY = "MLO"
SPL_BINARY_k2e-hs-evm = ""
SPL_BINARY_k2hk-hs-evm = ""
SPL_BINARY_k2g-hs-evm = ""
SPL_BINARY_k2l-hs-evm = ""
SPL_BINARY_omapl138 = ""
SPL_BINARY_k3 = "tispl.bin"

# SPL (Second Program Loader) to be loaded over UART
SPL_UART_BINARY = "u-boot-spl.bin"
SPL_UART_BINARY_keystone = ""
SPL_UART_BINARY_k3 = ""

SPL_UART_IMAGE ?= "${SPL_UART_BINARY}-${MACHINE}-${PV}-${PR}"
SPL_UART_SYMLINK ?= "${SPL_UART_BINARY}-${MACHINE}"

# Keystone specifics
UBOOT_SUFFIX_keystone = "bin"

# SPI NOR Flash binaries
UBOOT_SPI_SPL_BINARY = "u-boot-spl.bin"
UBOOT_SPI_SPL_BINARY_k2e-hs-evm = ""
UBOOT_SPI_SPL_BINARY_k2hk-hs-evm = ""
UBOOT_SPI_SPL_BINARY_k2g-hs-evm = ""
UBOOT_SPI_SPL_BINARY_k2l-hs-evm = ""
UBOOT_SPI_BINARY = "u-boot.img"
UBOOT_SPI_GPH_BINARY = "u-boot-spi.gph"
UBOOT_SPI_GPH_BINARY_k2e-hs-evm = ""
UBOOT_SPI_GPH_BINARY_k2hk-hs-evm = ""
UBOOT_SPI_GPH_BINARY_k2g-hs-evm = ""
UBOOT_SPI_GPH_BINARY_k2l-hs-evm = ""

# SPI NOR Flash deployed images
UBOOT_SPI_SPL_IMAGE = "u-boot-spl-${MACHINE}-${PV}-${PR}.bin"
UBOOT_SPI_SPL_SYMLINK = "u-boot-spl-${MACHINE}.bin"
UBOOT_SPI_IMAGE = "u-boot-${MACHINE}-${PV}-${PR}.img"
UBOOT_SPI_SYMLINK = "u-boot-${MACHINE}.img"
UBOOT_SPI_GPH_IMAGE = "u-boot-spi-${MACHINE}-${PV}-${PR}.gph"
UBOOT_SPI_GPH_SYMLINK = "u-boot-spi-${MACHINE}.gph"

# HS XLD
UBOOT_HS_XLD_BINARY = "u-boot-spl_HS_X-LOADER"
UBOOT_HS_XLD_IMAGE = "u-boot-spl_HS_X-LOADER-${MACHINE}-${PV}-${PR}"
UBOOT_HS_XLD_SYMLINK = "u-boot-spl_HS_X-LOADER-${MACHINE}"

# HS MLO
UBOOT_HS_MLO_BINARY = "u-boot_HS_MLO"
UBOOT_HS_MLO_IMAGE = "u-boot_HS_MLO-${MACHINE}-${PV}-${PR}"
UBOOT_HS_MLO_SYMLINK = "u-boot_HS_MLO-${MACHINE}"

# HS ISSW
UBOOT_HS_ISSW_BINARY = "u-boot-spl_HS_ISSW"
UBOOT_HS_ISSW_IMAGE = "u-boot-spl_HS_ISSW-${MACHINE}-${PV}-${PR}"
UBOOT_HS_ISSW_SYMLINK = "u-boot-spl_HS_ISSW-${MACHINE}"

# HS 2ND
UBOOT_HS_2ND_BINARY = "u-boot-spl_HS_2ND"
UBOOT_HS_2ND_IMAGE = "u-boot-spl_HS_2ND-${MACHINE}-${PV}-${PR}"
UBOOT_HS_2ND_SYMLINK = "u-boot-spl_HS_2ND-${MACHINE}"

do_install_append () {
	if [ -n "${UBOOT_CONFIG}" ]
	then
		for config in ${UBOOT_MACHINE}; do
			i=$(expr $i + 1);
			for type in ${UBOOT_CONFIG}; do
				j=$(expr $j + 1);
				if [ $j -eq $i ]
				then
					if [ "x${SPL_UART_BINARY}" != "x" ]; then
						install ${B}/${config}/spl/${SPL_UART_BINARY} ${D}/boot/${SPL_UART_IMAGE}-${type}
						ln -sf ${SPL_UART_IMAGE}-${type} ${D}/boot/${SPL_UART_BINARY}-${type}
						ln -sf ${SPL_UART_IMAGE}-${type} ${D}/boot/${SPL_UART_BINARY}
					fi
					if [ -f ${B}/${config}/${UBOOT_HS_XLD_BINARY} ]; then
						install ${B}/${config}/${UBOOT_HS_XLD_BINARY} ${D}/boot/${UBOOT_HS_XLD_IMAGE}-${type}
						ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${D}/boot/${UBOOT_HS_XLD_BINARY}-${type}
						ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${D}/boot/${UBOOT_HS_XLD_BINARY}
					fi
					if [ -f ${B}/${config}/${UBOOT_HS_MLO_BINARY} ]; then
						install ${B}/${config}/${UBOOT_HS_MLO_BINARY} ${D}/boot/${UBOOT_HS_MLO_IMAGE}-${type}
						ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${D}/boot/${UBOOT_HS_MLO_BINARY}-${type}
						ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${D}/boot/${UBOOT_HS_MLO_BINARY}
					fi
					if [ -f ${B}/${config}/${UBOOT_HS_ISSW_BINARY} ]; then
						install ${B}/${config}/${UBOOT_HS_ISSW_BINARY} ${D}/boot/${UBOOT_HS_ISSW_IMAGE}-${type}
						ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${D}/boot/${UBOOT_HS_ISSW_BINARY}-${type}
						ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${D}/boot/${UBOOT_HS_ISSW_BINARY}
					fi
					if [ -f ${B}/${config}/${UBOOT_HS_2ND_BINARY} ]; then
						install ${B}/${config}/${UBOOT_HS_2ND_BINARY} ${D}/boot/${UBOOT_HS_2ND_IMAGE}-${type}
						ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${D}/boot/${UBOOT_HS_2ND_BINARY}-${type}
						ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${D}/boot/${UBOOT_HS_2ND_BINARY}
					fi
				fi
			done
			unset  j
		done
		unset  i
	else
		if [ "x${SPL_UART_BINARY}" != "x" ]; then
			install ${B}/spl/${SPL_UART_BINARY} ${D}/boot/${SPL_UART_IMAGE}
			ln -sf ${SPL_UART_IMAGE} ${D}/boot/${SPL_UART_BINARY}
		fi
		if [ -f ${B}/${UBOOT_HS_XLD_BINARY} ]; then
			install ${B}/${UBOOT_HS_XLD_BINARY} ${D}/boot/${UBOOT_HS_XLD_IMAGE}
			ln -sf ${UBOOT_HS_XLD_IMAGE} ${D}/boot/${UBOOT_HS_XLD_BINARY}
		fi
		if [ -f ${B}/${UBOOT_HS_MLO_BINARY} ]; then
			install ${B}/${UBOOT_HS_MLO_BINARY} ${D}/boot/${UBOOT_HS_MLO_IMAGE}
			ln -sf ${UBOOT_HS_MLO_IMAGE} ${D}/boot/${UBOOT_HS_MLO_BINARY}
		fi
		if [ -f ${B}/${UBOOT_HS_ISSW_BINARY} ]; then
			install ${B}/${UBOOT_HS_ISSW_BINARY} ${D}/boot/${UBOOT_HS_ISSW_IMAGE}
			ln -sf ${UBOOT_HS_ISSW_IMAGE} ${D}/boot/${UBOOT_HS_ISSW_BINARY}
		fi
		if [ -f ${B}/${UBOOT_HS_2ND_BINARY} ]; then
			install ${B}/${UBOOT_HS_2ND_BINARY} ${D}/boot/${UBOOT_HS_2ND_IMAGE}
			ln -sf ${UBOOT_HS_2ND_IMAGE} ${D}/boot/${UBOOT_HS_2ND_BINARY}
		fi
	fi
}

do_deploy_append () {
	if [ -n "${UBOOT_CONFIG}" ]
	then
		for config in ${UBOOT_MACHINE}; do
			i=$(expr $i + 1);
			for type in ${UBOOT_CONFIG}; do
				j=$(expr $j + 1);
				if [ $j -eq $i ]
				then
					cd ${DEPLOYDIR}
					if [ "x${SPL_UART_BINARY}" != "x" ]; then
						install ${B}/${config}/spl/${SPL_UART_BINARY} ${DEPLOYDIR}/${SPL_UART_IMAGE}-${type}
						rm -f ${DEPLOYDIR}/${SPL_UART_BINARY} ${DEPLOYDIR}/${SPL_UART_SYMLINK}
						ln -sf ${SPL_UART_IMAGE}-${type} ${DEPLOYDIR}/${SPL_UART_BINARY}-${type}
						ln -sf ${SPL_UART_IMAGE}-${type} ${DEPLOYDIR}/${SPL_UART_BINARY}
						ln -sf ${SPL_UART_IMAGE}-${type} ${DEPLOYDIR}/${SPL_UART_SYMLINK}-${type}
						ln -sf ${SPL_UART_IMAGE}-${type} ${DEPLOYDIR}/${SPL_UART_SYMLINK}
					fi
					if [ -f ${B}/${config}/${UBOOT_HS_XLD_BINARY} ]; then
						install ${B}/${config}/${UBOOT_HS_XLD_BINARY} ${DEPLOYDIR}/${UBOOT_HS_XLD_IMAGE}-${type}
						rm -f ${UBOOT_HS_XLD_BINARY} ${UBOOT_HS_XLD_SYMLINK}
						ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${UBOOT_HS_XLD_SYMLINK}-${type}
						ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${UBOOT_HS_XLD_SYMLINK}
						ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${UBOOT_HS_XLD_BINARY}-${type}
						ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${UBOOT_HS_XLD_BINARY}
					fi
					if [ -f ${B}/${config}/${UBOOT_HS_MLO_BINARY} ]; then
						install ${B}/${config}/${UBOOT_HS_MLO_BINARY} ${DEPLOYDIR}/${UBOOT_HS_MLO_IMAGE}-${type}
						rm -f ${UBOOT_HS_MLO_BINARY} ${UBOOT_HS_MLO_SYMLINK}
						ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${UBOOT_HS_MLO_SYMLINK}-${type}
						ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${UBOOT_HS_MLO_SYMLINK}
						ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${UBOOT_HS_MLO_BINARY}-${type}
						ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${UBOOT_HS_MLO_BINARY}
					fi
					if [ -f ${B}/${config}/${UBOOT_HS_ISSW_BINARY} ]; then
						install ${B}/${config}/${UBOOT_HS_ISSW_BINARY} ${DEPLOYDIR}/${UBOOT_HS_ISSW_IMAGE}-${type}
						rm -f ${UBOOT_HS_ISSW_BINARY} ${UBOOT_HS_ISSW_SYMLINK}
						ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${UBOOT_HS_ISSW_SYMLINK}-${type}
						ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${UBOOT_HS_ISSW_SYMLINK}
						ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${UBOOT_HS_ISSW_BINARY}-${type}
						ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${UBOOT_HS_ISSW_BINARY}
					fi
					if [ -f ${B}/${config}/${UBOOT_HS_2ND_BINARY} ]; then
						install ${B}/${config}/${UBOOT_HS_2ND_BINARY} ${DEPLOYDIR}/${UBOOT_HS_2ND_IMAGE}-${type}
						rm -f ${UBOOT_HS_2ND_BINARY} ${UBOOT_HS_2ND_SYMLINK}
						ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${UBOOT_HS_2ND_SYMLINK}-${type}
						ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${UBOOT_HS_2ND_SYMLINK}
						ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${UBOOT_HS_2ND_BINARY}-${type}
						ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${UBOOT_HS_2ND_BINARY}
					fi
				fi
			done
			unset  j
		done
		unset  i
	else
		cd ${DEPLOYDIR}
		if [ "x${SPL_UART_BINARY}" != "x" ]; then
			install ${B}/spl/${SPL_UART_BINARY} ${DEPLOYDIR}/${SPL_UART_IMAGE}
			rm -f ${DEPLOYDIR}/${SPL_UART_BINARY} ${DEPLOYDIR}/${SPL_UART_SYMLINK}
			ln -sf ${SPL_UART_IMAGE} ${DEPLOYDIR}/${SPL_UART_BINARY}
			ln -sf ${SPL_UART_IMAGE} ${DEPLOYDIR}/${SPL_UART_SYMLINK}
		fi
		if [ -f ${B}/${UBOOT_HS_XLD_BINARY} ]; then
			install ${B}/${UBOOT_HS_XLD_BINARY} ${DEPLOYDIR}/${UBOOT_HS_XLD_IMAGE}
			rm -f ${UBOOT_HS_XLD_BINARY} ${UBOOT_HS_XLD_SYMLINK}
			ln -sf ${UBOOT_HS_XLD_IMAGE} ${UBOOT_HS_XLD_SYMLINK}
			ln -sf ${UBOOT_HS_XLD_IMAGE} ${UBOOT_HS_XLD_BINARY}
		fi
		if [ -f ${B}/${UBOOT_HS_MLO_BINARY} ]; then
			install ${B}/${UBOOT_HS_MLO_BINARY} ${DEPLOYDIR}/${UBOOT_HS_MLO_IMAGE}
			rm -f ${UBOOT_HS_MLO_BINARY} ${UBOOT_HS_MLO_SYMLINK}
			ln -sf ${UBOOT_HS_MLO_IMAGE} ${UBOOT_HS_MLO_SYMLINK}
			ln -sf ${UBOOT_HS_MLO_IMAGE} ${UBOOT_HS_MLO_BINARY}
		fi
		if [ -f ${B}/${UBOOT_HS_ISSW_BINARY} ]; then
			install ${B}/${UBOOT_HS_ISSW_BINARY} ${DEPLOYDIR}/${UBOOT_HS_ISSW_IMAGE}
			rm -f ${UBOOT_HS_ISSW_BINARY} ${UBOOT_HS_ISSW_SYMLINK}
			ln -sf ${UBOOT_HS_ISSW_IMAGE} ${UBOOT_HS_ISSW_SYMLINK}
			ln -sf ${UBOOT_HS_ISSW_IMAGE} ${UBOOT_HS_ISSW_BINARY}
		fi
		if [ -f ${B}/${UBOOT_HS_2ND_BINARY} ]; then
			install ${B}/${UBOOT_HS_2ND_BINARY} ${DEPLOYDIR}/${UBOOT_HS_2ND_IMAGE}
			rm -f ${UBOOT_HS_2ND_BINARY} ${UBOOT_HS_2ND_SYMLINK}
			ln -sf ${UBOOT_HS_2ND_IMAGE} ${UBOOT_HS_2ND_SYMLINK}
			ln -sf ${UBOOT_HS_2ND_IMAGE} ${UBOOT_HS_2ND_BINARY}
		fi
	fi
}

do_install_append_keystone () {
	if [ -n "${UBOOT_CONFIG}" ]
	then
		for config in ${UBOOT_MACHINE}; do
			i=$(expr $i + 1);
			for type in ${UBOOT_CONFIG}; do
				j=$(expr $j + 1);
				if [ $j -eq $i ]
				then
					if [ "x${UBOOT_SPI_SPL_BINARY}" != "x" ]; then
						install ${B}/${config}/spl/${UBOOT_SPI_SPL_BINARY} ${D}/boot/${UBOOT_SPI_SPL_IMAGE}-${type}
						ln -sf ${UBOOT_SPI_SPL_IMAGE}-${type} ${D}/boot/${UBOOT_SPI_SPL_BINARY}-${type}
						ln -sf ${UBOOT_SPI_SPL_IMAGE}-${type} ${D}/boot/${UBOOT_SPI_SPL_BINARY}
					fi

					install ${B}/${config}/${UBOOT_SPI_BINARY} ${D}/boot/${UBOOT_SPI_IMAGE}-${type}
					ln -sf ${UBOOT_SPI_IMAGE}-${type} ${D}/boot/${UBOOT_SPI_BINARY}-${type}
					ln -sf ${UBOOT_SPI_IMAGE}-${type} ${D}/boot/${UBOOT_SPI_BINARY}

					if [ "x${UBOOT_SPI_GPH_BINARY}" != "x" ]; then
						install ${B}/${config}/${UBOOT_SPI_GPH_BINARY} ${D}/boot/${UBOOT_SPI_GPH_IMAGE}-${type}
						ln -sf ${UBOOT_SPI_GPH_IMAGE}-${type} ${D}/boot/${UBOOT_SPI_GPH_BINARY}-${type}
						ln -sf ${UBOOT_SPI_GPH_IMAGE}-${type} ${D}/boot/${UBOOT_SPI_GPH_BINARY}
					fi
				fi
			done
			unset  j
		done
		unset  i
	else
		if [ "x${UBOOT_SPI_SPL_BINARY}" != "x" ]; then
			install ${B}/spl/${UBOOT_SPI_SPL_BINARY} ${D}/boot/${UBOOT_SPI_SPL_IMAGE}
			ln -sf ${UBOOT_SPI_SPL_IMAGE} ${D}/boot/${UBOOT_SPI_SPL_BINARY}
		fi

		install ${B}/${UBOOT_SPI_BINARY} ${D}/boot/${UBOOT_SPI_IMAGE}
		ln -sf ${UBOOT_SPI_IMAGE} ${D}/boot/${UBOOT_SPI_BINARY}

		if [ "x${UBOOT_SPI_GPH_BINARY}" != "x" ]; then
			install ${B}/${UBOOT_SPI_GPH_BINARY} ${D}/boot/${UBOOT_SPI_GPH_IMAGE}
			ln -sf ${UBOOT_SPI_GPH_IMAGE} ${D}/boot/${UBOOT_SPI_GPH_BINARY}
		fi
	fi
}

do_deploy_append_keystone () {
	if [ -n "${UBOOT_CONFIG}" ]
	then
		for config in ${UBOOT_MACHINE}; do
			i=$(expr $i + 1);
			for type in ${UBOOT_CONFIG}; do
				j=$(expr $j + 1);
				if [ $j -eq $i ]
				then
					if [ "x${UBOOT_SPI_SPL_BINARY}" != "x" ]; then
						install ${B}/${config}/spl/${UBOOT_SPI_SPL_BINARY} ${DEPLOYDIR}/${UBOOT_SPI_SPL_IMAGE}-${type}
						rm -f ${UBOOT_SPI_SPL_BINARY} ${UBOOT_SPI_SPL_SYMLINK}
						ln -sf ${UBOOT_SPI_SPL_IMAGE}-${type} ${UBOOT_SPI_SPL_SYMLINK}-${type}
						ln -sf ${UBOOT_SPI_SPL_IMAGE}-${type} ${UBOOT_SPI_SPL_SYMLINK}
						ln -sf ${UBOOT_SPI_SPL_IMAGE}-${type} ${UBOOT_SPI_SPL_BINARY}-${type}
						ln -sf ${UBOOT_SPI_SPL_IMAGE}-${type} ${UBOOT_SPI_SPL_BINARY}
					fi

					install ${B}/${config}/${UBOOT_SPI_BINARY} ${DEPLOYDIR}/${UBOOT_SPI_IMAGE}-${type}
					rm -f ${UBOOT_SPI_BINARY} ${UBOOT_SPI_SYMLINK}
					ln -sf ${UBOOT_SPI_IMAGE}-${type} ${UBOOT_SPI_SYMLINK}-${type}
					ln -sf ${UBOOT_SPI_IMAGE}-${type} ${UBOOT_SPI_SYMLINK}
					ln -sf ${UBOOT_SPI_IMAGE}-${type} ${UBOOT_SPI_BINARY}-${type}
					ln -sf ${UBOOT_SPI_IMAGE}-${type} ${UBOOT_SPI_BINARY}

					if [ "x${UBOOT_SPI_GPH_BINARY}" != "x" ]; then
						install ${B}/${config}/${UBOOT_SPI_GPH_BINARY} ${DEPLOYDIR}/${UBOOT_SPI_GPH_IMAGE}-${type}
						rm -f ${UBOOT_SPI_GPH_BINARY} ${UBOOT_SPI_GPH_SYMLINK}
						ln -sf ${UBOOT_SPI_GPH_IMAGE}-${type} ${UBOOT_SPI_GPH_SYMLINK}-${type}
						ln -sf ${UBOOT_SPI_GPH_IMAGE}-${type} ${UBOOT_SPI_GPH_SYMLINK}
						ln -sf ${UBOOT_SPI_GPH_IMAGE}-${type} ${UBOOT_SPI_GPH_BINARY}-${type}
						ln -sf ${UBOOT_SPI_GPH_IMAGE}-${type} ${UBOOT_SPI_GPH_BINARY}
					fi
				fi
			done
			unset  j
		done
		unset  i
	else
		if [ "x${UBOOT_SPI_SPL_BINARY}" != "x" ]; then
			install ${B}/spl/${UBOOT_SPI_SPL_BINARY} ${DEPLOYDIR}/${UBOOT_SPI_SPL_IMAGE}
			rm -f ${UBOOT_SPI_SPL_BINARY} ${UBOOT_SPI_SPL_SYMLINK}
			ln -sf ${UBOOT_SPI_SPL_IMAGE} ${UBOOT_SPI_SPL_SYMLINK}
			ln -sf ${UBOOT_SPI_SPL_IMAGE} ${UBOOT_SPI_SPL_BINARY}
		fi

		install ${B}/${UBOOT_SPI_BINARY} ${DEPLOYDIR}/${UBOOT_SPI_IMAGE}
		rm -f ${UBOOT_SPI_BINARY} ${UBOOT_SPI_SYMLINK}
		ln -sf ${UBOOT_SPI_IMAGE} ${UBOOT_SPI_SYMLINK}
		ln -sf ${UBOOT_SPI_IMAGE} ${UBOOT_SPI_BINARY}

		if [ "x${UBOOT_SPI_GPH_BINARY}" != "x" ]; then
			install ${B}/${UBOOT_SPI_GPH_BINARY} ${DEPLOYDIR}/${UBOOT_SPI_GPH_IMAGE}
			rm -f ${UBOOT_SPI_GPH_BINARY} ${UBOOT_SPI_GPH_SYMLINK}
			ln -sf ${UBOOT_SPI_GPH_IMAGE} ${UBOOT_SPI_GPH_SYMLINK}
			ln -sf ${UBOOT_SPI_GPH_IMAGE} ${UBOOT_SPI_GPH_BINARY}
		fi
	fi
}
