require u-boot-ti.inc

# DEPEND on dtc-native for mainline u-boot because the mainline u-boot depends
# on some of the latest syntax constructs for an appended in dtb used for
# items like secure boot/image signing.
DEPENDS += "dtc-native"

DESCRIPTION = "Mainline u-boot bootloader"

DEFAULT_PREFERENCE = "-1"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=c7383a594871c03da76b3707929d2919"

PV = "2014.10+2015.01-rc4"

SRC_URI = "git://git.denx.de/u-boot.git;protocol=git;branch=${BRANCH}"

BRANCH ?= "master"

# Corresponds to tag v2015.01-rc4 plus more fixes
SRCREV = "b188067f39627d977bb1db67c8456e9aaab90743"

SPL_BINARY = "MLO"
SPL_UART_BINARY = "u-boot-spl.bin"

SPL_BINARY_omapl138 = ""
SPL_UART_BINARY_omapl138 = ""
