require recipes-ti/includes/ti-paths.inc
require ipc-transport.inc

PR = "${INC_PR}.1"

LIC_FILES_CHKSUM = "file://common_src/bench_common.h;beginline=1;endline=31;md5=a6ddfb26d2097041d513e3881f40e4e8"

COMPATIBLE_MACHINE = "k2hk|k2l|k2e"

IPC_TRANSPORT_COMMON_DESTSUFFIX = "git/c66/example"

S = "${WORKDIR}/${IPC_TRANSPORT_COMMON_DESTSUFFIX}"

do_compile() {
    :
}

do_install () {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/transport/ipc/c66/example
    cp -r ${S} ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/transport/ipc/c66
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages/ti/transport/ipc/c66/example/*"
