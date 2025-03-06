DESCRIPTION =  "Kernel drivers for the VXE384 and D5500 Video Accelerators found in the J721E TI SoC"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://README;beginline=11;endline=12;md5=ad10b552e357ac443ec5dd2e02912b1b"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "j721e"

DEPENDS += "virtual/kernel"

BRANCH = "scarthgap/k${PV}"

SRC_URI = "git://git.ti.com/multimedia/img-vxe-vxd.git;protocol=https;branch=${BRANCH}"

SRCREV = "99d063aa0ef53633057042570411b84bd967624b"

TARGET_PRODUCT:j721e = "j721e_linux"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += 'KERNEL_DIR="${STAGING_KERNEL_DIR}" PREFIX="${D}"'
