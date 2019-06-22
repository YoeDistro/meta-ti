SUMMARY = "Video Decoding Firmware Packaging recipe"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84ca7278930db001870686ad997d6bb1"

BRANCH = "master"
SRCREV = "0acbf1d0a7c7eaca0ef14c1a30ce313ea82147e5"

SRC_URI = "git://git.ti.com/jacinto7_multimedia/ti-img-encode-decode.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

PV = "1.0-git${SRCPV}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

VXD_FW_DIR = "${S}/decoder/firmware/"

# make sure that lib/firmware, and all its contents are part of the package
FILES_${PN} += "${base_libdir}/firmware"

do_install() {
  install -d ${D}${base_libdir}/firmware
  install -m 0755 ${VXD_FW_DIR}/pvdec_full_bin.fw ${D}${base_libdir}/firmware
}

# This is used to prevent the build system to strip the executables
INHIBIT_PACKAGE_STRIP = "1"
# This is used to prevent the build system to split the debug info in a separate file
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
# As it likely to be a different arch from the Yocto build, disable checking by adding "arch" to INSANE_SKIP
INSANE_SKIP_${PN} += "arch"

# we don't want to configure and build the source code
do_compile[noexec] = "1"
do_configure[noexec] = "1"
