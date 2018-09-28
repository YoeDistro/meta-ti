DESCRIPTION = "Chip support library low level interface"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5857833e20836213677fac33f9aded21"

COMPATIBLE_MACHINE = "keystone|dra7xx|ti33x|ti43x|omapl1"

PV = "03.03.00.12"
INC_PR = "r0"
PE = "1"

CSL_GIT_URI = "git://git.ti.com/keystone-rtos/common-csl-ip.git"
CSL_GIT_PROTOCOL ="git"
CSL_GIT_BRANCH = "master"
CSL_GIT_DESTSUFFIX = "git"

# Below commit ID corresponding to "DEV.CSL_PROCESSOR-SDK.03.03.00.12"
CSL_SRCREV = "0c3b2c8a054d2af27fe1683e9aa579d43049a3bf"

BRANCH="${CSL_GIT_BRANCH}"
SRC_URI = "${CSL_GIT_URI};protocol=${CSL_GIT_PROTOCOL};branch=${BRANCH};destsuffix=${CSL_GIT_DESTSUFFIX}"
SRCREV = "${CSL_SRCREV}"

S = "${WORKDIR}/${CSL_GIT_DESTSUFFIX}"
