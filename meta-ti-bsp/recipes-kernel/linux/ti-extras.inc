
# Use different commit, repo and branch for TI extras build
# This will have priority over generic rt path

COMPATIBLE_MACHINE = "am62xx"

BRANCH = "ti-linux-6.1.y"
BRANCH:tie-jailhouse = "ti-linux-6.1.y-jailhouse"

SRCREV = "30bcc25e7cb1b25667db88d61828728f0f0b5f4b"
SRCREV:tie-jailhouse = "0ed0dec2462b805b0188fe080895adc437871549"

KERNEL_GIT_URI = "git://git.ti.com/git/processor-sdk/linux.git"

