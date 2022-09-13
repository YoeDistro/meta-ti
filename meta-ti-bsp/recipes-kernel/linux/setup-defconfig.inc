# KERNEL_LOCALVERSION can be set to add a tag to the end of the
# kernel version string.  such as the commit id
KERNEL_LOCALVERSION = "-g${@d.getVar('SRCPV', True).replace('AUTOINC+','')[:10]}"

# Check the defconfig file and see if it points to an in kernel
# defconfig that should be used, or if it is a complete config file
# Or if it points to a combined defconfig that lists both in kernel
# defconfig and associated config fragments.

do_configure() {
    # Always copy the defconfig file to .config to keep consistency
    # between the case where there is a real config and the in kernel
    # tree config
    cp ${WORKDIR}/defconfig ${B}/.config

    echo ${KERNEL_LOCALVERSION} > ${B}/.scmversion
    echo ${KERNEL_LOCALVERSION} > ${S}/.scmversion

    # Zero, when using "tisdk" configs, pass control to defconfig_builder
    config=`cat ${B}/.config | grep use-tisdk-config | cut -d= -f2`
    if [ -n "$config" ]
    then
        ${DEFCONFIG_BUILDER} -w ${S} -t $config
        oe_runmake -C ${S} O=${B} "$config"_defconfig
    else
        # First, check if pointing to a combined config with config fragments
        config=`cat ${B}/.config | grep use-combined-config | cut -d= -f2`
        if [ -n "$config" ]
        then
            cp ${S}/$config ${B}/.config
        fi

        # Second, extract any config fragments listed in the defconfig
        config=`cat ${B}/.config | grep config-fragment | cut -d= -f2`
        if [ -n "$config" ]
        then
            configfrags=""
            for f in $config
            do
                # Check if the config fragment is available
                if [ ! -e "${S}/$f" ]
                then
                    echo "Could not find kernel config fragment $f"
                    exit 1
                else
                    # Sanitize config fragment files to be relative to sources
                    configfrags="$configfrags ${S}/$f"
                fi
            done
        fi

        # Third, check if pointing to a known in kernel defconfig
        config=`cat ${B}/.config | grep use-kernel-config | cut -d= -f2`
        if [ -n "$config" ]
        then
            oe_runmake -C ${S} O=${B} $config
        else
            yes '' | oe_runmake -C ${S} O=${B} oldconfig
        fi
    fi

    # Fourth, handle config fragments specified in the recipe
    # The assumption is that the config fragment will be specified with the absolute path.
    # E.g. ${WORKDIR}/config1.cfg or ${S}/config2.cfg
    if [ -n "${KERNEL_CONFIG_FRAGMENTS}" ]
    then
        for f in ${KERNEL_CONFIG_FRAGMENTS}
        do
            # Check if the config fragment is available
            if [ ! -e "$f" ]
            then
                echo "Could not find kernel config fragment $f"
                exit 1
            fi
        done
    fi

    # Now that all the fragments are located merge them
    if [ -n "${KERNEL_CONFIG_FRAGMENTS}" -o -n "$configfrags" ]
    then
        ( cd ${WORKDIR} && ${S}/scripts/kconfig/merge_config.sh -m -r -O ${B} ${B}/.config $configfrags ${KERNEL_CONFIG_FRAGMENTS} 1>&2 )
        yes '' | oe_runmake -C ${S} O=${B} oldconfig
    fi
}
