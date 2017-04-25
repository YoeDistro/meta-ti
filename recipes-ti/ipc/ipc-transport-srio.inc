DESCRIPTION = "TI SRIO-Based IPC MessageQ Transport"
LICENSE = "BSD-3-Clause"

require ipc-transport.inc

INC_PR_append = ".1"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=27bb0deb260c60120a8d7b91da7c3b02"

COMPATIBLE_MACHINE = "k2hk"

S = "${WORKDIR}/git/linux/srio"

EXTRA_OEMAKE += "PDK_INSTALL_PATH=${STAGING_INCDIR} TRANS_SRIO_SRC_DIR=${S}"
