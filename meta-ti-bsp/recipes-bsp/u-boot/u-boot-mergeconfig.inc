do_configure:append () {
   if [ -n "${UBOOT_CONFIG_FRAGMENTS}" ] && [ -n "${UBOOT_MACHINE}" ]
   then
       oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE} ${UBOOT_CONFIG_FRAGMENTS}
       oe_runmake -C ${S} O=${B} olddefconfig
   fi
}
