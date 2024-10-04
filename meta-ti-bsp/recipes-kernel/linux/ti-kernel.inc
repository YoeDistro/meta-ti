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

KERNEL_PATCHES = ""

KERNEL_PATCHES:bsp-mainline = " \
    file://0001-vt-conmakehash-improve-reproducibility-for-v6.10.patch \
    file://0001-drivers-gpu-drm-msm-registers-improve-reproducibilit.patch \
"

KERNEL_PATCHES:bsp-ti-6_12 = " \
    file://0001-drivers-gpu-drm-msm-registers-improve-reproducibilit.patch \
"

KERNEL_PATCHES:bsp-ti-6_6 = " \
    file://0001-vt-conmakehash-improve-reproducibility.patch \
"

SRC_URI:append = " ${KERNEL_PATCHES} "
