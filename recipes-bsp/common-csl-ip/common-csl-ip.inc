DESCRIPTION = "Chip support library low level interface"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5857833e20836213677fac33f9aded21"

COMPATIBLE_MACHINE = "keystone|dra7xx|ti33x|ti43x|omapl1"

PV = "05.01.00.00"
INC_PR = "r0"

CSL_GIT_URI = "git://git.ti.com/keystone-rtos/common-csl-ip.git"
CSL_GIT_PROTOCOL ="git"
CSL_GIT_BRANCH = "master"
CSL_GIT_DESTSUFFIX = "git"

# Below commit ID corresponding to "DEV.CSL_PROCESSOR-SDK.05.01.00.00"
CSL_SRCREV = "8db3d673101a504fd7fb36d6c9c4981e1e15ff50"

BRANCH="${CSL_GIT_BRANCH}"
SRC_URI = "${CSL_GIT_URI};protocol=${CSL_GIT_PROTOCOL};branch=${BRANCH};destsuffix=${CSL_GIT_DESTSUFFIX}"
SRCREV = "${CSL_SRCREV}"

S = "${WORKDIR}/${CSL_GIT_DESTSUFFIX}"
