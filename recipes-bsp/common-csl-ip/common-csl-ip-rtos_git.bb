require common-csl-ip.inc
PR = "${INC_PR}.5"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE_append = "|c66x"

inherit ti-pdk

DEPENDS_remove = "${PN}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_CSL_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"
