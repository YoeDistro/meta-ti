
# Use different commit, repo and branch for TI extras build
# This will have priority over generic rt path

COMPATIBLE_MACHINE = "am62xx|am62pxx"

BRANCH = "ti-linux-6.1.y"
BRANCH:tie-jailhouse = "ti-linux-6.1.y-jailhouse"

SRCREV = "ec058c3ddc5377c629e988a95ef84826b14a3307"
SRCREV:tie-jailhouse = "d035e0cc72399f1c087a63c4210d29e79a7a66e4"

KERNEL_GIT_URI = "git://git.ti.com/git/processor-sdk/linux.git"

