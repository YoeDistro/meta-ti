#
# Jailhouse settings
#
COMPATIBLE_MACHINE:tie-jailhouse = "am62xx|am62pxx|am62lxx"

BRANCH:tie-jailhouse:bsp-ti-6_12 = "ti-u-boot-2025.01-jailhouse"
BRANCH:tie-jailhouse:bsp-ti-6_18 = "ti-u-boot-2026.01-jailhouse"

SRCREV_uboot:tie-jailhouse:bsp-ti-6_12 = "e718bbcec3ebf663c021839753034a224be4cc53"
SRCREV_uboot:tie-jailhouse:bsp-ti-6_18 = "257704a4a690f221064e0210028e8986a4b4ccae"

UBOOT_GIT_URI:tie-jailhouse = "git://git.ti.com/git/processor-sdk/u-boot.git"
