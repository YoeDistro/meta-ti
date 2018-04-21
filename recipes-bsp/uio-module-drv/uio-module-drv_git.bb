DESCRIPTION = "User space IO (UIO) driver for on-chip modules"

include uio-module-drv.inc

# This package builds a kernel module, use kernel PR as base and append a local
MACHINE_KERNEL_PR_append = "d"
PR = "${MACHINE_KERNEL_PR}"

module_auto_load_uio-module-drv = "uio-module-drv"

inherit module

do_install_append () {
    install -d ${D}${includedir}
    cp *.h ${D}${includedir}
}

KERNEL_MODULE_AUTOLOAD += "uio_module_drv"
