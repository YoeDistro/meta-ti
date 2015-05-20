SRC_URI += "file://cmem.dtsi"

CMEM_BASE ?= ""
CMEM_SIZE ?= ""

do_setup_cmem() {
    if [ ! -z "${CMEM_BASE}" ]
    then
        cp ${WORKDIR}/cmem.dtsi ${S}/arch/arm/boot/dts/${MACHINE}-cmem.dtsi

        sed -i -e "s|__CMEM_BASE__|${CMEM_BASE}|g" \
               -e "s|__CMEM_SIZE__|${CMEM_SIZE}|g" \
               ${S}/arch/arm/boot/dts/${MACHINE}-cmem.dtsi

        for dtb in ${KERNEL_DEVICETREE}
        do
            dts=`echo $dtb | sed -e 's|dtb$|dts|'`
            echo "#include \"${MACHINE}-cmem.dtsi\"" >> ${S}/arch/arm/boot/dts/$dts
        done
    fi
}

do_patch[postfuncs] += "do_setup_cmem"
