require u-boot-ti.inc

# DEPEND on dtc-native for mainline u-boot because the mainline u-boot depends
# on some of the latest syntax constructs for an appended in dtb used for
# items like secure boot/image signing.
DEPENDS += "dtc-native"

DESCRIPTION = "Mainline u-boot bootloader"

DEFAULT_PREFERENCE = "-1"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"

PV = "2014.07"

SRC_URI = "git://git.denx.de/u-boot.git;protocol=git;branch=${BRANCH}"

BRANCH ?= "master"

# Corresponds to tag v2014.07
SRCREV = "524123a70761110c5cf3ccc5f52f6d4da071b959"

SPL_BINARY = "MLO"
SPL_UART_BINARY = "u-boot-spl.bin"

SPL_BINARY_omapl138 = ""
SPL_UART_BINARY_omapl138 = ""
