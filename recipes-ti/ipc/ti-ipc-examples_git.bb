DESCRIPTION = "TI Inter Process Communication (IPC) examples with Host running linux"
require ti-ipc-examples.inc

DEPENDS = "ti-ipc ti-xdctools ti-sysbios ti-ipc-rtos zip-native"

do_compile_append() {

  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    oe_runmake extract HOSTOS="bios" IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake extract PLATFORM="${ALT_PLATFORM}" HOSTOS="bios" \
         IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"
    fi
    oe_runmake -C examples all HOSTOS="bios" \
      IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"
    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake -C examples all HOSTOS="bios" \
        IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" PLATFORM="${ALT_PLATFORM}"
    fi
  fi
}

do_install_append() {
  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    # Install directory for bios examples
    install -d ${D}/ipc_${IPC_VERSION}/examples/bios
    oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
      HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios"
    oe_runmake -C examples install_rov IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
      HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios" \
        PLATFORM="${ALT_PLATFORM}"
      oe_runmake -C examples install_rov IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios" \
        PLATFORM="${ALT_PLATFORM}"
    fi
  fi
}

FILES_${PN} += "ipc_*"
