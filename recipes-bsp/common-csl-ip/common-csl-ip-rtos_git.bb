require common-csl-ip.inc
PR = "${INC_PR}.6"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE_append = "|c66x|k3"

inherit ti-pdk

DEPENDS_remove = "${PN}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_CSL_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "CSL-Chip Support Library"

# Workaround: dra7xx build requires am57xx CSL libraries for opencl-monitor
TI_PDK_LIMIT_SOCS_append_dra7xx = " am571x am572x am574x"
