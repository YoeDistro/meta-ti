FILESEXTRAPATHS:prepend := "${THISDIR}/pm-qa:"

# Bump to 0.5.0+
SRCREV = "e66966ef6a08cd3b491ee1df5c6c5d33baaf1f1c"

SRC_URI += " \
	file://0001-Return-non-zero-value-on-test-failure.patch \
	file://0002-cpuidle-Avoid-relative-paths.patch \
"

PATCHTOOL = "git"

PR:append = ".ti0"
