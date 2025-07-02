# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

SECTION = "kernel"
SUMMARY = "BeagleBoard.org Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "beagle.*"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

# Extra DT overlays/capes
KERNEL_DEVICETREE:append:armv7a = " \
AM335X-PRU-UIO-00A0.dtbo \
BB-ADC-00A0.dtbo \
BB-BBBW-WL1835-00A0.dtbo \
BB-BBGG-WL1835-00A0.dtbo \
BB-BBGW-WL1835-00A0.dtbo \
BB-BONE-4D5R-01-00A1.dtbo \
BB-BONE-eMMC1-01-00A0.dtbo \
BB-BONE-LCD4-01-00A1.dtbo \
BB-BONE-NH7C-01-A0.dtbo \
BB-CAPE-DISP-CT4-00A0.dtbo \
BB-HDMI-TDA998x-00A0.dtbo \
BB-I2C1-MCP7940X-00A0.dtbo \
BB-I2C1-RTC-DS3231.dtbo \
BB-I2C1-RTC-PCF8563.dtbo \
BB-I2C2-BME680.dtbo \
BB-I2C2-MPU6050.dtbo \
BB-LCD-ADAFRUIT-24-SPI1-00A0.dtbo \
BB-NHDMI-TDA998x-00A0.dtbo \
BBORG_COMMS-00A2.dtbo \
BBORG_FAN-A000.dtbo \
BBORG_RELAY-00A2.dtbo \
BB-SPIDEV0-00A0.dtbo \
BB-SPIDEV1-00A0.dtbo \
BB-UART1-00A0.dtbo \
BB-UART2-00A0.dtbo \
BB-UART4-00A0.dtbo \
BB-W1-P9.12-00A0.dtbo \
BONE-ADC.dtbo \
M-BB-BBG-00A0.dtbo \
M-BB-BBGG-00A0.dtbo \
PB-MIKROBUS-0.dtbo \
PB-MIKROBUS-1.dtbo \
"

S = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}"

# 6.1.83 version for 32-bit
SRCREV:armv7a = "be9b910b08dfd674e47a2643f35080b005ac3792"
PV:armv7a = "6.1.83+git"
BRANCH:armv7a = "v6.1.83-ti-r35"

# 6.1.83 version for 64-bit
SRCREV:aarch64 = "135558243d5a98ed39812b797d2ba6295ef3b86a"
PV:aarch64 = "6.1.83+git"
BRANCH:aarch64 = "v6.1.83-ti-arm64-r64"

KERNEL_GIT_URI = "git://github.com/beagleboard/linux.git"

INSANE_SKIP:kernel-modules += "debug-deps"
