DESCRIPTION = "Package containing PDK makefile infrastructure"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://makerules/env.mk;beginline=1;endline=31;md5=10503e8de09f5475185f3fd56de1e310"

require recipes-ti/includes/ti-paths.inc

PV = "01.00.00.09A"
PR = "r0"

PDK_BUILD_GIT_URI = "git://git.ti.com/keystone-rtos/processor-pdk-build.git"
PDK_BUILD_GIT_PROTOCOL = "git"
PDK_BUILD_GIT_BRANCH = "master"

# Below Commit ID corresponds to "DEV.PDK_BUILD.01.00.00.09A"
PDK_BUILD_SRCREV = "4870d6cfcab92dfedae8ed28054fdc61169e28b1"

BRANCH = "${PDK_BUILD_GIT_BRANCH}"
SRC_URI = "\
    ${PDK_BUILD_GIT_URI};protocol=${PDK_BUILD_GIT_PROTOCOL};branch=${BRANCH} \
    file://0001-comp_mk-Allow-doxygen-support-to-be-overridden.patch \
    "

SRCREV = "${PDK_BUILD_SRCREV}"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1|c66x"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git/"

PARALLEL_MAKE = ""
CLEANBROKEN = "1"

do_compile() {
    :
}

EXTRA_OEMAKE = "-f make_install LIMIT_SOCS="${TI_PDK_LIMIT_SOCS}" PDK_INSTALL_DIR="${D}${PDK_INSTALL_DIR_RECIPE}""

do_install() {
    oe_runmake
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages/*"
