require u-boot-ti.inc

SUMMARY = "Mainline U-Boot for TI devices"

PV = "2024.10"

UBOOT_GIT_URI = "git://source.denx.de/u-boot/u-boot.git"

SRC_URI += "file://0001-scripts-dtc-pylibfdt-libfdt.i_shipped-Use-SWIG_Appen.patch"

SRCREV = "f919c3a889f0ec7d63a48b5d0ed064386b0980bd"
