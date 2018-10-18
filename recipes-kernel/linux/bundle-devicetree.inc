# Upstream kernel-devicetree.bbclass only supports bundling the DTB with
# zImage. The factory u-boot on lego-ev3 EEPROM only supports uImage, so we
# append the DTB to the final uImage here.

do_deploy_append() {
    if [ "${KERNEL_DEVICETREE_BUNDLE}" = "1" ]; then
        install -d ${DEPLOYDIR}
        for dtbf in ${KERNEL_DEVICETREE}; do
            dtb=`normalize_dtb "$dtbf"`
            dtb_ext=${dtb##*.}
            dtb_base_name=`basename $dtb .$dtb_ext`
            for type in ${KERNEL_IMAGETYPE_FOR_MAKE}; do
                if [ "$type" = "uImage" ]; then
                    cat ${D}/${KERNEL_IMAGEDEST}/$type \
                    ${DEPLOYDIR}/$dtb_base_name-${KERNEL_DTB_NAME}.$dtb_ext \
                    > ${DEPLOYDIR}/$type-$dtb_base_name-${KERNEL_DTB_NAME}.$dtb_ext.bin
                    ln -sf $type-$dtb_base_name-${KERNEL_DTB_NAME}.$dtb_ext.bin \
                    ${DEPLOYDIR}/$type-$dtb_base_name-${KERNEL_DTB_LINK_NAME}.$dtb_ext.bin
                    cat ${D}/${KERNEL_IMAGEDEST}/$type \
                    ${DEPLOYDIR}/$dtb_base_name-${KERNEL_DTB_NAME}.$dtb_ext \
                    > ${DEPLOYDIR}/$type
                fi
            done
        done
    fi
}
