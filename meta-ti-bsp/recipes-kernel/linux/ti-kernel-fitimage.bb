SUMMARY = "Generate and sign the fitImage file for TI Machines"

FIT_DESC ?= "Kernel fitImage for ${DISTRO_NAME}/${PKGV}/${MACHINE}"
FIT_CONF_PREFIX ?= "conf-ti_"

require recipes-kernel/linux/linux-yocto-fitimage.bb

inherit ti-secdev

inherit ${@ 'ti-devicetree-prefix' if d.getVar('KERNEL_DEVICETREE_PREFIX') else ''}

do_compile[depends] += "virtual/kernel:do_shared_workdir"
