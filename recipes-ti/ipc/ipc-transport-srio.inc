DESCRIPTION = "TI SRIO-Based IPC MessageQ Transport"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=27bb0deb260c60120a8d7b91da7c3b02"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-linux/ipc-transport.git;protocol=git;branch=${BRANCH}"
# Below commit ID corresponds to DEV.IPC-TRANSPORT.02.00.00.01
SRCREV = "88ea336c584ccec4b2c281cd7f75df9dff6d4edf"
PV = "02.00.00.01"

COMPATIBLE_MACHINE = "k2hk-evm"

S = "${WORKDIR}/git/linux/srio"

EXTRA_OEMAKE += "PDK_INSTALL_PATH=${STAGING_INCDIR} TRANS_SRIO_SRC_DIR=${S}"
