SUMMARY = "Linux-based partitioning hypervisor"
DESCRIPTION = "Jailhouse is a partitioning Hypervisor based on Linux. It is able to run bare-metal applications or (adapted) \
operating systems besides Linux. For this purpose, it configures CPU and device virtualization features of the hardware \
platform in a way that none of these domains, called 'cells' here, can interfere with each other in an unacceptable way."
HOMEPAGE = "https://github.com/siemens/jailhouse"
SECTION = "jailhouse"

LICENSE = "GPL-2.0-only & BSD-2-Clause"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=9fa7f895f96bde2d47fd5b7d95b6ba4d \
"

COMPATIBLE_MACHINE = "am62xx|am62pxx|am62lxx"
TARGET_CC_ARCH += "${LDFLAGS}"
PV = "0.12+git"
SRCREV = "ef512f9591febed854d8f07ad8c1fa731c2d3c69"
BRANCH = "master"
SRC_URI = " \
    git://git.ti.com/git/jailhouse/ti-jailhouse.git;protocol=https;branch=${BRANCH} \
"

# Set jailhouse architecture JH_ARCH variable
#
# return value must match one of architectures supported by jailhouse
#
valid_jh_archs = "x86 arm"

def map_jh_arch(a, d):
    import re

    valid_jh_archs = d.getVar('valid_jh_archs', True).split()

    if   re.match('(i.86|athlon|x86.64)$', a):  return 'x86'
    elif re.match('armeb$', a):                 return 'arm'
    elif re.match('aarch64$', a):               return 'arm64'
    elif re.match('aarch64_be$', a):            return 'arm64'
    elif a in valid_jh_archs:                   return a
    else:
        bb.error("cannot map '%s' to a jailhouse supported architecture" % a)

export JH_ARCH = "${@map_jh_arch(d.getVar('TARGET_ARCH', True), d)}"

COMPATIBLE_HOST = "(i.86|x86_64|arm|aarch64).*-linux"

S = "${WORKDIR}/git"
B = "${S}"

DEPENDS = "virtual/kernel dtc-native python3-mako-native python3-mako make-native"

inherit module python3native bash-completion deploy setuptools3

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(ti-soc)"

JH_DATADIR ?= "${datadir}/jailhouse"
JH_EXEC_DIR ?= "${libexecdir}/jailhouse"
CELL_DIR ?= "${JH_DATADIR}/cells"
CELLCONF_DIR ?= "${JH_DATADIR}/configs"
INMATES_DIR ?= "${JH_DATADIR}/inmates"

JH_SYSCONFIG_CELL ?= ""
JH_SYSCONFIG_CELL:am62xx ?= "k3-am625-sk.cell"
JH_SYSCONFIG_CELL:am65xx ?= "k3-am654-idk.cell"
JH_SYSCONFIG_CELL:am62pxx ?= "k3-am62p5-sk.cell"
JH_SYSCONFIG_CELL:am62lxx ?= "k3-am62l3-evm.cell"
JH_SYSCONFIG_CELL:j7 ?= "k3-j721e-evm.cell"
JH_SYSCONFIG_CELL:j7200-evm ?= "k3-j7200-evm.cell"

do_configure() {
        if [ -d ${STAGING_DIR_HOST}/${CELLCONF_DIR} ];
        then
            cp ${STAGING_DIR_HOST}/${CELLCONF_DIR}/*.c ${S}/configs/
        fi
}

USER_SPACE_CFLAGS = '${CFLAGS} -DLIBEXECDIR=\\\"${libexecdir}\\\" \
                    -DJAILHOUSE_VERSION=\\\"$JAILHOUSE_VERSION\\\" \
                    -Wall -Wextra -Wmissing-declarations -Wmissing-prototypes -Werror \
                    -I../driver'

TOOLS_SRC_DIR = "${S}/tools"

EXTRA_OEMAKE = "ARCH=${JH_ARCH} CROSS_COMPILE=${TARGET_PREFIX} CC="${CC}" KDIR=${STAGING_KERNEL_BUILDDIR}"

do_compile() {
        oe_runmake V=1
}
