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

KERNEL_DEVICETREE:beaglebone = " \
am335x-bone.dtb \
am335x-boneblack.dtb \
am335x-boneblack-pps.dtb \
am335x-boneblack-uboot.dtb \
am335x-boneblack-uboot-univ.dtb \
am335x-boneblack-wireless.dtb \
am335x-boneblue.dtb \
am335x-bonegreen.dtb \
am335x-bonegreen-gateway.dtb \
am335x-bonegreen-wireless.dtb \
am335x-bonegreen-wireless-uboot-univ.dtb \
am335x-pocketbeagle.dtb \
am335x-sancloud-bbe.dtb \
am335x-sancloud-bbe-extended-wifi.dtb \
am335x-sancloud-bbe-lite.dtb \
"

KERNEL_DEVICETREE:beagle-x15 = " \
am57xx-beagle-x15.dtb \
am57xx-beagle-x15-revb1.dtb \
am57xx-beagle-x15-revc.dtb \
am57xx-idk-lcd-osd101t2045.dtbo \
am57xx-idk-lcd-osd101t2587.dtbo \
"

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

KERNEL_DEVICETREE:beaglebone-ai64 = " \
ti/k3-j721e-beagleboneai64.dtb \
ti/k3-j721e-common-proc-board.dtb \
ti/k3-j721e-beagleboneai64-dsi-rpi-7inch-panel.dtbo \
ti/k3-j721e-common-proc-board-infotainment.dtbo \
ti/k3-j721e-evm-csi2-ov5640.dtbo \
ti/k3-j721e-evm-fusion.dtbo \
ti/k3-j721e-evm-gesi-exp-board.dtbo \
ti/k3-j721e-evm-pcie0-ep.dtbo \
ti/k3-j721e-evm-quad-port-eth-exp.dtbo \
ti/k3-j721e-evm-virt-mac-client.dtbo \
ti/k3-j721e-sk.dtb \
ti/k3-j721e-sk-csi2-ov5640.dtbo \
ti/k3-j721e-sk-csi2-rpi-imx219.dtbo \
ti/k3-j721e-sk-fusion.dtbo \
ti/k3-j721e-sk-rpi-hdr-ehrpwm.dtbo \
"

KERNEL_DEVICETREE:beagleplay = " \
ti/k3-am625-beagleplay.dtb \
ti/k3-am625-beagleplay-csi2-ov5640.dtbo \
ti/k3-am625-beagleplay-csi2-tevi-ov5640.dtbo \
ti/k3-am625-beagleplay-lincolntech-lcd185-panel.dtbo \
ti/k3-am625-beaglemod.dtb \
ti/k3-am625-beaglemod-audio.dtbo \
ti/k3-am625-beaglemod-can0.dtbo \
ti/k3-am625-beaglemod-can1.dtbo \
ti/k3-am625-beaglemod-csi0-ov5640.dtbo \
ti/k3-am625-beaglemod-eeprom.dtbo \
ti/k3-am625-beaglemod-eth.dtbo \
ti/k3-am625-beaglemod-hdmi.dtbo \
ti/k3-am625-beaglemod-io-expand.dtbo \
ti/k3-am625-beaglemod-lt-lcd185.dtbo \
ti/k3-am625-beaglemod-ospi-flash.dtbo \
ti/k3-am625-beaglemod-rs485-1.dtbo \
ti/k3-am625-beaglemod-rs485-2.dtbo \
ti/k3-am625-beaglemod-rtc.dtbo \
ti/k3-am625-beaglemod-wl1835.dtbo \
ti/k3-am625-sk.dtb \
ti/k3-am625-sk-dmtimer-pwm.dtbo \
ti/k3-am625-sk-ecap-capture.dtbo \
ti/k3-am625-sk-lincolntech-lcd185-panel.dtbo \
ti/k3-am625-sk-mcspi-loopback.dtbo \
ti/k3-am625-sk-microtips-mf101hie-panel.dtbo \
ti/k3-am625-sk-microtips-mf103hie-lcd2.dtbo \
ti/k3-am625-sk-pwm.dtbo \
ti/k3-am625-sk-rpi-hdr-ehrpwm.dtbo \
ti/k3-am62x-sk-csi2-imx219.dtbo \
ti/k3-am62x-sk-csi2-ov5640.dtbo \
ti/k3-am62x-sk-csi2-tevi-ov5640.dtbo \
ti/k3-am62x-sk-csi2-v3link-fusion.dtbo \
ti/k3-am62x-sk-eqep.dtbo \
ti/k3-am62x-sk-hdmi-audio.dtbo \
ti/k3-am62x-sk-hdmi-disable-fastboot.dtbo \
ti/k3-am62x-sk-lpm-wkup-sources.dtbo \
ti/k3-am62x-sk-mcan.dtbo \
"

S = "${WORKDIR}/git"

# 6.1.80 version for 32-bit
SRCREV:armv7a = "4ca9ea30768d58c8d4d56d03dd1eaf8c8feb7ef9"
PV:armv7a = "6.1.80+git"
BRANCH:armv7a = "v6.1.80-ti-r34"

# 6.1.80 version for 64-bit
SRCREV:aarch64 = "977c75e082620f15c06c72bcced30f787c14b390"
PV:aarch64 = "6.1.80+git"
BRANCH:aarch64 = "v6.1.80-ti-arm64-r49"

SRC_URI = " \
    git://github.com/beagleboard/linux.git;protocol=https;branch=${BRANCH} \
    file://defconfig \
"
