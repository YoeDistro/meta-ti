DESCRIPTION = "The cmem component supports contiguous memory allocation from userspace"

include cmem.inc

RDEPENDS_${PN} = "cmem-mod"

PR = "r1"

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${bindir}/*"

inherit autotools
