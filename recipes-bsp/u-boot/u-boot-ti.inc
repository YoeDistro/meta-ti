# UBOOT_LOCALVERSION can be set to add a tag to the end of the
# U-boot version string.  such as the commit id
def get_git_revision(p):
    import subprocess

    try:
        return subprocess.Popen("git rev-parse HEAD 2>/dev/null ", cwd=p, shell=True, stdout=subprocess.PIPE).communicate()[0].rstrip()
    except OSError:
        return None

UBOOT_LOCALVERSION = "-g${@get_git_revision('${S}').__str__()[:10]}"

UBOOT_SUFFIX ?= "img"

require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES += "u-boot"
PKG_${PN} = "u-boot"
PKG_${PN}-dev = "u-boot-dev"
PKG_${PN}-dbg = "u-boot-dbg"

S = "${WORKDIR}/git"

# SPL (Second Program Loader) to be loaded over UART
SPL_UART_BINARY ?= ""
SPL_UART_IMAGE ?= "${SPL_UART_BINARY}-${MACHINE}-${PV}-${PR}"
SPL_UART_SYMLINK ?= "${SPL_UART_BINARY}-${MACHINE}"

do_install_append () {
    if [ "x${SPL_UART_BINARY}" != "x" ]
    then
        install ${S}/spl/${SPL_UART_BINARY} ${D}/boot/${SPL_UART_IMAGE}
        ln -sf ${SPL_UART_IMAGE} ${D}/boot/${SPL_UART_BINARY}
    fi
}

do_deploy_append () {
    cd ${DEPLOYDIR}
    if [ "x${SPL_UART_BINARY}" != "x" ]
    then
        install ${S}/spl/${SPL_UART_BINARY} ${DEPLOYDIR}/${SPL_UART_IMAGE}
        rm -f ${DEPLOYDIR}/${SPL_UART_BINARY} ${DEPLOYDIR}/${SPL_UART_SYMLINK}
        ln -sf ${SPL_UART_IMAGE} ${DEPLOYDIR}/${SPL_UART_BINARY}
        ln -sf ${SPL_UART_IMAGE} ${DEPLOYDIR}/${SPL_UART_SYMLINK}
    fi
}
