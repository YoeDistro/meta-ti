HOMEPAGE = "http://processors.wiki.ti.com/index.php/Category:IPC"
require ti-ipc-common.inc
require ti-ipc-rtos.inc

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S_ipc-examples}/src/makefile;beginline=1;endline=30;md5=fb83580b16bce88e8ed568a6005c8f02"

INSANE_SKIP_${PN} += "arch"

ALLOW_EMPTY_${PN} = "1"

IPC_INSTALL_DIR = "${STAGING_DIR_TARGET}/usr/share/ti/ti-ipc-tree"

do_compile() {

  cd ${S_ipc-examples}/src
  oe_runmake .examples \
    IPCTOOLS="${S_ipc-metadata}/src/etc"

  if [ ! -z ${ALT_PLATFORM} ]; then
    oe_runmake .examples "PLATFORM=${ALT_PLATFORM}" \
      IPCTOOLS="${S_ipc-metadata}/src/etc"
  fi
}

do_install() {
  cd ${S_ipc-examples}/src
  IPC_VERSION=`echo ${PV}${RELEASE_SUFFIX} | sed -e 's|\.|_|g'`
}

INHIBIT_PACKAGE_STRIP = "1"
