do_compile:prepend () {
    scm_version=$(printf '%s%s' -ti-g $(git -C ${S} rev-parse --verify HEAD 2>/dev/null | cut -c1-12))

    echo ${scm_version} > ${B}/.scmversion
    echo ${scm_version} > ${S}/.scmversion
}

require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot-common.inc
require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc
require u-boot-mergeconfig.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/u-boot:"

FILES:${PN}-falcon = "boot/tifalcon.bin"

SUMMARY = "u-boot bootloader for TI devices"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

BRANCH ?= "master"
UBOOT_GIT_URI ?= "git://git.ti.com/git/ti-u-boot/ti-u-boot.git"
UBOOT_GIT_PROTOCOL ?= "https"
UBOOT_GIT_BRANCH ?= "branch=${BRANCH}"
SRC_URI = "${UBOOT_GIT_URI};protocol=${UBOOT_GIT_PROTOCOL};${UBOOT_GIT_BRANCH};name=uboot"

SRCREV_FORMAT = "uboot"

PV:append = "+git"

# u-boot builds a tool mkeficapsule and it needs this
DEPENDS += "gnutls-native"

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native bc-native flex-native bison-native python3-setuptools-native"

# u-boot needs these for binman
DEPENDS += "python3-pyelftools-native python3-pyyaml-native python3-jsonschema-native python3-yamllint-native"

# SYSFW/TIFS Firmware
DEPENDS:append:k3 = " ti-sci-fw"
DEPENDS:append:k3r5 = " ti-sci-fw"

# HSM Demo Firmware is only applicable for J721S2 & J784S4 devices
DEPENDS:append:j721s2 = " ti-hsm-demo-fw"
DEPENDS:append:j784s4 = " ti-hsm-demo-fw"

DM_FIRMWARE = "ipc_echo_testb_mcu1_0_release_strip.xer5f"
DM_FIRMWARE:am62axx = "dm_edgeai_mcu1_0_release_strip.out"
DM_FIRMWARE:am62pxx = "${@oe.utils.conditional('DISPLAY_CLUSTER_ENABLE', '1', 'dss_display_share.wkup-r5f0_0.release.strip.out', 'ipc_echo_testb_mcu1_0_release_strip.xer5f', d)}"

PLAT_SFX = ""
PLAT_SFX:j721e = "j721e"
PLAT_SFX:j7200 = "j7200"
PLAT_SFX:j721s2 = "j721s2"
PLAT_SFX:j784s4 = "j784s4"
PLAT_SFX:j722s = "j722s"
PLAT_SFX:j742s2 = "j742s2"
PLAT_SFX:am65xx = "am65xx"
PLAT_SFX:am64xx = "am64xx"
PLAT_SFX:am62xx = "am62xx"
PLAT_SFX:am62axx = "am62axx"
PLAT_SFX:am62dxx = "am62dxx"
PLAT_SFX:am62lxx = "am62lxx"
PLAT_SFX:am62pxx = "am62pxx"

ATF_BL1_PATH = "${STAGING_DIR_HOST}/firmware/trusted-firmware-a/bl1.bin"

ATF_BL31_PATH = "${STAGING_DIR_HOST}/firmware/trusted-firmware-a/bl31.bin"

OPTEE_BL32_PATH = "${STAGING_DIR_HOST}${nonarch_base_libdir}/firmware/bl32.bin"

TI_DM_PATH = "${STAGING_DIR_HOST}${nonarch_base_libdir}/firmware/ti-dm/${PLAT_SFX}/${DM_FIRMWARE}"

PACKAGECONFIG[ap-trusted-rom] = "BL1=${ATF_BL1_PATH},,trusted-firmware-a"
PACKAGECONFIG[atf] = "BL31=${ATF_BL31_PATH},,trusted-firmware-a"
PACKAGECONFIG[optee] = "TEE=${OPTEE_BL32_PATH},,optee-os"
PACKAGECONFIG[dm] = "TI_DM=${TI_DM_PATH},,ti-dm-fw"

PACKAGECONFIG:append:aarch64 = " atf optee"
PACKAGECONFIG:append:j721e = " dm"
PACKAGECONFIG:append:j7200 = " dm"
PACKAGECONFIG:append:j721s2 = " dm"
PACKAGECONFIG:append:j784s4 = " dm"
PACKAGECONFIG:append:j722s = " dm"
PACKAGECONFIG:append:j742s2 = " dm"
PACKAGECONFIG:append:am62xx = " dm"
PACKAGECONFIG:append:am62axx = " dm"
PACKAGECONFIG:append:am62dxx = " dm"
PACKAGECONFIG:append:am62lxx = " ap-trusted-rom"
PACKAGECONFIG:append:am62pxx = " dm"

COMPATIBLE_MACHINE = "(ti-soc)"

BINMAN_INDIRS = "${STAGING_DIR_HOST}${nonarch_base_libdir}/firmware"

EXTRA_OEMAKE += "${PACKAGECONFIG_CONFARGS} BINMAN_INDIRS=${BINMAN_INDIRS}"

PROVIDES += "u-boot"
PKG:${PN} = "u-boot"
PKG:${PN}-dev = "u-boot-dev"
PKG:${PN}-dbg = "u-boot-dbg"

# Support for secure devices - detailed info is in doc/README.ti-secure
inherit ti-secdev

SYSROOT_DIRS += "/boot"

# SPL (Second Program Loader) to be loaded over UART
SPL_UART_BINARY = "u-boot-spl.bin"
SPL_UART_BINARY:k3r5 = ""

SPL_UART_IMAGE ?= "${SPL_UART_BINARY}-${MACHINE}-${UBOOT_VERSION}"
SPL_UART_SYMLINK ?= "${SPL_UART_BINARY}-${MACHINE}"

SPL_HS_MLO_BINARY = "u-boot-spl_HS_MLO"
SPL_HS_MLO_IMAGE = "u-boot-spl_HS_MLO-${MACHINE}-${UBOOT_VERSION}"
SPL_HS_MLO_SYMLINK = "u-boot-spl_HS_MLO-${MACHINE}"

# HS XLD
UBOOT_HS_XLD_BINARY = "u-boot-spl_HS_X-LOADER"
UBOOT_HS_XLD_IMAGE = "u-boot-spl_HS_X-LOADER-${MACHINE}-${UBOOT_VERSION}"
UBOOT_HS_XLD_SYMLINK = "u-boot-spl_HS_X-LOADER-${MACHINE}"

# HS MLO
UBOOT_HS_MLO_BINARY = "u-boot_HS_MLO"
UBOOT_HS_MLO_IMAGE = "u-boot_HS_MLO-${MACHINE}-${UBOOT_VERSION}"
UBOOT_HS_MLO_SYMLINK = "u-boot_HS_MLO-${MACHINE}"

# HS ISSW
UBOOT_HS_ISSW_BINARY = "u-boot-spl_HS_ISSW"
UBOOT_HS_ISSW_IMAGE = "u-boot-spl_HS_ISSW-${MACHINE}-${UBOOT_VERSION}"
UBOOT_HS_ISSW_SYMLINK = "u-boot-spl_HS_ISSW-${MACHINE}"

# HS 2ND
UBOOT_HS_2ND_BINARY = "u-boot-spl_HS_2ND"
UBOOT_HS_2ND_IMAGE = "u-boot-spl_HS_2ND-${MACHINE}-${UBOOT_VERSION}"
UBOOT_HS_2ND_SYMLINK = "u-boot-spl_HS_2ND-${MACHINE}"

uboot_compile:append () {
    if ! [ -f ${B}/${UBOOT_BINARY} ]; then
        ln -s spl/u-boot-spl.${UBOOT_SUFFIX} ${B}/${UBOOT_BINARY}
    fi
}

uboot_compile_config_copy_binary:k3r5 () {
    config=$1
    type=$2
    binary=$3

    builddir="${builddir}"

    if ! [ -L ${B}/${builddir}/${UBOOT_BINARYNAME}-${type}.${UBOOT_SUFFIX} ] && ! [ -f ${B}/${builddir}/${UBOOT_BINARYNAME}-${type}.${UBOOT_SUFFIX} ]; then
        ln -s ${B}/${builddir}/spl/u-boot-spl.${UBOOT_SUFFIX} ${B}/${builddir}/${UBOOT_BINARYNAME}-${type}.${UBOOT_SUFFIX}
    fi

    if ! [ -L ${B}/${builddir}/${binary} ] && ! [ -f ${B}/${builddir}/${binary} ]; then
        ln -s ${B}/${builddir}/spl/u-boot-spl.${UBOOT_SUFFIX} ${B}/${builddir}/${binary}
    fi
}

uboot_install_config:append () {
    if [ "x${SPL_UART_BINARY}" != "x" ]; then
        install ${B}/${builddir}/spl/${SPL_UART_BINARY} ${D}/boot/${SPL_UART_IMAGE}-${type}
        ln -sf ${SPL_UART_IMAGE}-${type} ${D}/boot/${SPL_UART_BINARY}-${type}
        ln -sf ${SPL_UART_IMAGE}-${type} ${D}/boot/${SPL_UART_BINARY}
    fi
    if [ -f ${B}/${builddir}/${SPL_HS_MLO_BINARY} ]; then
        install ${B}/${builddir}/${SPL_HS_MLO_BINARY} ${D}/boot/${SPL_HS_MLO_IMAGE}-${type}
        ln -sf ${SPL_HS_MLO_IMAGE}-${type} ${D}/boot/${SPL_HS_MLO_BINARY}-${type}
        ln -sf ${SPL_HS_MLO_IMAGE}-${type} ${D}/boot/${SPL_HS_MLO_BINARY}
    fi
    if [ -f ${B}/${builddir}/${UBOOT_HS_XLD_BINARY} ]; then
        install ${B}/${builddir}/${UBOOT_HS_XLD_BINARY} ${D}/boot/${UBOOT_HS_XLD_IMAGE}-${type}
        ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${D}/boot/${UBOOT_HS_XLD_BINARY}-${type}
        ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${D}/boot/${UBOOT_HS_XLD_BINARY}
    fi
    if [ -f ${B}/${builddir}/${UBOOT_HS_MLO_BINARY} ]; then
        install ${B}/${builddir}/${UBOOT_HS_MLO_BINARY} ${D}/boot/${UBOOT_HS_MLO_IMAGE}-${type}
        ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${D}/boot/${UBOOT_HS_MLO_BINARY}-${type}
        ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${D}/boot/${UBOOT_HS_MLO_BINARY}
    fi
    if [ -f ${B}/${builddir}/${UBOOT_HS_ISSW_BINARY} ]; then
        install ${B}/${builddir}/${UBOOT_HS_ISSW_BINARY} ${D}/boot/${UBOOT_HS_ISSW_IMAGE}-${type}
        ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${D}/boot/${UBOOT_HS_ISSW_BINARY}-${type}
        ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${D}/boot/${UBOOT_HS_ISSW_BINARY}
    fi
    if [ -f ${B}/${builddir}/${UBOOT_HS_2ND_BINARY} ]; then
        install ${B}/${builddir}/${UBOOT_HS_2ND_BINARY} ${D}/boot/${UBOOT_HS_2ND_IMAGE}-${type}
        ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${D}/boot/${UBOOT_HS_2ND_BINARY}-${type}
        ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${D}/boot/${UBOOT_HS_2ND_BINARY}
    fi
}

uboot_install:append () {
    if [ "x${SPL_UART_BINARY}" != "x" ]; then
        install ${B}/spl/${SPL_UART_BINARY} ${D}/boot/${SPL_UART_IMAGE}
        ln -sf ${SPL_UART_IMAGE} ${D}/boot/${SPL_UART_BINARY}
    fi
    if [ -f ${B}/${SPL_HS_MLO_BINARY} ]; then
        install ${B}/${SPL_HS_MLO_BINARY} ${D}/boot/${SPL_HS_MLO_IMAGE}
        ln -sf ${SPL_HS_MLO_IMAGE} ${D}/boot/${SPL_HS_MLO_BINARY}
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
}

do_deploy:append:k3r5 () {
	rm -f ${DEPLOYDIR}/u-boot-initial-env*
	rm -f ${DEPLOYDIR}/${PN}-initial-env*
	rm -f ${DEPLOYDIR}/u-boot-initial-env-${MACHINE}*
	rm -f ${DEPLOYDIR}/${PN}-initial-env-${MACHINE}*
	rm -f ${DEPLOYDIR}/u-boot-initial-env-${MACHINE}-${UBOOT_VERSION}*
	rm -f ${DEPLOYDIR}/${PN}-initial-env-${MACHINE}-${UBOOT_VERSION}*
}

uboot_deploy_config:append:k3r5 () {
	for f in ${B}/${builddir}/tiboot3-*.bin; do
		if [ -f "$f" ]; then
			f_base=$(basename $f)
			install -m 644 $f ${DEPLOYDIR}/${f_base}-${type}
			ln -sf ${f_base}-${type} ${DEPLOYDIR}/${f_base}
		fi
	done

	for f in ${B}/${builddir}/sysfw*.itb; do
		if [ -f "$f" ]; then
			f_base=$(basename $f)
			install -m 644 $f ${DEPLOYDIR}/${f_base}-${type}
			ln -sf ${f_base}-${type} ${DEPLOYDIR}/${f_base}
		fi
	done
}

uboot_deploy_config:append:am62lxx () {
	for f in ${B}/${builddir}/tiboot3-*.bin; do
		if [ -f "$f" ]; then
			f_base=$(basename $f)
			install -m 644 $f ${DEPLOYDIR}/${f_base}-${type}
			ln -sf ${f_base}-${type} ${DEPLOYDIR}/${f_base}
		fi
	done
}

uboot_deploy:append:k3r5 () {
    for f in ${B}/tiboot3-*.bin; do
		if [ -f "$f" ]; then
			install -m 644 $f ${DEPLOYDIR}/
		fi
	done

	for f in ${B}/sysfw*.itb; do
		if [ -f "$f" ]; then
			install -m 644 $f ${DEPLOYDIR}/
		fi
	done
}

uboot_deploy:append:am62lxx () {
	for f in ${B}/tiboot3*.bin; do
		if [ -f "$f" ]; then
			install -m 644 $f ${DEPLOYDIR}/
		fi
	done
}

uboot_deploy_config:append () {
    cd ${DEPLOYDIR}
    if [ "x${SPL_UART_BINARY}" != "x" ]; then
        install ${B}/${builddir}/spl/${SPL_UART_BINARY} ${SPL_UART_IMAGE}-${type}
        rm -f ${SPL_UART_BINARY} ${SPL_UART_SYMLINK}
        ln -sf ${SPL_UART_IMAGE}-${type} ${SPL_UART_BINARY}-${type}
        ln -sf ${SPL_UART_IMAGE}-${type} ${SPL_UART_BINARY}
        ln -sf ${SPL_UART_IMAGE}-${type} ${SPL_UART_SYMLINK}-${type}
        ln -sf ${SPL_UART_IMAGE}-${type} ${SPL_UART_SYMLINK}
    fi
    if [ -f ${B}/${builddir}/${SPL_HS_MLO_BINARY} ]; then
        install ${B}/${builddir}/${SPL_HS_MLO_BINARY} ${SPL_HS_MLO_IMAGE}-${type}
        rm -f ${SPL_HS_MLO_BINARY} ${SPL_HS_MLO_SYMLINK}
        ln -sf ${SPL_HS_MLO_IMAGE}-${type} ${SPL_HS_MLO_SYMLINK}-${type}
        ln -sf ${SPL_HS_MLO_IMAGE}-${type} ${SPL_HS_MLO_SYMLINK}
        ln -sf ${SPL_HS_MLO_IMAGE}-${type} ${SPL_HS_MLO_BINARY}-${type}
        ln -sf ${SPL_HS_MLO_IMAGE}-${type} ${SPL_HS_MLO_BINARY}
    fi
    if [ -f ${B}/${builddir}/${UBOOT_HS_XLD_BINARY} ]; then
        install ${B}/${builddir}/${UBOOT_HS_XLD_BINARY} ${UBOOT_HS_XLD_IMAGE}-${type}
        rm -f ${UBOOT_HS_XLD_BINARY} ${UBOOT_HS_XLD_SYMLINK}
        ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${UBOOT_HS_XLD_SYMLINK}-${type}
        ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${UBOOT_HS_XLD_SYMLINK}
        ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${UBOOT_HS_XLD_BINARY}-${type}
        ln -sf ${UBOOT_HS_XLD_IMAGE}-${type} ${UBOOT_HS_XLD_BINARY}
    fi
    if [ -f ${B}/${builddir}/${UBOOT_HS_MLO_BINARY} ]; then
        install ${B}/${builddir}/${UBOOT_HS_MLO_BINARY} ${UBOOT_HS_MLO_IMAGE}-${type}
        rm -f ${UBOOT_HS_MLO_BINARY} ${UBOOT_HS_MLO_SYMLINK}
        ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${UBOOT_HS_MLO_SYMLINK}-${type}
        ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${UBOOT_HS_MLO_SYMLINK}
        ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${UBOOT_HS_MLO_BINARY}-${type}
        ln -sf ${UBOOT_HS_MLO_IMAGE}-${type} ${UBOOT_HS_MLO_BINARY}
    fi
    if [ -f ${B}/${builddir}/${UBOOT_HS_ISSW_BINARY} ]; then
        install ${B}/${builddir}/${UBOOT_HS_ISSW_BINARY} ${UBOOT_HS_ISSW_IMAGE}-${type}
        rm -f ${UBOOT_HS_ISSW_BINARY} ${UBOOT_HS_ISSW_SYMLINK}
        ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${UBOOT_HS_ISSW_SYMLINK}-${type}
        ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${UBOOT_HS_ISSW_SYMLINK}
        ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${UBOOT_HS_ISSW_BINARY}-${type}
        ln -sf ${UBOOT_HS_ISSW_IMAGE}-${type} ${UBOOT_HS_ISSW_BINARY}
    fi
    if [ -f ${B}/${builddir}/${UBOOT_HS_2ND_BINARY} ]; then
        install ${B}/${builddir}/${UBOOT_HS_2ND_BINARY} ${UBOOT_HS_2ND_IMAGE}-${type}
        rm -f ${UBOOT_HS_2ND_BINARY} ${UBOOT_HS_2ND_SYMLINK}
        ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${UBOOT_HS_2ND_SYMLINK}-${type}
        ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${UBOOT_HS_2ND_SYMLINK}
        ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${UBOOT_HS_2ND_BINARY}-${type}
        ln -sf ${UBOOT_HS_2ND_IMAGE}-${type} ${UBOOT_HS_2ND_BINARY}
    fi
}

uboot_deploy:append () {
    cd ${DEPLOYDIR}
    if [ "x${SPL_UART_BINARY}" != "x" ]; then
        install ${B}/spl/${SPL_UART_BINARY} ${SPL_UART_IMAGE}
        rm -f ${SPL_UART_BINARY} ${SPL_UART_SYMLINK}
        ln -sf ${SPL_UART_IMAGE} ${SPL_UART_BINARY}
        ln -sf ${SPL_UART_IMAGE} ${SPL_UART_SYMLINK}
    fi
    if [ -f ${B}/${SPL_HS_MLO_BINARY} ]; then
        install ${B}/${SPL_HS_MLO_BINARY} ${SPL_HS_MLO_IMAGE}
        rm -f ${SPL_HS_MLO_BINARY} ${SPL_HS_MLO_SYMLINK}
        ln -sf ${SPL_HS_MLO_IMAGE} ${SPL_HS_MLO_SYMLINK}
        ln -sf ${SPL_HS_MLO_IMAGE} ${SPL_HS_MLO_BINARY}
    fi
    if [ -f ${B}/${UBOOT_HS_XLD_BINARY} ]; then
        install ${B}/${UBOOT_HS_XLD_BINARY} ${UBOOT_HS_XLD_IMAGE}
        rm -f ${UBOOT_HS_XLD_BINARY} ${UBOOT_HS_XLD_SYMLINK}
        ln -sf ${UBOOT_HS_XLD_IMAGE} ${UBOOT_HS_XLD_SYMLINK}
        ln -sf ${UBOOT_HS_XLD_IMAGE} ${UBOOT_HS_XLD_BINARY}
    fi
    if [ -f ${B}/${UBOOT_HS_MLO_BINARY} ]; then
        install ${B}/${UBOOT_HS_MLO_BINARY} ${UBOOT_HS_MLO_IMAGE}
        rm -f ${UBOOT_HS_MLO_BINARY} ${UBOOT_HS_MLO_SYMLINK}
        ln -sf ${UBOOT_HS_MLO_IMAGE} ${UBOOT_HS_MLO_SYMLINK}
        ln -sf ${UBOOT_HS_MLO_IMAGE} ${UBOOT_HS_MLO_BINARY}
    fi
    if [ -f ${B}/${UBOOT_HS_ISSW_BINARY} ]; then
        install ${B}/${UBOOT_HS_ISSW_BINARY} ${UBOOT_HS_ISSW_IMAGE}
        rm -f ${UBOOT_HS_ISSW_BINARY} ${UBOOT_HS_ISSW_SYMLINK}
        ln -sf ${UBOOT_HS_ISSW_IMAGE} ${UBOOT_HS_ISSW_SYMLINK}
        ln -sf ${UBOOT_HS_ISSW_IMAGE} ${UBOOT_HS_ISSW_BINARY}
    fi
    if [ -f ${B}/${UBOOT_HS_2ND_BINARY} ]; then
        install ${B}/${UBOOT_HS_2ND_BINARY} ${UBOOT_HS_2ND_IMAGE}
        rm -f ${UBOOT_HS_2ND_BINARY} ${UBOOT_HS_2ND_SYMLINK}
        ln -sf ${UBOOT_HS_2ND_IMAGE} ${UBOOT_HS_2ND_SYMLINK}
        ln -sf ${UBOOT_HS_2ND_IMAGE} ${UBOOT_HS_2ND_BINARY}
    fi
}

do_deploy:append:am62xx() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${S}/tools/logos/ti_logo_414x97_32bpp.bmp.gz ${DEPLOYDIR}
}

do_deploy:append:am62pxx() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${S}/tools/logos/ti_logo_414x97_32bpp.bmp.gz ${DEPLOYDIR}
}

do_install:append:ti-falcon() {
	install -m 0644 ${B}/tifalcon.bin ${D}/boot
}

FALCON_PKG = ""
FALCON_PKG:ti-falcon = "${PN}-falcon "

PACKAGES:prepend:am62xx-evm = "${FALCON_PKG} "
PACKAGES:prepend:am62axx-evm = "${FALCON_PKG} "
PACKAGES:prepend:am62pxx-evm = "${FALCON_PKG} "
PACKAGES:prepend:am62xx-lp-evm = "${FALCON_PKG} "

TOOLCHAIN = "gcc"
