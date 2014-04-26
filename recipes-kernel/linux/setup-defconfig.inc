# KERNEL_LOCALVERSION can be set to add a tag to the end of the
# kernel version string.  such as the commit id
KERNEL_LOCALVERSION ?= ""

# Check the defconfig file and see if it points to an in kernel
# defconfig that should be used, or if it is a complete config file

# define our own do_configure that will:
#   1. Check the .config file and see if string use-kernel-config= is present
#   2. If the use-kernel-config string is present parse out the config to use
#      and run make $config
#   3. else run yes '' | oe_runmake oldconfig like the default do_configure
#      does
do_configure() {
    # Always copy the defconfig file to .config to keep consistency
    # between the case where there is a real config and the in kernel
    # tree config
    cp ${WORKDIR}/defconfig ${S}/.config

    echo ${KERNEL_LOCALVERSION} > ${B}/.scmversion
    echo ${KERNEL_LOCALVERSION} > ${S}/.scmversion
    config=`cat ${S}/.config | grep use-kernel-config | cut -d= -f2`
    if [ ! -z "$config" ]
    then
        oe_runmake $config
    else
        yes '' | oe_runmake oldconfig
    fi

    # check for fragments
    if [ ! -z "${KERNEL_CONFIG_FRAGMENTS}" ]
    then
        ( cd ${WORKDIR} && ${S}/scripts/kconfig/merge_config.sh -m -r -O ${S} ${S}/.config ${KERNEL_CONFIG_FRAGMENTS} 1>&2 )
        yes '' | oe_runmake oldconfig
    fi
}
