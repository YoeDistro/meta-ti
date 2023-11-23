
# This will have priority over generic uboot path

COMPATIBLE_MACHINE = "am62xx|am62pxx"

BRANCH = "ti-u-boot-2023.04"
BRANCH:tie-jailhouse = "ti-u-boot-2023.04-jailhouse"

SRCREV = "b0d868ee086eb5c96a3d32f72a7cb9670abf5b90"
SRCREV:tie-jailhouse = "765cab112d16fcf68a3266738073663dae67a55d"

UBOOT_GIT_URI = "git://git.ti.com/git/processor-sdk/u-boot.git"
