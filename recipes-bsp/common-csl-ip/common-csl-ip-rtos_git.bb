require common-csl-ip.inc
PR = "${INC_PR}.4"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit ti-pdk

DEPENDS_remove = "${PN}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

TI_PDK_LIMIT_SOCS_ti33x = "am335x"
TI_PDK_LIMIT_SOCS_ti43x = "am437x"
TI_PDK_LIMIT_SOCS_omap-a15 = "am571x am572x"
TI_PDK_LIMIT_SOCS_k2hk = "k2h k2k"
TI_PDK_LIMIT_SOCS_k2l-evm = "k2l"
TI_PDK_LIMIT_SOCS_k2e = "k2e"
TI_PDK_LIMIT_SOCS_k2g = "k2g"

export PDK_CSL_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"
