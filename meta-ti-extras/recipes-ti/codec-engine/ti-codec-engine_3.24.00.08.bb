SUMMARY = "Codec Engine for TI ARM/DSP processors"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ce"
SECTION = "devel"
LICENSE = "BSD-3-Clause"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc

PV = "3_24_00_08"
PR = "r0"

LIC_FILES_CHKSUM = "file://codec_engine_${PV}_Manifest.html;md5=de7d9c2594a6f3868e42a33b6a748ce7"

SRC_URI[cetarball.md5sum] = "d66a8ccbd6cdfe9f735af9f4a07e2b9b"
SRC_URI[cetarball.sha256sum] = "ed7b7399903bbf76af06ee6457836f344ad75b903618339bae82b3967f27ffa1"

S = "${UNPACKDIR}/codec_engine_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ce/${PV}/exports/codec_engine_${PV},lite.tar.gz;name=cetarball "

do_install() {
        CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
        # Install/Stage the Source Tree
        install -d ${D}${CE_INSTALL_DIR_RECIPE}
        cp ${CP_ARGS} ${S}/* ${D}${CE_INSTALL_DIR_RECIPE}
}

ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-dev += "${CE_INSTALL_DIR_RECIPE}"
