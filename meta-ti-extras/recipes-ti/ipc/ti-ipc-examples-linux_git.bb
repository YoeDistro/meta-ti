DESCRIPTION = "TI Inter Processor Communication (IPC) MessageQ Application for Linux Host"
SUMMARY = "TI IPC MessageQ Application for Linux Host"

require recipes-ti/ipc/ti-ipc-common.inc
require ti-ipc-rtos.inc

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S_ipc-examples}/src/makefile;beginline=1;endline=30;md5=a52324bd5033bb49ea07bade1244ac9a"

IPC_INSTALL_DIR = "${STAGING_DIR_TARGET}/usr/share/ti/ti-ipc-tree"
INHIBIT_PACKAGE_STRIP = "1"

DEPENDS = "ti-ipc ti-ipc-rtos ti-xdctools-native zip-native"

EX02_PATH = "examples/DRA7XX_linux_elf/ex02_messageq/host"

do_compile() {
  cd ${S_ipc-examples}/src
  oe_runmake .examples \
    IPCTOOLS="${S_ipc-metadata}/src/etc"

  for alt_platform in ${ALT_PLATFORM}; do
    oe_runmake .examples "PLATFORM=${alt_platform}" \
      IPCTOOLS="${S_ipc-metadata}/src/etc"
  done

  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    oe_runmake extract HOSTOS="linux" IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake extract PLATFORM="${ALT_PLATFORM}" HOSTOS="linux" \
         IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"
    fi
    oe_runmake -C ${EX02_PATH} all HOSTOS="linux" \
      LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
      IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake -C ${EX02_PATH} all HOSTOS="linux" \
        LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
        IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        PLATFORM="${ALT_PLATFORM}"
    fi
  fi
}

do_install() {
  cd ${S_ipc-examples}/src

  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    # Install directory for linux examples
    install -d ${D}${bindir}/ipc/examples/ex02_messageq
    oe_runmake -C ${EX02_PATH} install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
      LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
      HOSTOS="linux" EXEC_DIR="${D}/${bindir}/ipc/examples/ex02_messageq"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake -C ${EX02_PATH} install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
        HOSTOS="linux" EXEC_DIR="${D}/${bindir}/ipc/examples/ex02_messageq" \
        PLATFORM="${ALT_PLATFORM}"
    fi
  fi
}

FILES:${PN} += "${bindir}/*"
