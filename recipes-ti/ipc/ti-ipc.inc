DESCRIPTION = "TI Inter Process Communication (IPC) Mechanisms (for Uni- and Multi- Processor Configurations)"
HOMEPAGE="http://processors.wiki.ti.com/index.php/Category:IPC"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/ipc-linux.mak;beginline=1;endline=30;md5=7b327f9b710fd7c95e545b91cec79255"

BRANCH = "3.41"

SRC_URI = "git://git.ti.com/ipc/ipcdev.git;protocol=git;branch=${BRANCH}"

#Corresponds to 3.41.00.08
SRCREV = "7370abce2e3f2a09fd4b057fbbf9e7d3d029c0df"

S = "${WORKDIR}/git"
PV = "3.41.00.08"
INC_PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PLATFORM = "UNKNOWN"
PLATFORM_omap5-evm = "OMAP54XX"
PLATFORM_dra7xx = "DRA7XX"
PLATFORM_k2hk-evm = "TCI6638"
PLATFORM_k2l-evm = "TCI6630"
PLATFORM_k2e-evm = "66AK2E"
PLATFORM_k2g-evm = "66AK2G"
