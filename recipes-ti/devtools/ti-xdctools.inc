require ti-xdctools-common.inc
require ../includes/ti-eula-unpack.inc

S = "${WORKDIR}/xdctools_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/rtsc/${PV}/exports/xdctools_setuplinux_${PV}.bin;name=xdcbin"

BINFILE="xdctools_setuplinux_${PV}.bin"
TI_BIN_UNPK_CMDS="Y:workdir:Y"
TI_BIN_UNPK_WDEXT="/ti"
