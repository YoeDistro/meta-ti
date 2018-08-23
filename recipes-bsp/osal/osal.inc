LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://osal.h;beginline=1;endline=31;md5=6fbde6922f8bca1e6cd371d3c2faa4f9"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

OSAL_GIT_URI = "git://git.ti.com/keystone-rtos/osal.git"
OSAL_GIT_PROTOCOL = "git"
OSAL_GIT_BRANCH = "master"
OSAL_GIT_DESTSUFFIX = "git/ti/osal"

# Below commit ID corresponding to "DEV.OSAL.01.00.00.12"
OSAL_SRCREV = "d93d8833e5e0bbd3e76f8618a4f717e3539a8d11"

BRANCH = "${OSAL_GIT_BRANCH}"
SRC_URI = "${OSAL_GIT_URI};destsuffix=${OSAL_GIT_DESTSUFFIX};protocol=${OSAL_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${OSAL_SRCREV}"
PV = "01.00.00.12"
INC_PR = "r0"
