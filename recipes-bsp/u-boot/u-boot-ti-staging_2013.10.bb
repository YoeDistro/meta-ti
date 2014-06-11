require u-boot-ti.inc

DESCRIPTION = "u-boot bootloader for TI devices"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"

PV = "2013.10"
PR = "r8+gitr${SRCPV}"

SRC_URI = "git://git.ti.com/ti-u-boot/ti-u-boot.git;protocol=git;branch=${BRANCH}"

BRANCH ?= "ti-u-boot-2013.10"

# Corresponds to tag ti2013.12.01_amsdk-07.01
SRCREV = "259ff9a577cc66cb40e3cdff3e41628e466f0fef"

SPL_BINARY = "MLO"
SPL_UART_BINARY = "u-boot-spl.bin"
