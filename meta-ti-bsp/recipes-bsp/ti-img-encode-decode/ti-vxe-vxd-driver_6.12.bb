SUMMARY =  "Kernel drivers for Video Accelerators found in the J721E TI SoC"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://README;beginline=11;endline=12;md5=ad10b552e357ac443ec5dd2e02912b1b"

inherit module

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "j721e"

DEPENDS += "virtual/kernel"

BRANCH = "scarthgap/k${PV}"

SRC_URI = "git://git.ti.com/git/multimedia/img-vxe-vxd.git;protocol=https;branch=${BRANCH}"

SRCREV = "038d9263c67900ea4eff631820b088a3dc3a6ad5"

TARGET_PRODUCT:j721e = "j721e_linux"

EXTRA_OEMAKE += 'KERNEL_DIR="${STAGING_KERNEL_DIR}" PREFIX="${D}"'
