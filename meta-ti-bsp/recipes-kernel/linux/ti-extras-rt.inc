
# Use different commit, repo and branch for TI extras build
# This will have priority over generic rt path

COMPATIBLE_MACHINE = "am62xx|am62pxx|am62lxx"

BRANCH = "ti-linux-6.12.y"
BRANCH:tie-jailhouse = "ti-linux-6.12.y-jailhouse"

SRCREV = "c9e21a1ebd757f2b4487035382bbf65299cf8170"
SRCREV:tie-jailhouse = "229a48602ad1557612a4ffabec6a3cbcdd745f87"
