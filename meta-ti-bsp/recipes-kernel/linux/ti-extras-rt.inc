
# Use different commit, repo and branch for TI extras build
# This will have priority over generic rt path

COMPATIBLE_MACHINE = "am62xx|am62pxx"

BRANCH = "ti-rt-linux-6.1.y"
BRANCH:tie-jailhouse = "ti-rt-linux-6.1.y-jailhouse"

SRCREV = "685e77152461bd6b791500f717bec62d17c1b36d"
SRCREV:tie-jailhouse = "a74088b40b4d27478a2af5c20cdc0b4ec8ed9470"
