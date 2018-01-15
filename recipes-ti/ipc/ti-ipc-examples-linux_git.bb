DESCRIPTION = "TI Inter Process Communication (IPC) examples with Host running bios"
require ti-ipc-examples.inc

DEPENDS = "ti-ipc ti-xdctools-native ti-sysbios ti-ipc-rtos zip-native"

do_compile_append() {

  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    oe_runmake extract HOSTOS="linux" IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake extract PLATFORM="${ALT_PLATFORM}" HOSTOS="linux" \
         IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"
    fi
    oe_runmake -C examples all HOSTOS="linux" \
      LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
      IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake -C examples all HOSTOS="linux" \
        LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
        IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        PLATFORM="${ALT_PLATFORM}"
    fi
  fi
}

do_install_append() {
  cd ${S_ipc-examples}/src

  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    # Install directory for linux examples
    install -d ${D}${bindir}/ipc/examples
    oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
      LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
      HOSTOS="linux" EXEC_DIR="${D}/${bindir}/ipc/examples"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
        HOSTOS="linux" EXEC_DIR="${D}/${bindir}/ipc/examples" \
        PLATFORM="${ALT_PLATFORM}"
    fi
  fi
}

FILES_${PN} += "${bindir}/*"
