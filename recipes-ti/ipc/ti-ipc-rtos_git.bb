require ti-ipc.inc
require ti-ipc-common.inc
require ti-ipc-rtos.inc

DEPENDS = "ti-xdctools ti-sysbios doxygen-native zip-native"

PACKAGES =+ "${PN}-fw"
FILES_${PN}-fw = "${base_libdir}/firmware/*"
FILES_${PN}-dev += "${IPC_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN}-fw += "arch"
INSANE_SKIP_${PN}-dev += "arch"

ALLOW_EMPTY_${PN} = "1"

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
  if [ ! -z ${ALT_PLATFORM} ]; then
    oe_runmake .examples \
      IPCTOOLS="${S_ipc-metadata}/src/etc" \
      PLATFORM=${ALT_PLATFORM}
  fi

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
