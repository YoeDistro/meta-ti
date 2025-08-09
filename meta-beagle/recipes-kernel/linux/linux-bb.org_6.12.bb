# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.12:"

SECTION = "kernel"
SUMMARY = "BeagleBoard.org Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "pocketbeagle2|beagle.*"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

# Extra DT overlays/capes
KERNEL_DEVICETREE:append:armv7a = " \
    ti/omap/BB-ADC-00A0.dtbo \
    ti/omap/BB-BBBW-WL1835-00A0.dtbo \
    ti/omap/BB-BBGG-WL1835-00A0.dtbo \
    ti/omap/BB-BBGW-WL1835-00A0.dtbo \
    ti/omap/BB-BONE-4D5R-01-00A1.dtbo \
    ti/omap/BB-BONE-eMMC1-01-00A0.dtbo \
    ti/omap/BB-BONE-LCD4-01-00A1.dtbo \
    ti/omap/BB-BONE-NH7C-01-A0.dtbo \
    ti/omap/BB-CAPE-DISP-CT4-00A0.dtbo \
    ti/omap/BB-HDMI-TDA998x-00A0.dtbo \
    ti/omap/BB-I2C1-MCP7940X-00A0.dtbo \
    ti/omap/BB-I2C1-RTC-DS3231.dtbo \
    ti/omap/BB-I2C1-RTC-PCF8563.dtbo \
    ti/omap/BB-I2C2-BME680.dtbo \
    ti/omap/BB-I2C2-MPU6050.dtbo \
    ti/omap/BB-LCD-ADAFRUIT-24-SPI1-00A0.dtbo \
    ti/omap/BB-NHDMI-TDA998x-00A0.dtbo \
    ti/omap/BBORG_COMMS-00A2.dtbo \
    ti/omap/BBORG_FAN-A000.dtbo \
    ti/omap/BBORG_RELAY-00A2.dtbo \
    ti/omap/BB-SPIDEV0-00A0.dtbo \
    ti/omap/BB-SPIDEV1-00A0.dtbo \
    ti/omap/BB-UART1-00A0.dtbo \
    ti/omap/BB-UART2-00A0.dtbo \
    ti/omap/BB-UART4-00A0.dtbo \
    ti/omap/BB-W1-P9.12-00A0.dtbo \
    ti/omap/BONE-ADC.dtbo \
    ti/omap/M-BB-BBG-00A0.dtbo \
    ti/omap/M-BB-BBGG-00A0.dtbo \
    ti/omap/PB-MIKROBUS-0.dtbo \
    ti/omap/PB-MIKROBUS-1.dtbo \
"

KERNEL_DEVICETREE:append:aarch64 = " \
    ti/BONE-I2C1.dtbo \
    ti/BONE-I2C2.dtbo \
    ti/BONE-I2C3.dtbo \
"

S = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}"

# 6.12.34 version for 32-bit
SRCREV:armv7a = "9e419b26243bd7efcd103ae0f6456f828592b34b"
PV:armv7a = "6.12.34+git"
BRANCH:armv7a = "v6.12.34-ti-arm32-r12"

# 6.12.34 version for 64-bit
SRCREV:aarch64 = "9ca36b8c54806a037f357efcd40eaa8399798b05"
PV:aarch64 = "6.12.34+git"
BRANCH:aarch64 = "v6.12.34-ti-arm64-r46"

KERNEL_GIT_URI = "git://github.com/beagleboard/linux.git"
