# Install the essential utils for dm-verity in ti-core-initramfs
TI_CORE_INITRAMFS_EXTRA_INSTALL:append = "${@' initramfs-module-dmverity cryptsetup util-linux-mount' if d.getVar('DM_VERITY_IMAGE') else ''}"

# Ensure dm-verity.env is updated also when rebuilding DM_VERITY_IMAGE
do_image[nostamp] = "1"
do_rootfs[nostamp] = "1"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

deploy_verity_hash() {
    # The .verity.env file is created by the DM_VERITY_IMAGE build (e.g., arago-base-image).
    # On the first pass, it won't exist yet because the initramfs is built before the rootfs
    # (due to INITRAMFS_IMAGE dependency). The do_rootfs[nostamp] = "1" causes this task
    # to re-run after the rootfs is built, at which point the file will exist.
    if [ -f ${STAGING_VERITY_DIR}/${DM_VERITY_IMAGE}.${DM_VERITY_IMAGE_TYPE}.verity.env ]; then
        install -D -m 0644 \
            ${STAGING_VERITY_DIR}/${DM_VERITY_IMAGE}.${DM_VERITY_IMAGE_TYPE}.verity.env \
            ${IMAGE_ROOTFS}${datadir}/misc/dm-verity.env
    else
        bbwarn "dm-verity.env not found at ${STAGING_VERITY_DIR}/${DM_VERITY_IMAGE}.${DM_VERITY_IMAGE_TYPE}.verity.env - will retry on rebuild (nostamp)"
    fi
}


IMAGE_PREPROCESS_COMMAND += "deploy_verity_hash;"
