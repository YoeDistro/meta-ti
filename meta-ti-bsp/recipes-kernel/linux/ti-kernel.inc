# Add DTC FLAGS -@ when KERNEL_DTB_OVERLAY_SUPPORT is enabled

def get_extra_dtc_args(d):
    if d.getVar('KERNEL_DTB_OVERLAY_SUPPORT') == "1":
        return "DTC_FLAGS=-@"
    else:
        return ""

EXTRA_DTC_ARGS += "${@get_extra_dtc_args(d)}"

# Tell the kernel class to install the DTBs in the same directory structure as
# the kernel.
KERNEL_DTBDEST = "${KERNEL_IMAGEDEST}/dtb"
KERNEL_DTBVENDORED = "1"

