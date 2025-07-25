# Generate list of DTBs from the kernel source
def get_dtbs_from_kernel(dts_dir, dts_prefix):
    import os
    import glob
    matches = []

    for prefix in dts_prefix.split():
        filenames = glob.glob(dts_dir + prefix + '*.dts')
        filenames += glob.glob(dts_dir + prefix + '*.dtso')
        for filename in filenames:
            # Before v6.2 kernels DTB Overlays shared the same name as DTB files
            # so we need to search the file to find the type
            with open(filename) as f:
                file_postfix = '.dtbo' if '/plugin/;' in f.read() else '.dtb'
            filename = os.path.split(filename)[1]
            filename = os.path.splitext(filename)[0] + file_postfix
            filename = os.path.join(os.path.split(prefix)[0], filename)
            matches.append(filename)
    return ' '.join(matches)

# Generate list of "merged" DTBs from the kernel source
# It is TI custom feature to merge DTB overlays into a single DTB
def get_merge_dtbs_from_kernel(dts_dir, dts_pattern):
    import os
    matches = []
    if dts_dir == "":
        return ' '
    for pattern in dts_pattern.split():
        pattern_dir = os.path.split(pattern)[0]
        pattern_target = os.path.split(pattern)[1].replace(".","-") + "s"
        makefile = dts_dir + "/" + pattern_dir + "/Makefile"
        if os.path.exists(makefile):
            with open(makefile) as f:
                if pattern_target in f.read():
                    matches.append(pattern)
    return ' '.join(matches)

KERNEL_DEVICETREE = " \
    ${@get_dtbs_from_kernel('${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/', '${KERNEL_DEVICETREE_PREFIX}')} \
    ${@get_merge_dtbs_from_kernel('${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/', '${KERNEL_DEVICETREE_DTBMERGE}')} \
"

do_image_wic[depends] += "virtual/kernel:do_shared_workdir"
