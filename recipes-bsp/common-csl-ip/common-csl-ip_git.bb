DESCRIPTION = "Chip support library low level interface"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5857833e20836213677fac33f9aded21"

COMPATIBLE_MACHINE = "keystone"
ALLOW_EMPTY_${PN} = "1"

PR = "r1"
BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-rtos/common-csl-ip.git;protocol=git;branch=${BRANCH}"
# commit ID corresponds to DEV.CSL_KEYSTONE2.02.01.00.05A
SRCREV = "80d18dda4a76ce1a22cf2b5156f111390d75bea4"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}${includedir}/ti/csl
    find . -name "*.h" -type f | xargs -I {} cp --parents {} ${D}${includedir}/ti/csl
    find ./src/ip/serdes_sb/V0 -name "*.c" -type f | xargs -I {} cp --parents {} ${D}${includedir}/ti/csl
}
