SUMMARY = "TI RTOS prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

inherit deploy
inherit update-alternatives

PLAT_SFX = ""
PLAT_SFX_j7 = "j721e"
PLAT_SFX_j7-hs-evm = "j721e"
PLAT_SFX_j7200-evm = "j7200"
PLAT_SFX_j7200-hs-evm = "j7200"
PLAT_SFX_j721s2-evm = "j721s2"
PLAT_SFX_j721s2-hs-evm = "j721s2"
PLAT_SFX_j784s4-evm = "j784s4"
PLAT_SFX_j784s4-hs-evm = "j784s4"
PLAT_SFX_am65xx = "am65xx"
PLAT_SFX_am64xx = "am64xx"
PLAT_SFX_am62xx = "am62xx"
PLAT_SFX_am62xx-lp-evm = "am62xx"
PLAT_SFX_am62axx = "am62axx"

FILESEXTRAPATHS_prepend := "${METATIBASE}/recipes-bsp/ti-sci-fw/files/:"
require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

CORESDK_RTOS_VERSION ?= "08.00.00.26"
PV = "${CORESDK_RTOS_VERSION}"

CLEANBROKEN = "1"
PR = "${INC_PR}.0"

# Secure Build
include recipes-ti/includes/ti-paths.inc
DEPENDS += "openssl-native"

FILES_${PN} += "${base_libdir}"

DEPENDS_append = "${@ '' if d.getVar('TI_SECURE_DEV_PKG_K3') else ' ti-k3-secdev-native' }"
TI_SECURE_DEV_PKG = "${@ d.getVar('TI_SECURE_DEV_PKG_K3') or d.getVar('TI_K3_SECDEV_INSTALL_DIR') }"
export TI_SECURE_DEV_PKG

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
do_install_prepend_j7-hs-evm() {
        ( cd ${RTOS_DM_FW_DIR}; \
                mv ${DM_FIRMWARE} ${DM_FIRMWARE}.unsigned; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${DM_FIRMWARE}.unsigned ${DM_FIRMWARE}; \
        )
        (
          cd ${RTOS_IPC_FW_DIR}; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu2_0_release_strip.xer5f \
                        ipc_echo_test_mcu2_0_release_strip.xer5f.signed; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu2_1_release_strip.xer5f \
                        ipc_echo_test_mcu2_1_release_strip.xer5f.signed; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu3_0_release_strip.xer5f \
                        ipc_echo_test_mcu3_0_release_strip.xer5f.signed; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu3_1_release_strip.xer5f \
                        ipc_echo_test_mcu3_1_release_strip.xer5f.signed; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c66xdsp_1_release_strip.xe66 \
                        ipc_echo_test_c66xdsp_1_release_strip.xe66.signed; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c66xdsp_2_release_strip.xe66 \
                        ipc_echo_test_c66xdsp_2_release_strip.xe66.signed; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c7x_1_release_strip.xe71 \
                        ipc_echo_test_c7x_1_release_strip.xe71.signed; \
        )
        (
          cd ${RTOS_ETH_FW_DIR}; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh app_remoteswitchcfg_server_strip.xer5f \
                        app_remoteswitchcfg_server_strip.xer5f.signed;
        )
}

# J7 HS support
do_install_prepend_j7200-hs-evm() {
        ( cd ${RTOS_DM_FW_DIR}; \
                mv ${DM_FIRMWARE} ${DM_FIRMWARE}.unsigned; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${DM_FIRMWARE}.unsigned ${DM_FIRMWARE}; \
        )
        (
          cd ${RTOS_IPC_FW_DIR}; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu2_0_release_strip.xer5f \
                        ipc_echo_test_mcu2_0_release_strip.xer5f.signed; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu2_1_release_strip.xer5f \
                        ipc_echo_test_mcu2_1_release_strip.xer5f.signed; \
        )
        (
          cd ${RTOS_ETH_FW_DIR}; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh app_remoteswitchcfg_server_strip.xer5f \
                        app_remoteswitchcfg_server_strip.xer5f.signed;
        )
}

# J7 HS support
do_install_prepend_j721s2-hs-evm() {
        ( cd ${RTOS_DM_FW_DIR}; \
                mv ${DM_FIRMWARE} ${DM_FIRMWARE}.unsigned; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${DM_FIRMWARE}.unsigned ${DM_FIRMWARE}; \
        )
        (
          cd ${RTOS_IPC_FW_DIR}; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu2_0_release_strip.xer5f \
                ipc_echo_test_mcu2_0_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu2_1_release_strip.xer5f \
                ipc_echo_test_mcu2_1_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu3_0_release_strip.xer5f \
                ipc_echo_test_mcu3_0_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu3_1_release_strip.xer5f \
                ipc_echo_test_mcu3_1_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c7x_1_release_strip.xe71 \
                ipc_echo_test_c7x_1_release_strip.xe71.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c7x_2_release_strip.xe71 \
                ipc_echo_test_c7x_2_release_strip.xe71.signed; \
        )
}

# J784S4 support
do_install_prepend_j784s4-hs-evm() {
        ( cd ${RTOS_DM_FW_DIR}; \
                mv ${DM_FIRMWARE} ${DM_FIRMWARE}.unsigned; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${DM_FIRMWARE}.unsigned ${DM_FIRMWARE}; \
        )
        (
          cd ${RTOS_IPC_FW_DIR}; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu2_0_release_strip.xer5f \
                ipc_echo_test_mcu2_0_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu2_1_release_strip.xer5f \
                ipc_echo_test_mcu2_1_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu3_0_release_strip.xer5f \
                ipc_echo_test_mcu3_0_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu3_1_release_strip.xer5f \
                ipc_echo_test_mcu3_1_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu4_0_release_strip.xer5f \
                ipc_echo_test_mcu4_0_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_mcu4_1_release_strip.xer5f \
                ipc_echo_test_mcu4_1_release_strip.xer5f.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c7x_1_release_strip.xe71 \
                ipc_echo_test_c7x_1_release_strip.xe71.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c7x_2_release_strip.xe71 \
                ipc_echo_test_c7x_2_release_strip.xe71.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c7x_3_release_strip.xe71 \
                ipc_echo_test_c7x_3_release_strip.xe71.signed; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c7x_4_release_strip.xe71 \
                ipc_echo_test_c7x_4_release_strip.xe71.signed; \
        )
}

# Update the am64xx ipc binaries to be consistent with other platforms
do_install_prepend_am64xx() {
        ( cd ${RTOS_IPC_FW_DIR}; \
                mv am64-main-r5f0_0-fw ipc_echo_baremetal_test_mcu1_0_release_strip.xer5f; \
                mv am64-main-r5f0_1-fw ipc_echo_baremetal_test_mcu1_1_release_strip.xer5f; \
                mv am64-main-r5f1_0-fw ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f; \
                mv am64-main-r5f1_1-fw ipc_echo_baremetal_test_mcu2_1_release_strip.xer5f; \
                mv am64-mcu-m4f0_0-fw ipc_echo_baremetal_test_mcu3_0_release_strip.xer5f; \
        )
}

# AM62Q HS-SE support
do_install_prepend_am62xx() {
        ( cd ${RTOS_DM_FW_DIR}; \
                mv ${DM_FIRMWARE} ${DM_FIRMWARE}.unsigned; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${DM_FIRMWARE}.unsigned ${DM_FIRMWARE}; \
        )
        ( cd ${RTOS_IPC_FW_DIR}; \
                mv am62-mcu-m4f0_0-fw ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f \
                    ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f.signed; \
        )
}

do_install_prepend_am62axx-evm() {
        export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
        ( cd ${RTOS_DM_FW_DIR}; \
                mv ${DM_FIRMWARE} ${DM_FIRMWARE}.unsigned; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${DM_FIRMWARE}.unsigned ${DM_FIRMWARE}; \
        )
        ( cd ${RTOS_IPC_FW_DIR}; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh am62a-mcu-r5f0_0-fw \
                    am62a-mcu-r5f0_0-fw.signed; \
                ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ipc_echo_test_c7x_1_release_strip.xe71 \
                    ipc_echo_test_c7x_1_release_strip.xe71.signed; \
        )
}

#Install all R5 & DSP ipc echo test binaries in lib/firmware/pdk-ipc, with softlinks up a level
do_install() {

}

do_install_j7() {
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

do_install_append_j7-hs-evm() {
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_0_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_1_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c66xdsp_1_release_strip.xe66.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c66xdsp_2_release_strip.xe66.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71.signed ${LEGACY_IPC_FW_DIR}
    # ETH firmware
    install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f.signed ${LEGACY_ETH_FW_DIR}
}

do_install_j7200-evm() {
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

do_install_j7200-hs-evm() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    # Signed Firmwares
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    # DM Firmware
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR}
    # ETH firmware
    install -d ${LEGACY_ETH_FW_DIR}
    install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f ${LEGACY_ETH_FW_DIR}
    # ETH Signed firmware
    install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f.signed ${LEGACY_ETH_FW_DIR}
}

do_install_j721s2-evm() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_2_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    # DM Firmware
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR}
    # ETH firmware
    # install -d ${LEGACY_ETH_FW_DIR}
    # install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f ${LEGACY_ETH_FW_DIR}
}

do_install_j721s2-hs-evm() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_2_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    # Signed firmware
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_0_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_1_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_2_release_strip.xe71.signed ${LEGACY_IPC_FW_DIR}
    # DM Firmware
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR}
    # ETH firmware
    # install -d ${LEGACY_ETH_FW_DIR}
    # install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f ${LEGACY_ETH_FW_DIR}
}

do_install_j784s4-hs-evm() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu4_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu4_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_2_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_3_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_4_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    # Signed firmware
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_0_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_1_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu4_0_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu4_1_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_2_release_strip.xe71.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_3_release_strip.xe71.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_4_release_strip.xe71.signed ${LEGACY_IPC_FW_DIR}
    # DM Firmware
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR}
    # ETH firmware
    install -d ${LEGACY_ETH_FW_DIR}
    install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f ${LEGACY_ETH_FW_DIR}
}

do_install_j784s4-evm() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu3_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu4_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu4_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_2_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_3_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_4_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    # DM Firmware
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR}
    # ETH firmware
    install -d ${LEGACY_ETH_FW_DIR}
    install -m 0644 ${RTOS_ETH_FW_DIR}/app_remoteswitchcfg_server_strip.xer5f ${LEGACY_ETH_FW_DIR}
}

do_install_am65xx() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
}

do_install_am64xx() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu1_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu1_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu2_1_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu3_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
}

do_install_am62xx() {
    install -d ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f ${LEGACY_IPC_FW_DIR}
    # Signed Firmware
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f.signed ${LEGACY_IPC_FW_DIR}
    # DM Firmware
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR} 
}

do_install_am62axx() {
    install -d ${LEGACY_IPC_FW_DIR}
    # DM+IPC Firmware
    install -m 0644 ${RTOS_IPC_FW_DIR}/am62a-mcu-r5f0_0-fw ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/am62a-mcu-r5f0_0-fw.signed ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${LEGACY_DM_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71 ${LEGACY_IPC_FW_DIR}
    install -m 0644 ${RTOS_IPC_FW_DIR}/ipc_echo_test_c7x_1_release_strip.xe71.signed ${LEGACY_IPC_FW_DIR}
}

do_deploy() {
    install -d ${DEPLOYDIR}
}

do_deploy_am62xx() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${DEPLOYDIR}
}

do_deploy_am62axx() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${RTOS_DM_FW_DIR}/ipc_echo_testb_mcu1_0_release_strip.xer5f ${DEPLOYDIR}
}

# Set up names for the firmwares
ALTERNATIVE_${PN}_am65xx = "\
                    am65x-mcu-r5f0_0-fw \
                    am65x-mcu-r5f0_1-fw \
                    "

ALTERNATIVE_${PN}_am64xx = "\
                    am64-main-r5f0_0-fw \
                    am64-main-r5f0_1-fw \
                    am64-main-r5f1_0-fw \
                    am64-main-r5f1_1-fw \
                    am64-mcu-m4f0_0-fw \
                    "
ALTERNATIVE_${PN}_am62xx = "\
                    am62-mcu-m4f0_0-fw \
                    am62-main-r5f0_0-fw \
                    "

ALTERNATIVE_${PN}_am62xx-lp-evm = "\
                    am62-mcu-m4f0_0-fw \
                    am62-main-r5f0_0-fw \
                    "

ALTERNATIVE_${PN}_am62axx = "\
                    am62a-mcu-r5f0_0-fw \
                    am62a-c71_0-fw \
                    "

ALTERNATIVE_${PN}_j7-hs-evm = "\
                    j7-mcu-r5f0_0-fw \
                    j7-mcu-r5f0_1-fw \
                    j7-main-r5f0_0-fw \
                    j7-main-r5f0_1-fw \
                    j7-main-r5f1_0-fw \
                    j7-main-r5f1_1-fw \
                    j7-c66_0-fw \
                    j7-c66_1-fw \
                    j7-c71_0-fw\
                    j7-main-r5f0_0-fw-sec \
                    j7-main-r5f0_1-fw-sec \
                    j7-main-r5f1_0-fw-sec \
                    j7-main-r5f1_1-fw-sec \
                    j7-c66_0-fw-sec \
                    j7-c66_1-fw-sec \
                    j7-c71_0-fw-sec \
                    "

ALTERNATIVE_${PN}_j7 = "\
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

ALTERNATIVE_${PN}_j7200-evm = "\
                    j7200-mcu-r5f0_0-fw \
                    j7200-mcu-r5f0_1-fw \
                    j7200-main-r5f0_0-fw \
                    j7200-main-r5f0_1-fw \
                    "

ALTERNATIVE_${PN}_j7200-hs-evm = "\
                    j7200-mcu-r5f0_0-fw \
                    j7200-mcu-r5f0_1-fw \
                    j7200-main-r5f0_0-fw \
                    j7200-main-r5f0_1-fw \
                    j7200-main-r5f0_0-fw-sec \
                    j7200-main-r5f0_1-fw-sec \
                    "

ALTERNATIVE_${PN}_j721s2-evm = "\
                    j721s2-mcu-r5f0_0-fw \
                    j721s2-mcu-r5f0_1-fw \
                    j721s2-main-r5f0_0-fw \
                    j721s2-main-r5f0_1-fw \
                    j721s2-main-r5f1_0-fw \
                    j721s2-main-r5f1_1-fw \
                    j721s2-c71_0-fw \
                    j721s2-c71_1-fw \
                    "

ALTERNATIVE_${PN}_j721s2-hs-evm = "\
                    j721s2-mcu-r5f0_0-fw \
                    j721s2-mcu-r5f0_1-fw \
                    j721s2-main-r5f0_0-fw \
                    j721s2-main-r5f0_1-fw \
                    j721s2-main-r5f1_0-fw \
                    j721s2-main-r5f1_1-fw \
                    j721s2-c71_0-fw \
                    j721s2-c71_1-fw \
                    j721s2-main-r5f0_0-fw-sec \
                    j721s2-main-r5f0_1-fw-sec \
                    j721s2-main-r5f1_0-fw-sec \
                    j721s2-main-r5f1_1-fw-sec \
                    j721s2-c71_0-fw-sec \
                    j721s2-c71_1-fw-sec \
                    "

ALTERNATIVE_${PN}_j784s4-evm = "\
                    j784s4-mcu-r5f0_0-fw \
                    j784s4-mcu-r5f0_1-fw \
                    j784s4-main-r5f0_0-fw \
                    j784s4-main-r5f0_1-fw \
                    j784s4-main-r5f1_0-fw \
                    j784s4-main-r5f1_1-fw \
                    j784s4-main-r5f2_0-fw \
                    j784s4-main-r5f2_1-fw \
                    j784s4-c71_0-fw \
                    j784s4-c71_1-fw \
                    j784s4-c71_2-fw \
                    j784s4-c71_3-fw \
                    "

ALTERNATIVE_${PN}_j784s4-hs-evm = "\
                    j784s4-mcu-r5f0_0-fw \
                    j784s4-mcu-r5f0_1-fw \
                    j784s4-main-r5f0_0-fw \
                    j784s4-main-r5f0_1-fw \
                    j784s4-main-r5f1_0-fw \
                    j784s4-main-r5f1_1-fw \
                    j784s4-main-r5f2_0-fw \
                    j784s4-main-r5f2_1-fw \
                    j784s4-c71_0-fw \
                    j784s4-c71_1-fw \
                    j784s4-c71_2-fw \
                    j784s4-c71_3-fw \
                    "

ALTERNATIVE_${PN}_j784s4-hs-evm += "\
                    j784s4-main-r5f0_0-fw-sec \
                    j784s4-main-r5f0_1-fw-sec \
                    j784s4-main-r5f1_0-fw-sec \
                    j784s4-main-r5f1_1-fw-sec \
                    j784s4-main-r5f2_0-fw-sec \
                    j784s4-main-r5f2_1-fw-sec \
                    j784s4-c71_0-fw-sec \
                    j784s4-c71_1-fw-sec \
                    j784s4-c71_2-fw-sec \
                    j784s4-c71_3-fw-sec \
                    "

# Set up link names for the firmwares

TARGET_MCU_R5FSS0_0_am65xx = "am65x-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_am65xx = "am65x-mcu-r5f0_1-fw"

TARGET_MAIN_R5FSS0_0_am64xx = "am64-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1_am64xx = "am64-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0_am64xx = "am64-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1_am64xx = "am64-main-r5f1_1-fw"
TARGET_MCU_M4FSS0_0_am64xx = "am64-mcu-m4f0_0-fw"

TARGET_MAIN_R5FSS0_0_am62xx = "am62-main-r5f0_0-fw"
TARGET_MCU_M4FSS0_0_am62xx = "am62-mcu-m4f0_0-fw"

TARGET_MAIN_R5FSS0_0_am62xx-lp-evm = "am62-main-r5f0_0-fw"
TARGET_MCU_M4FSS0_0_am62xx-lp-evm = "am62-mcu-m4f0_0-fw"

TARGET_C7X_0_am62axx = "am62a-c71_0-fw"
TARGET_MCU_R5F0_0_am62axx = "am62a-mcu-r5f0_0-fw"

TARGET_MCU_R5FSS0_0_j7 = "j7-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_j7 = "j7-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0_j7 = "j7-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1_j7 = "j7-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0_j7 = "j7-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1_j7 = "j7-main-r5f1_1-fw"
TARGET_C66_0_j7 = "j7-c66_0-fw"
TARGET_C66_1_j7 = "j7-c66_1-fw"
TARGET_C7X_0_j7 = "j7-c71_0-fw"

TARGET_MAIN_R5FSS0_0_SIGNED_j7-hs-evm = "j7-main-r5f0_0-fw-sec"
TARGET_MAIN_R5FSS0_1_SIGNED_j7-hs-evm = "j7-main-r5f0_1-fw-sec"
TARGET_MAIN_R5FSS1_0_SIGNED_j7-hs-evm = "j7-main-r5f1_0-fw-sec"
TARGET_MAIN_R5FSS1_1_SIGNED_j7-hs-evm = "j7-main-r5f1_1-fw-sec"
TARGET_C66_0_SIGNED_j7-hs-evm = "j7-c66_0-fw-sec"
TARGET_C66_1_SIGNED_j7-hs-evm = "j7-c66_1-fw-sec"
TARGET_C7X_0_SIGNED_j7-hs-evm = "j7-c71_0-fw-sec"

TARGET_MCU_R5FSS0_0_j7200-evm = "j7200-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_j7200-evm = "j7200-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0_j7200-evm = "j7200-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1_j7200-evm = "j7200-main-r5f0_1-fw"

TARGET_MCU_R5FSS0_0_j7200-hs-evm = "j7200-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_j7200-hs-evm = "j7200-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0_j7200-hs-evm = "j7200-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1_j7200-hs-evm = "j7200-main-r5f0_1-fw"

TARGET_MAIN_R5FSS0_0_SIGNED_j7200-hs-evm = "j7200-main-r5f0_0-fw-sec"
TARGET_MAIN_R5FSS0_1_SIGNED_j7200-hs-evm = "j7200-main-r5f0_1-fw-sec"

TARGET_MCU_R5FSS0_0_j721s2-evm = "j721s2-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_j721s2-evm = "j721s2-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0_j721s2-evm = "j721s2-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1_j721s2-evm = "j721s2-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0_j721s2-evm = "j721s2-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1_j721s2-evm = "j721s2-main-r5f1_1-fw"
TARGET_C7X_0_j721s2-evm = "j721s2-c71_0-fw"
TARGET_C7X_1_j721s2-evm = "j721s2-c71_1-fw"

TARGET_MCU_R5FSS0_0_j721s2-hs-evm = "j721s2-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_j721s2-hs-evm = "j721s2-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0_j721s2-hs-evm = "j721s2-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1_j721s2-hs-evm = "j721s2-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0_j721s2-hs-evm = "j721s2-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1_j721s2-hs-evm = "j721s2-main-r5f1_1-fw"
TARGET_C7X_0_j721s2-hs-evm = "j721s2-c71_0-fw"
TARGET_C7X_1_j721s2-hs-evm = "j721s2-c71_1-fw"

TARGET_MAIN_R5FSS0_0_SIGNED_j721s2-hs-evm = "j721s2-main-r5f0_0-fw-sec"
TARGET_MAIN_R5FSS0_1_SIGNED_j721s2-hs-evm = "j721s2-main-r5f0_1-fw-sec"
TARGET_MAIN_R5FSS1_0_SIGNED_j721s2-hs-evm = "j721s2-main-r5f1_0-fw-sec"
TARGET_MAIN_R5FSS1_1_SIGNED_j721s2-hs-evm = "j721s2-main-r5f1_1-fw-sec"
TARGET_C7X_0_SIGNED_j721s2-hs-evm = "j721s2-c71_0-fw-sec"
TARGET_C7X_1_SIGNED_j721s2-hs-evm = "j721s2-c71_1-fw-sec"

TARGET_MCU_R5FSS0_0_j784s4-evm = "j784s4-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_j784s4-evm = "j784s4-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0_j784s4-evm = "j784s4-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1_j784s4-evm = "j784s4-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0_j784s4-evm = "j784s4-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1_j784s4-evm = "j784s4-main-r5f1_1-fw"
TARGET_MAIN_R5FSS2_0_j784s4-evm = "j784s4-main-r5f2_0-fw"
TARGET_MAIN_R5FSS2_1_j784s4-evm = "j784s4-main-r5f2_1-fw"
TARGET_C7X_0_j784s4-evm = "j784s4-c71_0-fw"
TARGET_C7X_1_j784s4-evm = "j784s4-c71_1-fw"
TARGET_C7X_2_j784s4-evm = "j784s4-c71_2-fw"
TARGET_C7X_3_j784s4-evm = "j784s4-c71_3-fw"

TARGET_MCU_R5FSS0_0_j784s4-hs-evm = "j784s4-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1_j784s4-hs-evm = "j784s4-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0_j784s4-hs-evm = "j784s4-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1_j784s4-hs-evm = "j784s4-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0_j784s4-hs-evm = "j784s4-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1_j784s4-hs-evm = "j784s4-main-r5f1_1-fw"
TARGET_MAIN_R5FSS2_0_j784s4-hs-evm = "j784s4-main-r5f2_0-fw"
TARGET_MAIN_R5FSS2_1_j784s4-hs-evm = "j784s4-main-r5f2_1-fw"
TARGET_C7X_0_j784s4-hs-evm = "j784s4-c71_0-fw"
TARGET_C7X_1_j784s4-hs-evm = "j784s4-c71_1-fw"
TARGET_C7X_2_j784s4-hs-evm = "j784s4-c71_2-fw"
TARGET_C7X_3_j784s4-hs-evm = "j784s4-c71_3-fw"

TARGET_MAIN_R5FSS0_0_SIGNED_j784s4-hs-evm = "j784s4-main-r5f0_0-fw-sec"
TARGET_MAIN_R5FSS0_1_SIGNED_j784s4-hs-evm = "j784s4-main-r5f0_1-fw-sec"
TARGET_MAIN_R5FSS1_0_SIGNED_j784s4-hs-evm = "j784s4-main-r5f1_0-fw-sec"
TARGET_MAIN_R5FSS1_1_SIGNED_j784s4-hs-evm = "j784s4-main-r5f1_1-fw-sec"
TARGET_MAIN_R5FSS2_0_SIGNED_j784s4-hs-evm = "j784s4-main-r5f2_0-fw-sec"
TARGET_MAIN_R5FSS2_1_SIGNED_j784s4-hs-evm = "j784s4-main-r5f2_1-fw-sec"
TARGET_C7X_0_SIGNED_j784s4-hs-evm = "j784s4-c71_0-fw-sec"
TARGET_C7X_1_SIGNED_j784s4-hs-evm = "j784s4-c71_1-fw-sec"
TARGET_C7X_2_SIGNED_j784s4-hs-evm = "j784s4-c71_2-fw-sec"
TARGET_C7X_3_SIGNED_j784s4-hs-evm = "j784s4-c71_3-fw-sec"

ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"

ALTERNATIVE_LINK_NAME[am64-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[am64-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0}"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1}"
ALTERNATIVE_LINK_NAME[am64-mcu-m4f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_M4FSS0_0}"

ALTERNATIVE_LINK_NAME[am62-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[am62-mcu-m4f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_M4FSS0_0}"

ALTERNATIVE_LINK_NAME[am62a-c71_0-fw] = "${base_libdir}/firmware/${TARGET_C7X_0}"
ALTERNATIVE_LINK_NAME[am62a-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5F0_0}"

ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1}"
ALTERNATIVE_LINK_NAME[j7-c66_0-fw] = "${base_libdir}/firmware/${TARGET_C66_0}"
ALTERNATIVE_LINK_NAME[j7-c66_1-fw] = "${base_libdir}/firmware/${TARGET_C66_1}"
ALTERNATIVE_LINK_NAME[j7-c71_0-fw] = "${base_libdir}/firmware/${TARGET_C7X_0}"

ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1_SIGNED}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1_SIGNED}"
ALTERNATIVE_LINK_NAME[j7-c66_0-fw-sec] = "${base_libdir}/firmware/${TARGET_C66_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j7-c66_1-fw-sec] = "${base_libdir}/firmware/${TARGET_C66_1_SIGNED}"
ALTERNATIVE_LINK_NAME[j7-c71_0-fw-sec] = "${base_libdir}/firmware/${TARGET_C7X_0_SIGNED}"

ALTERNATIVE_LINK_NAME[j7200-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7200-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"

ALTERNATIVE_LINK_NAME[j7200-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1_SIGNED}"

ALTERNATIVE_LINK_NAME[j721s2-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j721s2-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0}"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1}"
ALTERNATIVE_LINK_NAME[j721s2-c71_0-fw] = "${base_libdir}/firmware/${TARGET_C7X_0}"
ALTERNATIVE_LINK_NAME[j721s2-c71_1-fw] = "${base_libdir}/firmware/${TARGET_C7X_1}"

ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1_SIGNED}"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1_SIGNED}"
ALTERNATIVE_LINK_NAME[j721s2-c71_0-fw-sec] = "${base_libdir}/firmware/${TARGET_C7X_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j721s2-c71_1-fw-sec] = "${base_libdir}/firmware/${TARGET_C7X_1_SIGNED}"

ALTERNATIVE_LINK_NAME[j784s4-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j784s4-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS2_0}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS2_1}"
ALTERNATIVE_LINK_NAME[j784s4-c71_0-fw] = "${base_libdir}/firmware/${TARGET_C7X_0}"
ALTERNATIVE_LINK_NAME[j784s4-c71_1-fw] = "${base_libdir}/firmware/${TARGET_C7X_1}"
ALTERNATIVE_LINK_NAME[j784s4-c71_2-fw] = "${base_libdir}/firmware/${TARGET_C7X_2}"
ALTERNATIVE_LINK_NAME[j784s4-c71_3-fw] = "${base_libdir}/firmware/${TARGET_C7X_3}"

ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1_SIGNED}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1_SIGNED}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_0-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS2_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_1-fw-sec] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS2_1_SIGNED}"
ALTERNATIVE_LINK_NAME[j784s4-c71_0-fw-sec] = "${base_libdir}/firmware/${TARGET_C7X_0_SIGNED}"
ALTERNATIVE_LINK_NAME[j784s4-c71_1-fw-sec] = "${base_libdir}/firmware/${TARGET_C7X_1_SIGNED}"
ALTERNATIVE_LINK_NAME[j784s4-c71_2-fw-sec] = "${base_libdir}/firmware/${TARGET_C7X_2_SIGNED}"
ALTERNATIVE_LINK_NAME[j784s4-c71_3-fw-sec] = "${base_libdir}/firmware/${TARGET_C7X_3_SIGNED}"

# Create the firmware alternatives

ALTERNATIVE_TARGET[am65x-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[am65x-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"

ALTERNATIVE_TARGET[am64-main-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[am64-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu1_1_release_strip.xer5f"
ALTERNATIVE_TARGET[am64-main-r5f1_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f"
ALTERNATIVE_TARGET[am64-main-r5f1_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu2_1_release_strip.xer5f"
ALTERNATIVE_TARGET[am64-mcu-m4f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu3_0_release_strip.xer5f"

ALTERNATIVE_TARGET[am62-main-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_testb_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[am62-mcu-m4f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_baremetal_test_mcu2_0_release_strip.xer5f"

ALTERNATIVE_TARGET[am62a-c71_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_1_release_strip.xe71"
ALTERNATIVE_TARGET[am62a-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/am62a-mcu-r5f0_0-fw"

ALTERNATIVE_TARGET[j7-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_testb_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw] = "${base_libdir}/firmware/ethfw/app_remoteswitchcfg_server_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f1_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f1_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7-c66_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_1_release_strip.xe66"
ALTERNATIVE_TARGET[j7-c66_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_2_release_strip.xe66"
ALTERNATIVE_TARGET[j7-c71_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_1_release_strip.xe71"

ALTERNATIVE_TARGET[j7-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/ethfw/app_remoteswitchcfg_server_strip.xer5f.signed"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j7-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_0_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j7-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_1_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j7-c66_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_1_release_strip.xe66.signed"
ALTERNATIVE_TARGET[j7-c66_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_2_release_strip.xe66.signed"
ALTERNATIVE_TARGET[j7-c71_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_1_release_strip.xe71.signed"

ALTERNATIVE_TARGET[j7200-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_testb_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j7200-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw] = "${base_libdir}/firmware/ethfw/app_remoteswitchcfg_server_strip.xer5f"
ALTERNATIVE_TARGET[j7200-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f"

ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/ethfw/app_remoteswitchcfg_server_strip.xer5f.signed"
ALTERNATIVE_TARGET[j7200-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f.signed"

ALTERNATIVE_TARGET[j721s2-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_testb_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j721s2-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j721s2-main-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j721s2-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j721s2-main-r5f1_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j721s2-main-r5f1_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j721s2-c71_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_1_release_strip.xe71"
ALTERNATIVE_TARGET[j721s2-c71_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_2_release_strip.xe71"

ALTERNATIVE_TARGET[j721s2-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_0_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_0_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_1_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j721s2-c71_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_1_release_strip.xe71.signed"
ALTERNATIVE_TARGET[j721s2-c71_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_2_release_strip.xe71.signed"

ALTERNATIVE_TARGET[j784s4-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_testb_mcu1_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j784s4-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j784s4-main-r5f0_0-fw] = "${base_libdir}/firmware/ethfw/app_remoteswitchcfg_server_strip.xer5f"
ALTERNATIVE_TARGET[j784s4-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j784s4-main-r5f1_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j784s4-main-r5f1_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j784s4-main-r5f2_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu4_0_release_strip.xer5f"
ALTERNATIVE_TARGET[j784s4-main-r5f2_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu4_1_release_strip.xer5f"
ALTERNATIVE_TARGET[j784s4-c71_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_1_release_strip.xe71"
ALTERNATIVE_TARGET[j784s4-c71_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_2_release_strip.xe71"
ALTERNATIVE_TARGET[j784s4-c71_2-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_3_release_strip.xe71"
ALTERNATIVE_TARGET[j784s4-c71_3-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_4_release_strip.xe71"

ALTERNATIVE_TARGET[j784s4-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/ethfw/app_remoteswitchcfg_server_strip.xer5f.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_0_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_1_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f2_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu4_0_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f2_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu4_1_release_strip.xer5f.signed"
ALTERNATIVE_TARGET[j784s4-c71_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_1_release_strip.xe71.signed"
ALTERNATIVE_TARGET[j784s4-c71_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_2_release_strip.xe71.signed"
ALTERNATIVE_TARGET[j784s4-c71_2-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_3_release_strip.xe71.signed"
ALTERNATIVE_TARGET[j784s4-c71_3-fw-sec] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_4_release_strip.xe71.signed"

ALTERNATIVE_PRIORITY = "10"

# make sure that lib/firmware, and all its contents are part of the package
FILES_${PN} += "${base_libdir}/firmware"

# This is used to prevent the build system to_strip the executables
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
# This is used to prevent the build system to split the debug info in a separate file
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
# As it likely to be a different arch from the Yocto build, disable checking by adding "arch" to INSANE_SKIP
INSANE_SKIP_${PN} += "arch"

# we don't want to configure and build the source code
do_compile[noexec] = "1"
do_configure[noexec] = "1"

addtask deploy after do_install
