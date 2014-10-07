DESCRIPTION = "TI CSL"
LICENSE = "TI BSD"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5857833e20836213677fac33f9aded21"

COMPATIBLE_MACHINE = "keystone"
ALLOW_EMPTY_${PN} = "1"

PR = "r0"
BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-rtos/common-csl-ip.git;protocol=git;branch=${BRANCH}"
# commit ID corresponds to DEV.CSL_KEYSTONE2.02.00.00.17
SRCREV = "f6f90144c14e1ee783c4b893b52e54830be8166e" 

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}${includedir}/ti/csl
    find . -name "*.h" -type f | xargs -I {} cp --parents {} ${D}${includedir}/ti/csl 
    find ./src/ip/serdes_sb/V0 -name "*.c" -type f | xargs -I {} cp --parents {} ${D}${includedir}/ti/csl
}

