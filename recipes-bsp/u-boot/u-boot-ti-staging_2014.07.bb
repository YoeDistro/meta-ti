require u-boot-ti.inc

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native"

DESCRIPTION = "u-boot bootloader for TI devices"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"

PV = "2014.07"
PR = "r9+gitr${SRCPV}"

SRC_URI = "git://git.ti.com/ti-u-boot/ti-u-boot.git;protocol=git;branch=${BRANCH}"

BRANCH ?= "ti-u-boot-2014.07"

SRCREV = "67b4cea8897cef6797549c8fa1f89ee7e36b6cdd"

SPL_BINARY = "MLO"
SPL_UART_BINARY = "u-boot-spl.bin"
