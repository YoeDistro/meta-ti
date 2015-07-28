require u-boot-ti.inc

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native"

DESCRIPTION = "u-boot bootloader for TI devices"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

PR = "r1"
PV_append = "+git${SRCPV}"

SRC_URI = "git://git.ti.com/ti-u-boot/ti-u-boot.git;protocol=git;branch=${BRANCH}"

BRANCH ?= "ti-u-boot-2015.07"

SRCREV = "8dcdcb22f9d06df1ac411b2fe70c06adcd15237b"

SPL_BINARY = "MLO"
SPL_UART_BINARY = "u-boot-spl.bin"
