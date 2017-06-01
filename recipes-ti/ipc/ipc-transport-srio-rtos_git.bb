inherit ti-pdk
require ipc-transport.inc

PR = "${INC_PR}.2"

LIC_FILES_CHKSUM = "file://TransportSrio.xdc;beginline=1;endline=35;md5=4d466471e4b933a0cc353a46bb546fe7"

COMPATIBLE_MACHINE = "k2hk"

DEPENDS_append = " qmss-lld-rtos \
                   cppi-lld-rtos \
                   srio-lld-rtos \
                   ti-ipc-rtos \
                   ipc-transport-common-rtos \
"

IPC_TRANSPORT_SRIO_DESTSUFFIX = "git/c66/srio"

S = "${WORKDIR}/${IPC_TRANSPORT_SRIO_DESTSUFFIX}"

export IPC_INSTALL_PATH = "${IPC_INSTALL_DIR}"
XDCPATH_append = ";${IPC_INSTALL_DIR}/packages"
