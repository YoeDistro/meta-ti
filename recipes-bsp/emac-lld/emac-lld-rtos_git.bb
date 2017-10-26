SUMMARY = "TI RTOS low level driver for Ethernet MAC (EMAC) peripheral"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://emacver.h;beginline=1;endline=47;md5=f66bb3695972ca2a85d990a390f45d88"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g|omapl1|c665x-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

EMAC_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/emac-lld.git"
EMAC_LLD_GIT_PROTOCOL = "git"
EMAC_LLD_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.EMAC_LLD.01.00.03.07"
EMAC_LLD_SRCREV = "70bb4d2ed677d428f055c2178e95766511e14383"

BRANCH ="${EMAC_LLD_GIT_BRANCH}"
SRC_URI = "${EMAC_LLD_GIT_URI};protocol=${EMAC_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${EMAC_LLD_SRCREV}"
PV = "01.00.03.07"
PR = "r0"

DEPENDS_append = " board-rtos osal-rtos"
DEPENDS_append_ti33x = " starterware-rtos"
DEPENDS_append_ti43x = " starterware-rtos"
DEPENDS_append_k2g  = " cppi-lld-rtos qmss-lld-rtos"

# HTML doc link params
PDK_COMP_LINK_TEXT = "EMAC LLD"
