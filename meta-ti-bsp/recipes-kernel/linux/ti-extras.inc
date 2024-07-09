
# Use different commit, repo and branch for TI extras build
# This will have priority over generic rt path

COMPATIBLE_MACHINE = "am62xx|am62pxx"

BRANCH = "ti-linux-6.6.y"
BRANCH:tie-jailhouse = "ti-linux-6.6.y-jailhouse"

SRCREV = "e298dab666632b6f62ed61eac35813331341c3aa"
SRCREV:tie-jailhouse = "b45ba5ae4b724d4a09c0c7125ffb49e9d58c04f7"

KERNEL_GIT_URI = "git://git.ti.com/git/processor-sdk/linux.git"

