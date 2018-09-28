SUMMARY = "TI RTOS low level driver for Security Accelerator (SA)"

inherit ti-pdk

require recipes-bsp/sa-lld/sa-lld.inc

PR = "${INC_PR}.0"

COMPATIBLE_MACHINE_append = "|c667x-evm|k3"

# Build with make instead of XDC
TI_PDK_XDCMAKE_k3 = "0"

# SA promote/demote library depends on osal
DEPENDS_append_k3 = " osal-rtos \
"
export PDK_SA_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

INSANE_SKIP_${PN} = "arch"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "SA LLD"

