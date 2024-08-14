# Handle U-Boot config fragments for a machine
#
# This interacts with the UBOOT_CONFIG flow
#
# The format to specify it, in the machine, is:
#
# UBOOT_FRAGMENTS_CONFIG[foo] = "fragment[,fragment...]"
#
# or
#
# UBOOT_FRAGMENTS = "fragment[,fragment...]"
#
# Copyright 2024 (C) Texas Instruments, Inc.
#
# SPDX-License-Identifier: MIT

python () {
    loUbootConfig = (d.getVar('UBOOT_CONFIG') or "").split()
    loUbootFragments = d.getVar('UBOOT_FRAGMENTS')
    loUbootFragmentsConfigFlags = d.getVarFlags('UBOOT_FRAGMENTS_CONFIG')
        
    if loUbootFragmentsConfigFlags and loUbootFragments:
        raise bb.parse.SkipRecipe("You cannot use UBOOT_FRAGMENTS and UBOOT_FRAGMENTS_CONFIG at the same time.")

    if loUbootFragmentsConfigFlags is not None and len(loUbootConfig) > 0:
        for lpConfig in loUbootConfig:
            loFound = False
            for lpFlag, lpValue in loUbootFragmentsConfigFlags.items():
                if lpConfig == lpFlag: 
                    loFound = True
                    if lpValue == "":
                        d.appendVar('UBOOT_FRAGMENTS', ' none')
                    else:
                        d.appendVar('UBOOT_FRAGMENTS', ' ' + lpValue)

            if not loFound:
                raise bb.parse.SkipRecipe("The selected UBOOT_CONFIG key %s has no match in %s." % (loUbootConfig, loUbootFragmentsConfigFlags.keys()))
}

uboot_configure_config:append () {
   if [ -n "${UBOOT_FRAGMENTS}" ]
   then
        unset loTypeIdx
        for lpType in ${UBOOT_CONFIG}; do
            loTypeIdx=$(expr $loTypeIdx + 1)

            if [ "${lpType}" == "${type}" ]; then
                break
            fi
        done
        
        loApplyFragments=""
        
        for lpFragment in ${UBOOT_FRAGMENTS}; do
            lpFragmentIdx=$(expr $lpFragmentIdx + 1)
            if [ $y -eq $x ]; then
                if [ "${lpFragment}" != "none" ]; then
                    loApplyFragments=`echo ${lpFragment} | tr "," " "`
                fi
                break
            fi
        done

        if [ -n "${loApplyFragments}" ]; then
            oe_runmake -C ${S} O=${B}/${config} ${config} ${loApplyFragments}
            oe_runmake -C ${S} O=${B}/${config} oldconfig
        fi
    fi
}

uboot_configure:append () {
   if [ -n "${UBOOT_FRAGMENTS}" ] && [ -n "${UBOOT_MACHINE}" ]
   then
       oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE} `echo ${UBOOT_FRAGMENTS} | tr "," " "`
       oe_runmake -C ${S} O=${B} oldconfig
   fi
}
