DESCRIPTION = "TI QMSS-Based IPC MessageQ Transport"
LICENSE = "BSD-3-Clause"

require ipc-transport.inc

INC_PR_append = ".0"

COMPATIBLE_MACHINE = "keystone"

S = "${WORKDIR}/git/linux/qmss"

EXTRA_OEMAKE += "PDK_INSTALL_PATH=${STAGING_INCDIR} TRANS_QMSS_SRC_DIR=${S}"
