DESCRIPTION = "TI Inter Process Communication (IPC) examples with Host running linux"
require ti-ipc-examples.inc

DEPENDS = "ti-ipc ti-xdctools-native ti-sysbios ti-ipc-rtos zip-native"

do_compile:append() {

  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    oe_runmake extract HOSTOS="bios" IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"

    for alt_platform in ${ALT_PLATFORM}; do
      oe_runmake extract PLATFORM="${alt_platform}" HOSTOS="bios" \
         IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"
    done
    oe_runmake -C examples all HOSTOS="bios" \
      IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"
    for alt_platform in ${ALT_PLATFORM}; do
      oe_runmake -C examples all HOSTOS="bios" \
        IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" PLATFORM="${alt_platform}"
    done
  fi
}

do_install:append() {
  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    # Install directory for bios examples
    install -d ${D}/ipc_${IPC_VERSION}/examples/bios
    oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
      HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios"
    oe_runmake -C examples install_rov IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
      HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios"

    for alt_platform in ${ALT_PLATFORM}; do
      oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/${alt_platform}/bios" \
        PLATFORM="${alt_platform}"
      oe_runmake -C examples install_rov IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/${alt_platform}/bios" \
        PLATFORM="${alt_platform}"
    done
  fi
}

FILES:${PN} += "ipc_*"
