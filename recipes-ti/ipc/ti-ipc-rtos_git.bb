require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc
require ti-ipc.inc

TI_IPC_EXAMPLES_GIT_URI = "git://git.ti.com/ipc/ipc-examples.git"
TI_IPC_EXAMPLES_DEST_SUFFIX = "git/ipc-examples"
TI_IPC_EXAMPLES_GIT_PROTOCOL = "git"
TI_IPC_EXAMPLES_GIT_BRANCH = "master"
TI_IPC_EXAMPLES_NAME = "ipc-examples"

SRC_URI += "${TI_IPC_EXAMPLES_GIT_URI};\
destsuffix=${TI_IPC_EXAMPLES_DEST_SUFFIX};\
protocol=${TI_IPC_EXAMPLES_GIT_PROTOCOL};\
branch=${TI_IPC_EXAMPLES_GIT_BRANCH};\
name=${TI_IPC_EXAMPLES_NAME}"

TI_IPC_METADATA_GIT_URI = "git://git.ti.com/ipc/ipc-metadata.git"
TI_IPC_METADATA_DEST_SUFFIX = "git/ipc-metadata"
TI_IPC_METADATA_GIT_PROTOCOL = "git"
TI_IPC_METADATA_GIT_BRANCH = "master"
TI_IPC_METADATA_NAME = "ipc-metadata"

SRC_URI += "${TI_IPC_METADATA_GIT_URI};\
destsuffix=${TI_IPC_METADATA_DEST_SUFFIX};\
protocol=${TI_IPC_METADATA_GIT_PROTOCOL};\
branch=${TI_IPC_METADATA_GIT_BRANCH};\
name=${TI_IPC_METADATA_NAME}"

# Corresponds to tag: 3.43.00.01
SRCREV_ipc-examples = "54df963e83f2fd613f794dc3b1b72c8ed855e0ac"

# Corresponds to tag: 3.43.00.01
SRCREV_ipc-metadata = "8829b3cf2068c87140ac062849c33cd1a21de636"

S_ipc-examples = "${WORKDIR}/git/ipc-examples"
S_ipc-metadata = "${WORKDIR}/git/ipc-metadata"

PR = "${INC_PR}.0"

DEPENDS = "ti-xdctools ti-sysbios doxygen-native"
DEPENDS_append_keystone = " ti-cgt6x-native \
                            gcc-arm-none-eabi-native \
"
DEPENDS_append_omap-a15 = " ti-cgt6x-native \
                            ti-ccsv6-native \
                            gcc-arm-none-eabi-native \
"

PACKAGES =+ "${PN}-fw"
FILES_${PN}-fw = "${base_libdir}/firmware/*"
FILES_${PN}-dev += "${IPC_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN}-fw += "arch"
INSANE_SKIP_${PN}-dev += "arch"

ALLOW_EMPTY_${PN} = "1"

IPC_TARGETS = ""
IPC_TARGETS_omap-a15 = "\
    gnu.targets.arm.A15F="${GCC_ARM_NONE_TOOLCHAIN}" \
    ti.targets.elf.C66="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.elf.C66_big_endian="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.arm.elf.M4="${M4_TOOLCHAIN_INSTALL_DIR}" \
    ti.targets.arm.elf.M4F="${M4_TOOLCHAIN_INSTALL_DIR}" \
"

IPC_TARGETS_keystone = " \
    gnu.targets.arm.A15F="${GCC_ARM_NONE_TOOLCHAIN}" \
    ti.targets.elf.C66="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.elf.C66_big_endian="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
"

EXTRA_OEMAKE = "\
    PLATFORM=${PLATFORM} \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR}" \
    ${IPC_TARGETS} \
"

RELEASE_TYPE = "GA"
RELEASE_SUFFIX = ""

IPC_PACKAGE_DIR = "${S}/ipc-package"

do_compile() {
  oe_runmake -f ipc-bios.mak clean
  oe_runmake -f ipc-bios.mak release

  cd ${S_ipc-metadata}
  oe_runmake .all-files IPC_INSTALL_DIR="${S}" \
    BUILD_HOST_OS="linux" \
    RELEASE_TYPE="${RELEASE_TYPE}"

  cd ${S_ipc-examples}/src
  oe_runmake .examples \
    IPCTOOLS="${S_ipc-metadata}/src/etc"

  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    oe_runmake extract HOSTOS="bios" IPC_INSTALL_DIR="${S}"
    oe_runmake extract HOSTOS="linux" IPC_INSTALL_DIR="${S}"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake extract PLATFORM=${ALT_PLATFORM} HOSTOS="bios" IPC_INSTALL_DIR="${S}"
      oe_runmake extract PLATFORM=${ALT_PLATFORM} HOSTOS="linux" IPC_INSTALL_DIR="${S}"
    fi
  fi

  IPC_VERSION=`echo ${PV}${RELEASE_SUFFIX} | sed -e 's|\.|_|g'`
  install -d ${IPC_PACKAGE_DIR}
  # Copy docs and other meta files
  cp -pPrf  ${S_ipc-metadata}/exports/ipc_${IPC_VERSION}/* -d ${IPC_PACKAGE_DIR}

  # Copy example folders corresponding to the platforms
  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    install -d ${IPC_PACKAGE_DIR}/examples
    cp -pPf ${S_ipc-examples}/src/examples/*.* ${IPC_PACKAGE_DIR}/examples/
    cp -pPf ${S_ipc-examples}/src/examples/makefile ${IPC_PACKAGE_DIR}/examples/
    cp -pPrf ${S_ipc-examples}/src/examples/${PLATFORM}* ${IPC_PACKAGE_DIR}/examples/
    if [ ! -z ${ALT_PLATFORM} ]; then
      cp -pPrf ${S_ipc-examples}/src/examples/${ALT_PLATFORM}* ${IPC_PACKAGE_DIR}/examples/
    fi
    find ${IPC_PACKAGE_DIR}/examples/ -name "*zip" -type f | xargs -I {} rm {}
  fi
}

do_compile_append() {
  sourceipk_do_create_srcipk
}

do_install() {
  IPC_VERSION=`echo ${PV}${RELEASE_SUFFIX} | sed -e 's|\.|_|g'`
  # Copy docs and other meta files
  install -d ${D}${IPC_INSTALL_DIR_RECIPE}
  cp -pPrf  ${IPC_PACKAGE_DIR}/* -d ${D}${IPC_INSTALL_DIR_RECIPE}

  install -d ${D}${base_libdir}/firmware/ipc
  cp -pPrf ${S}/packages/ti/ipc/tests/bin/* ${D}${base_libdir}/firmware/ipc || true
}

ALTERNATIVE_PRIORITY = "5"

pkg_postinst_${PN}-fw_omap-a15 () {
  update-alternatives --install /lib/firmware/dra7-dsp1-fw.xe66 dra7-dsp1-fw.xe66 ipc/ti_platforms_evmDRA7XX_dsp1/test_omx_dsp1_vayu.xe66 ${ALTERNATIVE_PRIORITY}
  update-alternatives --install /lib/firmware/dra7-dsp2-fw.xe66 dra7-dsp2-fw.xe66 ipc/ti_platforms_evmDRA7XX_dsp2/test_omx_dsp2_vayu.xe66 ${ALTERNATIVE_PRIORITY}
  update-alternatives --install /lib/firmware/dra7-ipu1-fw.xem4 dra7-ipu1-fw.xem4 ipc/ti_platforms_evmDRA7XX_ipu1/test_omx_ipu1_vayu.xem4 ${ALTERNATIVE_PRIORITY}
  update-alternatives --install /lib/firmware/dra7-ipu2-fw.xem4 dra7-ipu2-fw.xem4 ipc/ti_platforms_evmDRA7XX_ipu2/test_omx_ipu2_vayu.xem4 ${ALTERNATIVE_PRIORITY}
}

pkg_postrm_${PN}-fw_omap-a15 () {
  update-alternatives --remove dra7-dsp1-fw.xe66 ipc/ti_platforms_evmDRA7XX_dsp1/test_omx_dsp1_vayu.xe66
  update-alternatives --remove dra7-dsp2-fw.xe66 ipc/ti_platforms_evmDRA7XX_dsp2/test_omx_dsp2_vayu.xe66
  update-alternatives --remove dra7-ipu1-fw.xem4 ipc/ti_platforms_evmDRA7XX_ipu1/test_omx_ipu1_vayu.xem4
  update-alternatives --remove dra7-ipu2-fw.xem4 ipc/ti_platforms_evmDRA7XX_ipu2/test_omx_ipu2_vayu.xem4
}
