DESCRIPTION="User space IO (UIO) driver for on-chip modules"

include uio-module-drv.inc
PV = "1.0.2.3+git${SRCPV}"

# This package builds a kernel module, use kernel PR as base and append a local
MACHINE_KERNEL_PR_append = "b"
PR = "${MACHINE_KERNEL_PR}"

module_auto_load_uio-module-drv = "uio-module-drv"

inherit module
