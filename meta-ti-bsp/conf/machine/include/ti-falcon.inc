IMAGE_INSTALL:append = " u-boot-ti-staging-falcon"
KERNEL_CLASSES:remove = "kernel-fitimage"
KERNEL_CLASSES += "kernel-fitimage-legacyhs"
FIT_KERNEL_COMP_ALG = "none"
