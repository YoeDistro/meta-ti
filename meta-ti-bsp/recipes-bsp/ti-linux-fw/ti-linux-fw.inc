SUMMARY = "Common include for TI Linux firmware"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "2022.01"
INC_PR = "r4"

# Firmware versions
CORESDK_RTOS_VERSION = "08.02.00.04"
PRUETH_FW_AM65X_VERSION = "08.00.00.20"
PRUETH_FW_AM65X_SR2_VERSION = "02.02.15.04"
GOODIX_FW_VERSION = "1.0.0.0"
CADENCE_MHDP_FW_VERSION = "2.1.0"
IMG_DEC_FW_VERSION = "1.0"
CNM_WAVE521_FW_VERSION = "1.0.7"
TI_DM_FW_VERSION = "11.00.09"
TI_SYSFW_VERSION = "11.00.07"
TI_HSM_DEMO_FW_VERSION = "11.00.09"

TI_LINUX_FW_SRCREV ?= "1e5d64d9385d6472d29e11ac7021765004ddff27"
SRCREV = "${TI_LINUX_FW_SRCREV}"

BRANCH ?= "ti-linux-firmware"

TI_LINUX_FW_GIT_URI ?= "git://git.ti.com/git/processor-firmware/ti-linux-firmware.git"
TI_LINUX_FW_GIT_PROTOCOL ?= "https"
TI_LINUX_FW_GIT_BRANCH ?= "branch=${BRANCH}"

SRC_URI = "${TI_LINUX_FW_GIT_URI};protocol=${TI_LINUX_FW_GIT_PROTOCOL};${TI_LINUX_FW_GIT_BRANCH}"

S = "${WORKDIR}/git"

CLEANBROKEN = "1"

# Make sure that lib/firmware, and all its contents are part of the package
FILES:${PN} = "${nonarch_base_libdir}/firmware"

# This is used to prevent the build system from stripping the firmwares
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

# This is used to prevent the build system from splitting out the firmware debug info into a separate file
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# Disable arch checking as firmware is likely to be a different arch from the Yocto build
INSANE_SKIP:${PN} += "arch"

# Firmware in Linux Firmware has no configure nor build steps
do_compile[noexec] = "1"
do_configure[noexec] = "1"
