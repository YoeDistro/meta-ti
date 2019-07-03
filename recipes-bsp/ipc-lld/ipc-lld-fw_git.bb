SUMMARY = "R5 and DSP IPC Echo Test Firmware Binaries"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7eae093f2b09fd39307f30028a068b91"

inherit deploy
inherit update-alternatives

PV = "1.0-git${SRCPV}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PROTOCOL = "git"
BRANCH = "master"
SRCREV = "66764a5f063db606ea9db23380694bfd7d4aeafb"

SRC_URI = "git://git.ti.com/keystone-rtos/ipc-lld.git;protocol=${PROTOCOL};branch=${BRANCH}"

S = "${WORKDIR}/git"

R5_DSP_FW_DIR = "${S}/examples/echo_test/binaries"

# make sure that lib/firmware, and all its contents are part of the package
FILES_${PN} += "${base_libdir}/firmware"

# install all R5 & DSP ipc echo test binaries in lib/firmware/pdk-ipc, with softlinks up a level
do_install() {
  install -d ${D}${base_libdir}/firmware/pdk-ipc
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu1_0_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu1_0_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu1_0_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu1_0_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu1_1_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu1_1_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu1_1_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu1_1_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu2_0_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu2_0_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu2_0_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu2_0_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu2_1_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu2_1_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu2_1_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu2_1_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu3_0_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu3_0_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu3_0_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu3_0_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu3_1_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_mcu3_1_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu3_1_release.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu3_1_release.strip.xer5f ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_c66xdsp_1_release.xe66 ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_c66xdsp_1_release.strip.xe66 ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_c66xdsp_2_release.xe66 ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_c66xdsp_2_release.strip.xe66 ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_c7x_release.xe71 ${D}${base_libdir}/firmware/pdk-ipc/
  install -m 0644 ${R5_DSP_FW_DIR}/ipc_echo_test_c7x_release.strip.xe71 ${D}${base_libdir}/firmware/pdk-ipc/
}

TARGET_MCU_R5FSS0_0 = "j7-mcu-r5f0_0-fw"
TARGET_MCU_R5FSS0_1 = "j7-mcu-r5f0_1-fw"
TARGET_MAIN_R5FSS0_0 = "j7-main-r5f0_0-fw"
TARGET_MAIN_R5FSS0_1 = "j7-main-r5f0_1-fw"
TARGET_MAIN_R5FSS1_0 = "j7-main-r5f1_0-fw"
TARGET_MAIN_R5FSS1_1 = "j7-main-r5f1_1-fw"
TARGET_C66_0 = "j7-c66_0-fw"
TARGET_C66_1 = "j7-c66_1-fw"
TARGET_C7X = "j7-c71_0-fw"
ALTERNATIVE_${PN} = "\
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
ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MCU_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS0_1}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_0-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_0}"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_1-fw] = "${base_libdir}/firmware/${TARGET_MAIN_R5FSS1_1}"
ALTERNATIVE_LINK_NAME[j7-c66_0-fw] = "${base_libdir}/firmware/${TARGET_C66_0}"
ALTERNATIVE_LINK_NAME[j7-c66_1-fw] = "${base_libdir}/firmware/${TARGET_C66_1}"
ALTERNATIVE_LINK_NAME[j7-c71_0-fw] = "${base_libdir}/firmware/${TARGET_C7X}"
ALTERNATIVE_TARGET[j7-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_testb_mcu1_0_release.strip.xer5f"
ALTERNATIVE_TARGET[j7-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu1_1_release.strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_0_release.strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu2_1_release.strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f1_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_0_release.strip.xer5f"
ALTERNATIVE_TARGET[j7-main-r5f1_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_mcu3_1_release.strip.xer5f"
ALTERNATIVE_TARGET[j7-c66_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_1_release.strip.xe66"
ALTERNATIVE_TARGET[j7-c66_1-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c66xdsp_2_release.strip.xe66"
ALTERNATIVE_TARGET[j7-c71_0-fw] = "${base_libdir}/firmware/pdk-ipc/ipc_echo_test_c7x_release.strip.xe71"

ALTERNATIVE_PRIORITY = "10"

# copy the executables into the deploy directory
do_deploy () {
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu1_0_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu1_0_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu1_0_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu1_0_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu1_1_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu1_1_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu1_1_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu1_1_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu2_0_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu2_0_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu2_0_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu2_0_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu2_1_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu2_1_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu2_1_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu2_1_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu3_0_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu3_0_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu3_0_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu3_0_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu3_1_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_mcu3_1_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu3_1_release.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_testb_mcu3_1_release.strip.xer5f ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_c66xdsp_1_release.xe66 ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_c66xdsp_1_release.strip.xe66 ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_c66xdsp_2_release.xe66 ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_c66xdsp_2_release.strip.xe66 ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_c7x_release.xe71 ${DEPLOYDIR}/
  install ${R5_DSP_FW_DIR}/ipc_echo_test_c7x_release.strip.xe71 ${DEPLOYDIR}/
}
addtask deploy after do_install before do_build

# This is used to prevent the build system to strip the executables
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
# This is used to prevent the build system to split the debug info in a separate file
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
# As it likely to be a different arch from the Yocto build, disable checking by adding "arch" to INSANE_SKIP
INSANE_SKIP_${PN} += "arch"

# we don't want to configure and build the source code
do_compile[noexec] = "1"
do_configure[noexec] = "1"
