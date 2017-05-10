DESCRIPTION = "TI Inter Process Communication (IPC) examples"
HOMEPAGE="http://processors.wiki.ti.com/index.php/Category:IPC"
require ti-ipc-common.inc
require ti-ipc-rtos.inc

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S_ipc-examples}/src/makefile;beginline=1;endline=30;md5=fb83580b16bce88e8ed568a6005c8f02"

DEPENDS = "ti-ipc ti-xdctools ti-sysbios ti-ipc-rtos zip-native"

INSANE_SKIP_${PN} += "arch"

ALLOW_EMPTY_${PN} = "1"

PACKAGES =+ "${PN}-linux"

INSANE_SKIP_${PN}-linux += "arch"
ALLOW_EMPTY_${PN}-linux  = "1"

IPC_INSTALL_DIR="${STAGING_DIR_TARGET}/usr/share/ti/ti-ipc-tree"

do_compile() {

  cd ${S_ipc-examples}/src
  oe_runmake .examples \
    IPCTOOLS="${S_ipc-metadata}/src/etc"

  if [ ! -z ${ALT_PLATFORM} ]; then
    oe_runmake .examples "PLATFORM=${ALT_PLATFORM}" \
      IPCTOOLS="${S_ipc-metadata}/src/etc"
	echo test
  fi
  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    oe_runmake extract HOSTOS="bios" IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"
    oe_runmake extract HOSTOS="linux" IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake extract PLATFORM="${ALT_PLATFORM}" HOSTOS="bios" \
         IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"
      oe_runmake extract PLATFORM="${ALT_PLATFORM}" HOSTOS="linux" \ 
        IPC_INSTALL_DIR="${IPC_INSTALL_DIR}"
    fi
    oe_runmake -C examples all HOSTOS="bios" \
      IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" 
    oe_runmake -C examples all HOSTOS="linux" \
      LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
      IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" 
    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake -C examples all HOSTOS="bios" \
        IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" PLATFORM="${ALT_PLATFORM}" 
      oe_runmake -C examples all HOSTOS="linux" \
        LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
        IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        PLATFORM="${ALT_PLATFORM}" 
      echo testing
    fi
  fi
}

do_install() {
  cd ${S_ipc-examples}/src
  IPC_VERSION=`echo ${PV}${RELEASE_SUFFIX} | sed -e 's|\.|_|g'`

  if [  "${PLATFORM}" != "UNKNOWN" ]; then
    # Install directory for bios examples
    install -d ${D}/ipc_${IPC_VERSION}/examples/bios
    # Install directory for linux examples
    install -d ${D}${bindir}/ipc/examples
    oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
      HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios"
    oe_runmake -C examples install_rov IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
      HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios"

    oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
      LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
      HOSTOS="linux" EXEC_DIR="${D}/${bindir}/ipc/examples"

    if [ ! -z ${ALT_PLATFORM} ]; then
      oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios" \
        PLATFORM="${ALT_PLATFORM}"
      oe_runmake -C examples install_rov IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        HOSTOS="bios" EXEC_DIR="${D}/ipc_${IPC_VERSION}/examples/bios" \
        PLATFORM="${ALT_PLATFORM}"

      oe_runmake -C examples install IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        LINUX_SYSROOT_DIR="${STAGING_INCDIR}" \
        HOSTOS="linux" EXEC_DIR="${D}/${bindir}/ipc/examples" \
        PLATFORM="${ALT_PLATFORM}"
    fi
  fi
}

FILES_${PN} += "ipc_*"
FILES_${PN}-linux += "${bindir}/*"
