# Recipe to fetch/unpack sources used by ti-pdk-fetch recipes.
#
# Mimic gcc-source.inc from oe-core.

# These shources will be unpacked to a "work-shared" directory. Then each
# "ti-pdk-fetch" recipe will hard-link only the sources it requires.

deltask do_configure
deltask do_compile
deltask do_install
deltask do_populate_sysroot
deltask do_populate_lic

RM_WORK_EXCLUDE += "${PN}"
EXCLUDE_FROM_WORLD = "1"

inherit nopackages ti-pdk-fetch

LICENSE = "BSD-3-Clause"

PN = "${TI_PDK_SOURCE_PN}"
WORKDIR = "${TI_PDK_SOURCE_WORKDIR}"
SSTATE_SWSPEC = "sstate:ti-pdk::${PV}:${PR}::${SSTATE_VERSION}:"

STAMP = "${STAMPS_DIR}/work-shared/ti-pdk-${PV}"
STAMPCLEAN = "${STAMPS_DIR}/work-shared/ti-pdk-${PV}*"

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = ""
PACKAGES = ""

SRC_URI = "${TI_PDK_GIT_URI};branch=${TI_PDK_GIT_BRANCH};protocol=${TI_PDK_GIT_PROTOCOL}"
SRCREV = "${TI_PDK_SRCREV}"
