require u-boot-ti.inc

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native"

DESCRIPTION = "u-boot bootloader for TI devices"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"

PR = "r12"
PV_append = "+git${SRCPV}"

SRC_URI = "git://git.ti.com/ti-u-boot/ti-u-boot.git;protocol=git;branch=${BRANCH}"

BRANCH ?= "ti-u-boot-2014.07"

SRCREV = "4973fffe6605be850d0d9f6b15c9918aff49eb07"

SPL_BINARY = "MLO"
SPL_UART_BINARY = "u-boot-spl.bin"
