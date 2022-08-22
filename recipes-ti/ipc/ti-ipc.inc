DESCRIPTION = "TI Inter Process Communication (IPC) Mechanisms (for Uni- and Multi- Processor Configurations)"
HOMEPAGE="http://processors.wiki.ti.com/index.php/Category:IPC"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/ipc-linux.mak;beginline=1;endline=30;md5=7b327f9b710fd7c95e545b91cec79255"

TI_IPC_GIT_URI = "git://git.ti.com/git/ipc/ipcdev.git"
TI_IPC_GIT_PROTOCOL = "https"
TI_IPC_GIT_BRANCH = "master"

#Corresponds to 3.51.00.00A
TI_IPC_SRCREV = "7a6e343a208d2849139b7272a67abc01db806e00"

BRANCH = "${TI_IPC_GIT_BRANCH}"
SRC_URI = "${TI_IPC_GIT_URI};protocol=${TI_IPC_GIT_PROTOCOL};branch=${BRANCH};name=ipcdev"
SRCREV = "${TI_IPC_SRCREV}"

S = "${WORKDIR}/git"
