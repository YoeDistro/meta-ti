DESCRIPTION = "Chip support library low level interface"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5857833e20836213677fac33f9aded21"

COMPATIBLE_MACHINE = "keystone|dra7xx"

PV = "3.3.0.3C"
INC_PR = "r0"

CSL_GIT_URI = "git://git.ti.com/keystone-rtos/common-csl-ip.git"
CSL_GIT_PROTOCOL ="git"
CSL_GIT_BRANCH = "master"
CSL_GIT_DESTSUFFIX = "git"

# Below commit ID corresponds to "DEV.CSL_PROCESSOR-SDK.03.03.00.03C"
CSL_SRCREV = "ccd8dc29a5edce1a4d6409cb06b460bec6d3ecac"

BRANCH="${CSL_GIT_BRANCH}"
SRC_URI = "${CSL_GIT_URI};protocol=${CSL_GIT_PROTOCOL};branch=${BRANCH};destsuffix=${CSL_GIT_DESTSUFFIX}"
SRCREV = "${CSL_SRCREV}"

S = "${WORKDIR}/${CSL_GIT_DESTSUFFIX}"
