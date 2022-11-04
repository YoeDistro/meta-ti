DESCRIPTION = "The cmem component supports contiguous memory allocation from userspace"

include cmem.inc

PR = "r0"

PACKAGES =+ "${PN}-test"

FILES:${PN}-test = "${bindir}/*"

inherit autotools
