# This will have priority over generic rt path

#
# Jailhouse settings
#
COMPATIBLE_MACHINE:tie-jailhouse = "am62xx|am62pxx|am62lxx"

BRANCH:tie-jailhouse:bsp-ti-6_12 = "ti-linux-6.12.y-jailhouse"

SRCREV:tie-jailhouse:bsp-ti-6_12 = "229a48602ad1557612a4ffabec6a3cbcdd745f87"

KERNEL_GIT_URI:tie-jailhouse = "git://git.ti.com/git/processor-sdk/linux.git"
