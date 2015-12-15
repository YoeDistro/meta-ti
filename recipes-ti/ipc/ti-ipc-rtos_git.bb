require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc
require ti-ipc.inc

PR = "${INC_PR}.2"

DEPENDS = "ti-xdctools ti-sysbios"
DEPENDS_append_keystone = " ti-cgt6x-native \
                            gcc-arm-none-eabi-native \
"
DEPENDS_append_omap-a15 = " ti-cgt6x-native \
                            ti-ccsv6-native \
                            gcc-arm-none-eabi-native \
"

PACKAGES =+ "${PN}-fw"
FILES_${PN}-fw = "${base_libdir}/firmware/*"
FILES_${PN}-dev += "${IPC_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN}-fw += "arch"
INSANE_SKIP_${PN}-dev += "arch"

ALLOW_EMPTY_${PN} = "1"

IPC_TARGETS = ""
IPC_TARGETS_omap-a15 = "\
    gnu.targets.arm.A15F="${GCC_ARM_NONE_TOOLCHAIN}" \
    ti.targets.elf.C66="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.arm.elf.M4="${M4_TOOLCHAIN_INSTALL_DIR}" \
    ti.targets.arm.elf.M4F="${M4_TOOLCHAIN_INSTALL_DIR}" \
"

IPC_TARGETS_keystone = " \
    gnu.targets.arm.A15F="${GCC_ARM_NONE_TOOLCHAIN}" \
    ti.targets.elf.C66="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
"

EXTRA_OEMAKE = "\
    PLATFORM=${PLATFORM} \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR}" \
    ${IPC_TARGETS} -f ipc-bios.mak\
"

do_compile() {
  oe_runmake clean
  oe_runmake all
}

do_compile_append() {
  sourceipk_do_create_srcipk
}

do_install() {
    install -d ${D}${IPC_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${IPC_INSTALL_DIR_RECIPE}

    install -d ${D}${base_libdir}/firmware
    find . -name "*.xe66" -type f | xargs -I {} install -m 0644 {} ${D}${base_libdir}/firmware/
}
