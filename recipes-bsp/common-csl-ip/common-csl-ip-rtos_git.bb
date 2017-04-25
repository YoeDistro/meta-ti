require common-csl-ip.inc
PR = "${INC_PR}.4"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit ti-pdk

DEPENDS_remove = "${PN}"

# Build with make instead of XDC
XDCMAKE = "0"

LIMSOCS_ti33x = "am335x"
LIMSOCS_ti43x = "am437x"
LIMSOCS_omap-a15 = "am571x am572x"
LIMSOCS_k2hk = "k2h k2k"
LIMSOCS_k2l-evm = "k2l"
LIMSOCS_k2e = "k2e"
LIMSOCS_k2g = "k2g"

export PDK_CSL_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"
