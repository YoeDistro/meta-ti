HOMEPAGE = "http://processors.wiki.ti.com/index.php/Category:IPC"
require recipes-ti/ipc/ti-ipc-common.inc
require ti-ipc-rtos.inc

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S_ipc-examples}/src/makefile;beginline=1;endline=30;md5=a52324bd5033bb49ea07bade1244ac9a"

INSANE_SKIP:${PN} += "arch"

ALLOW_EMPTY:${PN} = "1"

IPC_INSTALL_DIR = "${STAGING_DIR_TARGET}/usr/share/ti/ti-ipc-tree"

do_compile() {

  cd ${S_ipc-examples}/src
  oe_runmake .examples \
    IPCTOOLS="${S_ipc-metadata}/src/etc"

  for alt_platform in ${ALT_PLATFORM}; do
    oe_runmake .examples "PLATFORM=${alt_platform}" \
      IPCTOOLS="${S_ipc-metadata}/src/etc"
  done
}

do_install() {
  cd ${S_ipc-examples}/src
  IPC_VERSION=`echo ${PV}${RELEASE_SUFFIX} | sed -e 's|\.|_|g'`
}

INHIBIT_PACKAGE_STRIP = "1"
