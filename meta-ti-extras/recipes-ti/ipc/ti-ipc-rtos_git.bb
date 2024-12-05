require recipes-ti/ipc/ti-ipc.inc
require recipes-ti/ipc/ti-ipc-common.inc
require ti-ipc-rtos.inc

inherit deploy

DEPENDS = "ti-xdctools-native ti-sysbios doxygen-native zip-native"

PACKAGES =+ "${PN}-fw"
FILES:${PN}-fw = "${nonarch_base_libdir}/firmware/*"
FILES:${PN}-dev += "${IPC_INSTALL_DIR_RECIPE}"

INSANE_SKIP:${PN}-fw += "arch"
INSANE_SKIP:${PN}-dev += "arch"

ALLOW_EMPTY:${PN} = "1"

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
  for alt_platform in ${ALT_PLATFORM}; do
    oe_runmake .examples \
      IPCTOOLS="${S_ipc-metadata}/src/etc" \
      PLATFORM=${alt_platform}
  done

  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    oe_runmake extract HOSTOS="bios" IPC_INSTALL_DIR="${S}"
    oe_runmake extract HOSTOS="linux" IPC_INSTALL_DIR="${S}"

    for alt_platform in ${ALT_PLATFORM}; do
      oe_runmake extract PLATFORM=${alt_platform} HOSTOS="bios" IPC_INSTALL_DIR="${S}"
      oe_runmake extract PLATFORM=${alt_platform} HOSTOS="linux" IPC_INSTALL_DIR="${S}"
    done
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
    for alt_platform in ${ALT_PLATFORM}; do
      cp -pPrf ${S_ipc-examples}/src/examples/${alt_platform}* ${IPC_PACKAGE_DIR}/examples/
    done
    find ${IPC_PACKAGE_DIR}/examples/ -name "*zip" -type f | xargs -I {} rm {}
  fi
}

do_install() {
  CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
  IPC_VERSION=`echo ${PV}${RELEASE_SUFFIX} | sed -e 's|\.|_|g'`
  # Copy docs and other meta files
  install -d ${D}${IPC_INSTALL_DIR_RECIPE}
  cp ${CP_ARGS} ${IPC_PACKAGE_DIR}/* -d ${D}${IPC_INSTALL_DIR_RECIPE}

  install -d ${D}${nonarch_base_libdir}/firmware/ipc
  cp ${CP_ARGS} ${S}/packages/ti/ipc/tests/bin/* ${D}${nonarch_base_libdir}/firmware/ipc || true
}

KFDSPNUM = "0"

KFPLAT = ""

ALTERNATIVE_PRIORITY = "5"

pkg_postinst:${PN}-fw:omap-a15 () {
  update-alternatives --install /lib/firmware/dra7-dsp1-fw.xe66 dra7-dsp1-fw.xe66 ipc/ti_platforms_evmDRA7XX_dsp1/test_omx_dsp1_vayu.xe66 ${ALTERNATIVE_PRIORITY}
  update-alternatives --install /lib/firmware/dra7-dsp2-fw.xe66 dra7-dsp2-fw.xe66 ipc/ti_platforms_evmDRA7XX_dsp2/test_omx_dsp2_vayu.xe66 ${ALTERNATIVE_PRIORITY}
  update-alternatives --install /lib/firmware/dra7-ipu1-fw.xem4 dra7-ipu1-fw.xem4 ipc/ti_platforms_evmDRA7XX_ipu1/test_omx_ipu1_vayu.xem4 ${ALTERNATIVE_PRIORITY}
  update-alternatives --install /lib/firmware/dra7-ipu2-fw.xem4 dra7-ipu2-fw.xem4 ipc/ti_platforms_evmDRA7XX_ipu2/test_omx_ipu2_vayu.xem4 ${ALTERNATIVE_PRIORITY}
}

pkg_postrm:${PN}-fw:omap-a15 () {
  update-alternatives --remove dra7-dsp1-fw.xe66 ipc/ti_platforms_evmDRA7XX_dsp1/test_omx_dsp1_vayu.xe66
  update-alternatives --remove dra7-dsp2-fw.xe66 ipc/ti_platforms_evmDRA7XX_dsp2/test_omx_dsp2_vayu.xe66
  update-alternatives --remove dra7-ipu1-fw.xem4 ipc/ti_platforms_evmDRA7XX_ipu1/test_omx_ipu1_vayu.xem4
  update-alternatives --remove dra7-ipu2-fw.xem4 ipc/ti_platforms_evmDRA7XX_ipu2/test_omx_ipu2_vayu.xem4
}

pkg_postinst:${PN}-fw:omapl138 () {
  update-alternatives --install /lib/firmware/rproc-dsp-fw rproc-dsp-fw ipc/ti_platforms_evmOMAPL138_DSP/messageq_single.xe674 ${ALTERNATIVE_PRIORITY}
}

pkg_postrm:${PN}-fw:omapl138 () {
  update-alternatives --remove rproc-dsp-fw ipc/ti_platforms_evmOMAPL138_DSP/messageq_single.xe674
}

do_deploy() {
  install -d ${DEPLOYDIR}
}

do_deploy:append:omap-a15() {
  install -d ${DEPLOYDIR}/ipc
  install -m 0644 ${S}/packages/ti/ipc/tests/bin/ti_platforms_evmDRA7XX_ipu1/test_omx_ipu1_vayu.xem4 ${DEPLOYDIR}/ipc/dra7-ipu1-fw.xem4
}

addtask deploy after do_install
