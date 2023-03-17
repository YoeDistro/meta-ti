
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

KERNEL_DEVICETREE = "${@get_dtbs_from_kernel('${S}/arch/${ARCH}/boot/dts/', '${KERNEL_DEVICETREE_PREFIX}')} ${KERNEL_DEVICETREE_DTBMERGE}"

