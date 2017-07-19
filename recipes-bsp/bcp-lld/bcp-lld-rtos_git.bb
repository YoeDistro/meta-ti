SUMMARY = "TI Bit Coprocessor (BCP) low level driver"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://bcp.h;beginline=1;endline=40;md5=4f6e6128dd075a89548c0287a39b8896"

COMPATIBLE_MACHINE = "k2hk-evm|k2l-evm|c667x-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

BCP_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/bcp-lld.git"
BCP_LLD_GIT_PROTOCOL = "git"
BCP_LLD_GIT_BRANCH = "master"
BCP_LLD_GIT_DESTSUFFIX = "git/ti/drv/bcp"

# Below commit ID corresponds to "DEV.BCP_LLD.02.01.00.06"
BCP_LLD_SRCREV = "44e2ffcb6fd236c41f7ea8f9a1684833f5361daa"

BRANCH = "${BCP_LLD_GIT_BRANCH}"
SRC_URI = "${BCP_LLD_GIT_URI};destsuffix=${BCP_LLD_GIT_DESTSUFFIX};protocol=${BCP_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${BCP_LLD_SRCREV}"
PV = "02.01.00.06"
PR = "r0"

S = "${WORKDIR}/${BCP_LLD_GIT_DESTSUFFIX}"

DEPENDS_append = " qmss-lld-rtos \
                   cppi-lld-rtos \
"

# HTML doc link params
PDK_COMP_LINK_TEXT = "BCP LLD"
