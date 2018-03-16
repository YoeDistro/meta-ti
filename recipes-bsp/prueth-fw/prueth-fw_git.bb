DESCRIPTION = "PRU Ethernet firmware for AM57xx, AM437x, AM335x and K2G"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://src/firmware_version.h;beginline=6;endline=53;md5=1f0a640a261059cdcbbcf01e6a739ff3"

require recipes-ti/includes/ti-paths.inc

ICSS_EMAC_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/icss-emac.git"
ICSS_EMAC_LLD_GIT_PROTOCOL = "git"
ICSS_EMAC_LLD_GIT_BRANCH = "master"
ICSS_EMAC_LLD_GIT_DESTSUFFIX = "git/ti/drv/icss_emac"

# Below commit ID corresponds to "DEV.ICSS_EMAC_LLD.01.00.00.11B"
ICSS_EMAC_LLD_SRCREV = "bd643d75a0322b6ca769d8ecbee310d859c0f761"

BRANCH = "${ICSS_EMAC_LLD_GIT_BRANCH}"
SRC_URI = "${ICSS_EMAC_LLD_GIT_URI};destsuffix=${ICSS_EMAC_LLD_GIT_DESTSUFFIX};protocol=${ICSS_EMAC_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${ICSS_EMAC_LLD_SRCREV}"
PV = "5.1.4"
PR = "r0"

S = "${WORKDIR}/${ICSS_EMAC_LLD_GIT_DESTSUFFIX}/firmware/icss_dualemac"

COMPATIBLE_MACHINE = "am57xx-evm|am437x-evm|am335x-evm|k2g"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "ti-cgt-pru-native"

PRU_VERSION = ""
PRU_VERSION_am335x-evm = "REV1"
PRU_VERSION_am437x-evm = "REV1"
PRU_VERSION_am57xx-evm = "REV2"
PRU_VERSION_k2g = "REV2"

FW_PREFIX = ""
FW_PREFIX_am335x-evm = "am335x"
FW_PREFIX_am437x-evm = "am437x"
FW_PREFIX_am57xx-evm = "am57xx"
FW_PREFIX_k2g = "k2g"


EXTRA_OEMAKE += "CL_PRU_INSTALL_PATH="${TI_CGT_PRU_INSTALL_DIR}" PRU_VERSION_LIST="${PRU_VERSION}""

do_install() {
	install -d ${D}${base_libdir}/firmware/ti-pruss
	install -m 0644 ./elf/${PRU_VERSION}/icss_dualemac_PRU0.elf \
		${D}${base_libdir}/firmware/ti-pruss/${FW_PREFIX}-pru0-prueth-fw.elf
	install -m 0644 ./elf/${PRU_VERSION}/icss_dualemac_PRU1.elf \
		${D}${base_libdir}/firmware/ti-pruss/${FW_PREFIX}-pru1-prueth-fw.elf
}

FILES_${PN} = "${base_libdir}/firmware"

INSANE_SKIP_${PN} = "arch"
