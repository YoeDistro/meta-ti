SUMMARY = "Precompiled Zephyr OpenAMP example firmware for TI K3 devices"

# Name of this demo's firmware
FW_NAME = "zephyr_openamp_rsc_table.elf"

# List of cores for which this demo has a build
FW_CORES        = ""
FW_CORES:am62xx = "mcu-m4f0_0"
FW_CORES:am64xx = "mcu-m4f0_0"

# Note: Everything below is common to all our Zephyr firmwares and can be
#       factored out to a common include file once we have more than one demo

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://github.com/glneo/zephyr-firmware.git;protocol=https;branch=main"
SRCREV = "ef5aec48e589902839ebae7ad753c68cf498c15f"
PV = "3.6.0"
#PR = "r1"

FW_PLAT = ""
FW_PLAT:am62xx = "am62"
FW_PLAT:am64xx = "am64"

FW_INSTALL_DIR = "${nonarch_base_libdir}/firmware/zephyr/${FW_PLAT}"

do_install() {
    for FW_CORE in ${FW_CORES}
    do
        install -d ${D}${FW_INSTALL_DIR}/${FW_CORE}
        install -m 0644 ${S}/${FW_PLAT}/${FW_CORE}/${FW_NAME} ${D}${FW_INSTALL_DIR}/${FW_CORE}
    done
}

# Make sure that lib/firmware, and all its contents are part of the package
FILES:${PN} = "${nonarch_base_libdir}/firmware"

# This is used to prevent the build system from stripping the firmwares
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

# This is used to prevent the build system from splitting out the firmware debug info into a separate file
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# This is a prebuilt with no external dependencies
INHIBIT_DEFAULT_DEPS = "1"

# Disable arch checking as firmware is likely to be a different arch from the Yocto build
INSANE_SKIP:${PN} += "arch"

# Zephyr-Firmware has no configure, compile, nor clean steps
do_configure[noexec] = "1"
do_compile[noexec] = "1"
CLEANBROKEN = "1"

inherit update-alternatives

python fw_generate_alternatives() {
    for fw_core_name in d.getVar('FW_CORES').split():
        fw_plat = d.getVar('FW_PLAT')
        fw_pkg_name = d.getVar('PN')
        fw_core_name_full = (fw_plat + "-" + fw_core_name + "-fw")

        # Create the firmware alternatives
        d.appendVar('ALTERNATIVE:%s' % fw_pkg_name, (fw_core_name_full + " "))

        # Set up firmware alternatives link names
        fw_alt_link = ("${nonarch_base_libdir}/firmware/" + fw_core_name_full)
        d.setVarFlag('ALTERNATIVE_LINK_NAME', fw_core_name_full, fw_alt_link)

        # Set up firmware alternatives link targets
        fw_alt_target_rename = "${FW_INSTALL_DIR}/" + fw_core_name + "/${FW_NAME}"
        d.setVarFlag('ALTERNATIVE_TARGET_%s' % fw_pkg_name, fw_core_name_full, fw_alt_target_rename)
}

do_package[prefuncs] += "fw_generate_alternatives"

ALTERNATIVE_PRIORITY = "20"
