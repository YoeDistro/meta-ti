uboot_configure:append () {
   if [ -n "${UBOOT_CONFIG_FRAGMENTS}" ] && [ -n "${UBOOT_MACHINE}" ]
   then
       oe_runmake -C ${S} O=${B} ${UBOOT_MAKE_OPTS} ${UBOOT_MACHINE} ${UBOOT_CONFIG_FRAGMENTS}
       oe_runmake -C ${S} O=${B} ${UBOOT_MAKE_OPTS} olddefconfig
   fi
}

uboot_configure_config:append () {

   if [ -n "${UBOOT_CONFIG_FRAGMENTS}" ]; then
       oe_runmake -C ${S} O=${B}/${builddir} ${config_make_opts} ${UBOOT_MAKE_OPTS} ${config} ${UBOOT_CONFIG_FRAGMENTS}
       oe_runmake -C ${S} O=${B}/${builddir} ${config_make_opts} ${UBOOT_MAKE_OPTS} olddefconfig
   fi
}
