SUMMARY = "Second stage bootloader for c66x"
DESCRIPTION = "The Intermediate Bootloader (IBL) is a second stage \
bootloader for the c66x family of embedded processors. This second \
stage bootloader takes care of additional initializations, advisory \
setups, and errata workarounds that are not present in the first stage boot."

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/ibl.h;beginline=1;endline=34;md5=ee7d7a3305d1e524955996d1c5e31cb9"

require recipes-ti/includes/ti-paths.inc

DEPENDS = " ti-cgt6x-7-native \
            bison-native \
            flex-native \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "c66x"

CLEANBROKEN = "1"

IBL_BOOT_GIT_URI = "git://git.ti.com/git/keystone-rtos/ibl.git"
IBL_BOOT_GIT_PROTOCOL = "https"
IBL_BOOT_GIT_BRANCH = "master"
IBL_BOOT_SRCREV = "ed3f0989d8cdc813b7ca1ed899c4d84b63de3a75"

BRANCH = "${IBL_BOOT_GIT_BRANCH}"
SRC_URI = "${IBL_BOOT_GIT_URI};protocol=${IBL_BOOT_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${IBL_BOOT_SRCREV}"


PR = "r2"

S = "${WORKDIR}/git"

PATH_prepend = "${TI_CGT6X_7_INSTALL_DIR}/bin:"

IBLTARGETS = ""
IBLTARGETS_c665x-evm = "evm_c6657_i2c"
IBLTARGETS_c667x-evm = "evm_c6678_i2c"

IBLENDIAN = ""
IBLENDIAN_c665x-evm = "little"
IBLENDIAN_c667x-evm = "little"

export C6X_BASE_DIR="${TI_CGT6X_7_INSTALL_DIR}"
export TOOLSC6X="${C6X_BASE_DIR}"
export TOOLSC6XDOS="${C6X_BASE_DIR}"
export TOOLSBIOSC6XDOS="${C6X_BASE_DIR}"
export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"

do_compile() {
    cd src/make
    for t in ${IBLTARGETS}
    do
        for e in ${IBLENDIAN}
        do
            make ${t} ENDIAN=${e} I2C_BUS_ADDR=0x51
        done
    done
    cd -
}

do_install() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/boot/ibl
    cp -rP --preserve=mode,links,timestamps --no-preserve=ownership * ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/boot/ibl
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} = "arch file-rdeps"
