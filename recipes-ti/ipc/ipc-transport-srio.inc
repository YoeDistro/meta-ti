DESCRIPTION = "TI SRIO-Based IPC MessageQ Transport"
LICENSE = "BSD-3-Clause"

require ipc-transport.inc

INC_PR_append = ".0"

COMPATIBLE_MACHINE = "k2hk-evm"

S = "${WORKDIR}/git/linux/srio"

EXTRA_OEMAKE += "PDK_INSTALL_PATH=${STAGING_INCDIR} TRANS_SRIO_SRC_DIR=${S}"
