
# Use different commit, repo and branch for TI extras build
# This will have priority over generic rt path

COMPATIBLE_MACHINE = "am62xx|am62pxx"

BRANCH = "ti-rt-linux-6.6.y"
BRANCH:tie-jailhouse = "ti-rt-linux-6.6.y-jailhouse"

SRCREV = "fb89cd4d622ce6cc1b7b254ae21298570d437a08"
SRCREV:tie-jailhouse = "ea592a872ebdb974f26f6f839596e0a79e9d408b"
