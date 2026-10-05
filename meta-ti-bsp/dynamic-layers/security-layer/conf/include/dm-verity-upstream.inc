# Enables dm-verity to check the rootfs for tampering on TI K3 boards.
DM_VERITY_IMAGE_TYPE = "ext4"
IMAGE_CLASSES += "dm-verity-img"

# ti-core-initramfs.bbappend already wires the initramfs into the boot partition once dm-verity is enabled, so we don't need to do it here.

python () {
    import uuid

    if d.getVar('PN') != d.getVar('DM_VERITY_IMAGE'):
        return

    # Derive the root partition's UUID from MACHINE so everyone computes the same one.
    if not d.getVar('DM_VERITY_ROOT_PARTUUID'):
        d.setVar('DM_VERITY_ROOT_PARTUUID',
                 str(uuid.uuid5(uuid.NAMESPACE_DNS, 'dm-verity-root-%s' % d.getVar('MACHINE'))))

    d.setVar('WKS_FILE', 'k3-verity.wks.in')
    d.appendVar('EXTRA_IMAGE_FEATURES', ' read-only-rootfs')
    d.appendVar('WICVARS', ' DM_VERITY_IMAGE DM_VERITY_IMAGE_TYPE IMAGE_NAME_SUFFIX IMGDEPLOYDIR DM_VERITY_ROOT_PARTUUID')
}
