SUMMARY = "Diagnostic tool for TI K3 processors"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a56bddef00b39fb0f45488fdc4ce108f"

inherit cmake

PV = "0.3+git${SRCPV}"

COMPATIBLE_MACHINE = "k3"

BRANCH ?= "master"
SRCREV = "cadf72379c7eec7e69031cc939d5a85f209017d6"

SRC_URI = "git://git.ti.com/git/k3conf/k3conf.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"
