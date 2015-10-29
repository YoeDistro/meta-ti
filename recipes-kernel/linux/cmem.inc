SRC_URI += "file://cmem.dtsi"

RESERVE_CMEM ?= "0"

do_setup_cmem() {
    if [ "${RESERVE_CMEM}" == "1" ]
    then
        cp ${WORKDIR}/cmem.dtsi ${S}/arch/arm/boot/dts/${MACHINE}-cmem.dtsi

        for dtb in ${KERNEL_DEVICETREE}
        do
            dts=`echo $dtb | sed -e 's|dtb$|dts|'`
            echo "#include \"${MACHINE}-cmem.dtsi\"" >> ${S}/arch/arm/boot/dts/$dts
        done
    fi
}

do_patch[postfuncs] += "do_setup_cmem"
