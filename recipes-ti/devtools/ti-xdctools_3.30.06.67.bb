require ti-xdctools.inc

PV = "3_30_06_67"
PR = "r0"

LIC_FILES_CHKSUM = "file://docs/license/xdc/shelf/package.html;md5=117e3c5f48df3e785edee50f5f34a738"

SRC_URI[xdcbin.md5sum] = "b500f53e00c4422c7124e1825eee6352"
SRC_URI[xdcbin.sha256sum] = "e5ca1f55c09b64bc9414adb55b2fcca301178194abed80f1d75d94cae442fd08"

S = "${WORKDIR}/xdctools_${PV}"

TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"
TI_BIN_UNPK_CMDS = ""

INSANE_SKIP_${PN} = "installed-vs-shipped"
