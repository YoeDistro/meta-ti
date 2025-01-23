require u-boot-ti.inc

SUMMARY = "Mainline U-Boot for TI devices"

PV = "2025.01"

UBOOT_GIT_URI = "git://source.denx.de/u-boot/u-boot.git"

SRC_URI += "file://0001-scripts-dtc-pylibfdt-libfdt.i_shipped-Use-SWIG_Appen.patch"

SRCREV = "6d41f0a39d6423c8e57e92ebbe9f8c0333a63f72"
