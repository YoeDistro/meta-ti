DESCRIPTION = "TI SRIO-Based IPC MessageQ Transport"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=27bb0deb260c60120a8d7b91da7c3b02"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-linux/ipc-transport.git;protocol=git;branch=${BRANCH}"
# Below commit ID corresponds to DEV.IPC-TRANSPORT.01.00.00.00
SRCREV = "b3077467126c84b5d583dc29e13ab221064b687b"
PV = "01.00.00.00"

COMPATIBLE_MACHINE = "keystone"

S = "${WORKDIR}/git/linux/srio"

EXTRA_OEMAKE += "PDK_INSTALL_PATH=${STAGING_INCDIR} TRANS_SRIO_SRC_DIR=${S}"
