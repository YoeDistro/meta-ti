SUMMARY = "TI RTOS prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

inherit deploy

PLAT_SFX = ""
PLAT_SFX_j7 = "j721e"
PLAT_SFX_j7200-evm = "j7200"
PLAT_SFX_am65xx = "am65xx"
PLAT_SFX_am64xx = "am64xx"

require recipes-bsp/ti-sci-fw/ti-sci-fw.inc

CORESDK_RTOS_VERSION ?= "08.00.00.26"
PV = "${CORESDK_RTOS_VERSION}"

CLEANBROKEN = "1"
PR = "r1"


# Secure Build 
DEPENDS += "openssl-native"

FILES_${PN} += "${base_libdir}"

TI_SECURE_DEV_PKG ?= ""

RTOS_ETH_FW_DIR = "${S}/ti-eth/${PLAT_SFX}"
RTOS_DM_FW_DIR = "${S}/ti-dm/${PLAT_SFX}"
RTOS_IPC_FW_DIR = "${S}/ti-ipc/${PLAT_SFX}"

# For back-ward compatability keeping legacy firmware folder name
# TODO: fix this in next version
LEGACY_ETH_FW_DIR = "${D}${base_libdir}/firmware/ethfw"
LEGACY_IPC_FW_DIR = "${D}${base_libdir}/firmware/pdk-ipc"
LEGACY_DM_FW_DIR  = "${D}${base_libdir}/firmware/ethfw"

DM_FIRMWARE = "ipc_echo_testb_mcu1_0_release_strip.xer5f"

# Install
do_install_prepend_j7-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${RTOS_DM_FW_DIR}; \
		mv ${DM_FIRMWARE} ${DM_FIRMWARE}.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${DM_FIRMWARE}.unsigned ${DM_FIRMWARE}; \
	)
}

do_install() {
  CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
  install -d ${LEGACY_ETH_FW_DIR}
  install -d ${LEGACY_IPC_FW_DIR}
  cp ${CP_ARGS} "${RTOS_ETH_FW_DIR}/." ${LEGACY_ETH_FW_DIR}
  cp ${CP_ARGS} "${RTOS_IPC_FW_DIR}/." ${LEGACY_IPC_FW_DIR}
  cp ${CP_ARGS} "${RTOS_DM_FW_DIR}/." ${LEGACY_IPC_FW_DIR}
}

do_install_am65xx() {
  CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
  install -d ${LEGACY_IPC_FW_DIR}
  cp ${CP_ARGS} "${RTOS_IPC_FW_DIR}/." ${LEGACY_IPC_FW_DIR}
}

do_install_am64xx() {
  CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
  install -d ${LEGACY_IPC_FW_DIR}
  cp ${CP_ARGS} "${RTOS_IPC_FW_DIR}/." ${LEGACY_IPC_FW_DIR}
}


# make sure that lib/firmware, and all its contents are part of the package
FILES_${PN} += "${base_libdir}/firmware"


INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} += "arch"

do_compile[noexec] = "1"
do_configure[noexec] = "1"
