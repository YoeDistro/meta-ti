SUMMARY = "TI Display Sharing Firmware"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "06.01.00.00+git${SRCPV}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy
inherit update-alternatives

PROTOCOL = "git"
BRANCH = "ti-linux-firmware"
SRCREV = "e7be1ab85266c47236330e2909997d20fc62e312"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=${PROTOCOL};branch=${BRANCH}"

S = "${WORKDIR}/git"

TI_DISP_SHARE_FW_DIR = "${S}/ti-display-sharing/"
TI_DISP_SHARE_FW_FILENAME = "ti-display-sharing-j721e.bin"

# make sure that lib/firmware, and all its contents are part of the package
FILES_${PN} += "${base_libdir}/firmware"

do_install() {
  install -d ${D}${base_libdir}/firmware
  install -m 0755 ${TI_DISP_SHARE_FW_DIR}/${TI_DISP_SHARE_FW_FILENAME} ${D}${base_libdir}/firmware
}

TARGET_MAIN_R5FSS0_1 = "j7-main-r5f0_1-fw"
ALTERNATIVE_${PN} = "j7-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/${TI_DISP_SHARE_FW_FILENAME}"
ALTERNATIVE_PRIORITY = "9"

# This is used to prevent the build system to strip the executables
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

# This is used to prevent the build system to split the debug info in a separate file
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
# As it likely to be a different arch from the Yocto build, disable checking by adding "arch" to INSANE_SKIP
INSANE_SKIP_${PN} += "arch"

# we don't want to configure and build the source code
do_compile[noexec] = "1"
do_configure[noexec] = "1"
