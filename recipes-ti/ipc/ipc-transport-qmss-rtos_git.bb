inherit ti-pdk
require ipc-transport.inc

PR = "${INC_PR}.4"

LIC_FILES_CHKSUM = "file://TransportQmss.xdc;beginline=1;endline=35;md5=ad783760d74a4cc5b8d4ad3d8a1f28a2"

COMPATIBLE_MACHINE = "k2hk|k2l|k2e"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS_append = " qmss-lld-rtos \
                   cppi-lld-rtos \
                   ti-ipc-rtos \
                   ipc-transport-common-rtos \
"

IPC_TRANSPORT_QMSS_DESTSUFFIX = "git/c66/qmss"

S = "${WORKDIR}/${IPC_TRANSPORT_QMSS_DESTSUFFIX}"

export IPC_INSTALL_PATH = "${IPC_INSTALL_DIR}"
XDCPATH_append = ";${IPC_INSTALL_DIR}/packages"
