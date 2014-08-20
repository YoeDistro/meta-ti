require u-boot-ti.inc

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native"

DESCRIPTION = "u-boot bootloader for TI devices"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"

PV = "2014.07"
PR = "r1+gitr${SRCPV}"

SRC_URI = "git://git.ti.com/ti-u-boot/ti-u-boot.git;protocol=git;branch=${BRANCH}"

BRANCH ?= "ti-u-boot-2014.07"

# Corresponds to tag ti2014.07
SRCREV = "8bd803d2c552f5f85a79b4bfed7b322fbaf89e27"

SPL_BINARY = "MLO"
SPL_UART_BINARY = "u-boot-spl.bin"
