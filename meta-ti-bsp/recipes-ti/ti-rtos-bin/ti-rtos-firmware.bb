SUMMARY = "TI RTOS prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

inherit deploy
inherit update-alternatives

PLAT_SFX = ""
PLAT_SFX:j7 = "j721e"
PLAT_SFX:j7200-evm = "j7200"
PLAT_SFX:j7200-hs-evm = "j7200"
PLAT_SFX:am65xx = "am65xx"
PLAT_SFX:am64xx = "am64xx"

FILESEXTRAPATHS:prepend := "${METATIBASE}/recipes-bsp/ti-sci-fw/files/:"
require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

CORESDK_RTOS_VERSION ?= "08.00.00.26"
PV = "${CORESDK_RTOS_VERSION}"

CLEANBROKEN = "1"
PR = "${INC_PR}.0"

# Secure Build 
DEPENDS += "openssl-native"

FILES:${PN} += "${base_libdir}"

TI_SECURE_DEV_PKG ?= ""

RTOS_ETH_FW_DIR = "${S}/ti-eth/${PLAT_SFX}"
RTOS_DM_FW_DIR = "${S}/ti-dm/${PLAT_SFX}"
RTOS_IPC_FW_DIR = "${S}/ti-ipc/${PLAT_SFX}"

# For back-ward compatability keeping legacy firmware folder name
# TODO: fix this in next version
LEGACY_ETH_FW_DIR = "${D}${base_libdir}/firmware/ethfw/"
LEGACY_IPC_FW_DIR = "${D}${base_libdir}/firmware/pdk-ipc/"
LEGACY_DM_FW_DIR  = "${D}${base_libdir}/firmware/pdk-ipc/"

DM_FIRMWARE = "ipc_echo_testb_mcu1_0_release_strip.xer5f"

# J7 HS support
do_install:prepend:j7-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${RTOS_DM_FW_DIR}; \
		mv ${DM_FIRMWARE} ${DM_FIRMWARE}.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${DM_FIRMWARE}.unsigned ${DM_FIRMWARE}; \
	)
}

# J7 HS support
do_install:prepend:j7200-hs-evm() {
        export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
        ( cd ${RTOS_DM_FW_DIR}; \
                mv ${DM_FIRMWARE} ${DM_FIRMWARE}.unsigned; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${DM_FIRMWARE}.unsigned ${DM_FIRMWARE}; \
        )
}

# Update the am64xx ipc binaries to be consistent with other platforms
do_install:prepend:am64xx() {
        ( cd ${RTOS_IPC_FW_DIR}; \
                mv am64-main-r5f0_0-fw ipc_echo_baremetal_test_mcu1_0_release_strip.xer5f; \
                mv am64-main-r5f0_1-fw ipc_echo_baremetal_test_mcu1_1_release_strip.xer5f; \
                mv am64-main-r5f1_0-fw ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f; \
                mv am64-main-r5f1_1-fw ipc_echo_baremetal_test_mcu2_1_release_strip.xer5f; \
                mv am64-mcu-m4f0_0-fw ipc_echo_baremetal_test_mcu3_0_release_strip.xer5f; \
        )
}

#Install all R5 & DSP ipc echo test binaries in lib/firmware/pdk-ipc, with softlinks up a level
do_install() {
	:
}

do_install:j7() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c66xdsp_1_release_strip.xe66 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c66xdsp_2_release_strip.xe66 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    # DM Firmware
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR}
    # ETH firmware
    install -d ${LEGACY_ETH_FW_DIR}
    install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f ${LEGACY_ETH_FW_DIR}
}

do_install:j7200-evm() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    # DM Firmware
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR}
    # ETH firmware
    install -d ${LEGACY_ETH_FW_DIR}
    install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f ${LEGACY_ETH_FW_DIR}
}

do_install:j7200-hs-evm() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    # DM Firmware
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR}
    # ETH firmware
    install -d ${LEGACY_ETH_FW_DIR}
    install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f ${LEGACY_ETH_FW_DIR}
}

do_install:am65xx() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
}

do_install:am64xx() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu1_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu3_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
}

# Set up names for the firmwares
ALTERNATIVE:${PN}:am65xx = "\
                    am65x-mcu-r5f0_0-fw \
                    am65x-mcu-r5f0_1-fw \
                    "

ALTERNATIVE:${PN}:am64xx = "\
                    am64-main-r5f0_0-fw \
                    am64-main-r5f0_1-fw \
                    am64-main-r5f1_0-fw \
                    am64-main-r5f1_1-fw \
                    am64-mcu-m4f0_0-fw \
                    "

ALTERNATIVE:${PN}:j7 = "\
                    j7-mcu-r5f0_0-fw \
                    j7-mcu-r5f0_1-fw \
                    j7-main-r5f0_0-fw \
                    j7-main-r5f0_1-fw \
                    j7-main-r5f1_0-fw \
                    j7-main-r5f1_1-fw \
                    j7-c66_0-fw \
                    j7-c66_1-fw \
                    j7-c71_0-fw\
                    "

ALTERNATIVE:${PN}:j7200-evm = "\
                    j7200-mcu-r5f0_0-fw \
                    j7200-mcu-r5f0_1-fw \
                    j7200-main-r5f0_0-fw \
                    j7200-main-r5f0_1-fw \
                    "

ALTERNATIVE:${PN}:j7200-hs-evm = "\
                    j7200-mcu-r5f0_0-fw \
                    j7200-mcu-r5f0_1-fw \
                    j7200-main-r5f0_0-fw \
                    j7200-main-r5f0_1-fw \
                    "

# Set up link names for the firmwares

TARGET_MCU_R5FSS0_0:am65xx = "am65x-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1:am65xx = "am65x-mcu-r5f0_1-fw"

TARGET_MAIN_R5FSS0_0:am64xx = "am64-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1:am64xx = "am64-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0:am64xx = "am64-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1:am64xx = "am64-main-r5f1_1-fw"
TARGET_MCU_M4FSS0_0:am64xx = "am64-mcu-m4f0_0-fw"

TARGET_MCU_R5FSS0_0:j7 = "j7-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1:j7 = "j7-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0:j7 = "j7-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1:j7 = "j7-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0:j7 = "j7-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1:j7 = "j7-main-r5f1_1-fw"
TARGET_C66_0:j7 = "j7-c66_0-fw"
TARGET_C66_1:j7 = "j7-c66_1-fw"
TARGET_C7X:j7 = "j7-c71_0-fw"

TARGET_MCU_R5FSS0_0:j7200-evm = "j7200-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1:j7200-evm = "j7200-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0:j7200-evm = "j7200-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1:j7200-evm = "j7200-main-r5f0_1-fw"

TARGET_MCU_R5FSS0_0:j7200-hs-evm = "j7200-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1:j7200-hs-evm = "j7200-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0:j7200-hs-evm = "j7200-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1:j7200-hs-evm = "j7200-main-r5f0_1-fw"

ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"

ALTERNATIVE_LINK_NAME[am64-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[am64-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0}"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1}"
ALTERNATIVE_LINK_NAME[am64-mcu-m4f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_M4FSS0_0}"

ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1}"
ALTERNATIVE_LINK_NAME[j7-c66_0-fw] = "${base_libdir}/firmware/${TARGET_C66_0}"
ALTERNATIVE_LINK_NAME[j7-c66_1-fw] = "${base_libdir}/firmware/${TARGET_C66_1}"
ALTERNATIVE_LINK_NAME[j7-c71_0-fw] = "${base_libdir}/firmware/${TARGET_C7X}"

ALTERNATIVE_LINK_NAME[j7200-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7200-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"

# Create the firmware alternatives

ALTERNATIVE_TARGET[am65x-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[am65x-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"

ALTERNATIVE_TARGET[am64-main-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[am64-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu1_1_release_strip.xer5f"
ALTERNATIVE_TARGET[am64-main-r5f1_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f"
ALTERNATIVE_TARGET[am64-main-r5f1_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu2_1_release_strip.xer5f"
ALTERNATIVE_TARGET[am64-mcu-m4f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu3_0_release_strip.xer5f"

ALTERNATIVE_TARGET[j7-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_testb_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw] = "${base_libdir}/firmware/ethfw/app_remoteswitchcfg_server_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f1_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f1_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-c66_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_1_release_strip.xe66"
ALTERNATIVE_TARGET[j7-c66_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_2_release_strip.xe66"
ALTERNATIVE_TARGET[j7-c71_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_1_release_strip.xe71"

ALTERNATIVE_TARGET[j7200-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_testb_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j7200-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw] = "${base_libdir}/firmware/ethfw/app_remoteswitchcfg_server_strip.xer5f"
ALTERNATIVE_TARGET[j7200-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f"

ALTERNATIVE_PRIORITY = "10"

# make sure that lib/firmware, and all its contents are part of the package
FILES:${PN} += "${base_libdir}/firmware"

# This is used to prevent the build system to_strip the executables
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
# This is used to prevent the build system to split the debug info in a separate file
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
# As it likely to be a different arch from the Yocto build, disable checking by adding "arch" to INSANE_SKIP
INSANE_SKIP:${PN} += "arch"

# we don't want to configure and build the source code
do_compile[noexec] = "1"
do_configure[noexec] = "1"
