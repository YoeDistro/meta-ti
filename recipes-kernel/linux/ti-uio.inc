SRC_URI_append_keystone = " file://keystone-uio.dtsi"
SRC_URI_append_k2hk = " file://k2hk-uio.dtsi"
SRC_URI_append_k2l = " file://k2l-uio.dtsi"
SRC_URI_append_k2e = " file://k2e-uio.dtsi"
SRC_URI_append_k2g = " file://keystone-k2g-pru-uio.dtsi"
SRC_URI_append_dra7xx = " file://am57xx-pru-uio.dtsi \
                          file://am571x-pru-uio.dtsi \
"
SRC_URI_append_ti33x = " file://am335x-pru-uio.dtsi"
SRC_URI_append_ti43x = " file://am437x-pru-uio.dtsi"

DTSI_LIST = ""
DTSI_LIST_append_keystone = " keystone-uio.dtsi"
DTSI_LIST_append_k2hk = " k2hk-uio.dtsi"
DTSI_LIST_append_k2l = " k2l-uio.dtsi"
DTSI_LIST_append_k2e = " k2e-uio.dtsi"

do_setup_uio() {
    if [ "${ENABLE_TI_UIO_DEVICES}" == "1" ]
    then
        for dtsi in ${DTSI_LIST}
        do
            cp ${WORKDIR}/${dtsi} ${S}/arch/arm/boot/dts/
            for dtb in ${KERNEL_DEVICETREE}
            do
                dts=`echo $dtb | sed -e 's|dtb$|dts|'`
                echo "#include \"${dtsi}\"" >> ${S}/arch/arm/boot/dts/$dts
            done
        done
    fi
}

do_setup_uio_append_dra7xx() {

    if [ "${ENABLE_TI_UIO_DEVICES}" == "1" ]
    then
        dtsi="am57xx-pru-uio.dtsi"
        cp ${WORKDIR}/${dtsi} ${S}/arch/arm/boot/dts/

        dts="am572x-idk-pru-excl-uio.dts"
        cp ${S}/arch/arm/boot/dts/am572x-idk.dts ${S}/arch/arm/boot/dts/$dts
        echo "#include \"${dtsi}\"" >> ${S}/arch/arm/boot/dts/$dts

        dts="am574x-idk-pru-excl-uio.dts"
        cp ${S}/arch/arm/boot/dts/am574x-idk.dts ${S}/arch/arm/boot/dts/$dts
        echo "#include \"${dtsi}\"" >> ${S}/arch/arm/boot/dts/$dts

        dts="am571x-idk-pru-excl-uio.dts"
        dtsi="am571x-pru-uio.dtsi"
        cp ${S}/arch/arm/boot/dts/am571x-idk.dts ${S}/arch/arm/boot/dts/$dts
        cp ${WORKDIR}/${dtsi} ${S}/arch/arm/boot/dts/
        echo "#include \"${dtsi}\"" >> ${S}/arch/arm/boot/dts/$dts
    fi
}

do_setup_uio_append_ti33x() {

    if [ "${ENABLE_TI_UIO_DEVICES}" == "1" ]
    then
        dts="am335x-icev2-pru-excl-uio.dts"
        dtsi="am335x-pru-uio.dtsi"
        cp ${S}/arch/arm/boot/dts/am335x-icev2-prueth.dts ${S}/arch/arm/boot/dts/$dts
        cp ${WORKDIR}/${dtsi} ${S}/arch/arm/boot/dts/
        echo "#include \"${dtsi}\"" >> ${S}/arch/arm/boot/dts/$dts
    fi
}

do_setup_uio_append_ti43x() {

    if [ "${ENABLE_TI_UIO_DEVICES}" == "1" ]
    then
        dts="am437x-idk-pru-excl-uio.dts"
        dtsi="am437x-pru-uio.dtsi"
        cp ${S}/arch/arm/boot/dts/am437x-idk-evm.dts ${S}/arch/arm/boot/dts/$dts
        cp ${WORKDIR}/${dtsi} ${S}/arch/arm/boot/dts/
        echo "#include \"${dtsi}\"" >> ${S}/arch/arm/boot/dts/$dts
    fi
}

do_setup_uio_append_k2g() {

    if [ "${ENABLE_TI_UIO_DEVICES}" == "1" ]
    then
        dts="keystone-k2g-ice-pru-excl-uio.dts"
        dtsi="keystone-k2g-pru-uio.dtsi"
        cp ${S}/arch/arm/boot/dts/keystone-k2g-ice.dts ${S}/arch/arm/boot/dts/$dts
        cp ${WORKDIR}/${dtsi} ${S}/arch/arm/boot/dts/
        echo "#include \"${dtsi}\"" >> ${S}/arch/arm/boot/dts/$dts
    fi
}

do_patch[postfuncs] += "do_setup_uio"
