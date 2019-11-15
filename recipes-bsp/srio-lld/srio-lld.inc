LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/srio/srio_drv.h;beginline=1;endline=41;md5=469096088e072d883ff7eef301b34093"

COMPATIBLE_MACHINE = "k2hk"

inherit ti-pdk-fetch

TI_PDK_COMP = "ti.drv.srio"

PE = "1"

INC_PR = "r0"

DEVICELIST = "k2h k2k"

BASEDIR = "${WORKDIR}/git"

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR}"
