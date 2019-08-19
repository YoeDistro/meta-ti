SUMMARY = "Linux-based partitioning hypervisor"
DESCRIPTION = "Jailhouse is a partitioning Hypervisor based on Linux. It is able to run bare-metal applications or (adapted) \
operating systems besides Linux. For this purpose, it configures CPU and device virtualization features of the hardware \
platform in a way that none of these domains, called 'cells' here, can interfere with each other in an unacceptable way."
HOMEPAGE = "https://github.com/siemens/jailhouse"
SECTION = "jailhouse"
LICENSE = "GPL-2.0 & BSD-2-Clause"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=9fa7f895f96bde2d47fd5b7d95b6ba4d \
"

PV = "0.10+git${SRCPV}"
SRCREV = "96329f098e655cd42d7d0dbb3cd2cc7c3492d633"
BRANCH = "ti-jailhouse-0.10"

SRC_URI = " \
    git://git.ti.com/jailhouse/ti-jailhouse.git;protocol=git;branch=${BRANCH} \
"

DEPENDS = "virtual/kernel dtc-native python-mako-native python-mako make-native"
RDEPENDS_${PN} += "\
	python-curses\
	python-datetime\
	python-argparse\
	python-mmap\
"

S = "${WORKDIR}/git"

require jailhouse-arch.inc
inherit module pythonnative bash-completion deploy

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(ti-soc)"

JH_DATADIR ?= "${datadir}/jailhouse"
JH_EXEC_DIR ?= "${libexecdir}/jailhouse"
CELL_DIR ?= "${JH_DATADIR}/cells"
CELLCONF_DIR ?= "${JH_DATADIR}/configs"
INMATES_DIR ?= "${JH_DATADIR}/inmates"

JH_CONFIG ?= "${S}/ci/jailhouse-config-x86.h"
JH_CONFIG_k3 ?= "${S}/ci/jailhouse-config-k3.h"

JH_CELL_FILES ?= "*.cell"
JH_CELL_FILES_k3 ?= "k3-*.cell"

JH_INMATE_DTB ?= ""
JH_INMATE_DTB_am65xx ?= "inmate-k3-am654-idk.dtb"
JH_INMATE_DTB_j7-evm ?= "inmate-k3-j721e-evm.dtb"

JH_LINUX_DEMO_CELL ?= ""
JH_LINUX_DEMO_CELL_am65xx ?= "k3-am654-idk-linux-demo.cell"
JH_LINUX_DEMO_CELL_j7-evm ?= "k3-j721e-evm-linux-demo.cell"

JH_SYSCONFIG_CELL ?= ""
JH_SYSCONFIG_CELL_am65xx ?= "k3-am654-idk.cell"
JH_SYSCONFIG_CELL_j7-evm ?= "k3-j721e-evm.cell"

JH_CMDLINE ?= ""
JH_CMDLINE_k3 ?= "console=ttyS1,115200n8 root=/dev/mmcblk0p2 rw rootfstype=ext4 rootwait"
JH_CMDLINE_j7-evm ?= "console=ttyS1,115200n8 root=/dev/mmcblk0p2 rw rootfstype=ext4 rootwait earlycon=ns16550a,mmio32,0x2810000"

do_configure() {
	if [ -d ${STAGING_DIR_HOST}/${CELLCONF_DIR} ]; 
	then
		cp ${STAGING_DIR_HOST}/${CELLCONF_DIR}/*.c ${S}/configs/
	fi

	cp -av ${JH_CONFIG} ${S}/include/jailhouse/config.h
}

USER_SPACE_CFLAGS = '${CFLAGS} -DLIBEXECDIR=\\\"${libexecdir}\\\" \
                    -DJAILHOUSE_VERSION=\\\"$JAILHOUSE_VERSION\\\" \
                    -Wall -Wextra -Wmissing-declarations -Wmissing-prototypes -Werror \
                    -I../driver'

TOOLS_SRC_DIR = "${S}/tools"
TOOLS_OBJ_DIR = "${S}/tools"

do_compile() {
	oe_runmake V=1 ARCH=${JH_ARCH} CROSS_COMPILE=${TARGET_PREFIX} KDIR=${STAGING_KERNEL_BUILDDIR}
}

do_install() {
	oe_runmake ARCH=${JH_ARCH} CROSS_COMPILE=${TARGET_PREFIX} KDIR=${STAGING_KERNEL_BUILDDIR} DESTDIR=${D} install

	install -d ${D}${CELL_DIR}
	install -m 0644 ${B}/configs/${JH_ARCH}/${JH_CELL_FILES} ${D}${CELL_DIR}/

	install -d ${D}${INMATES_DIR}
	install -m 0644 ${B}/inmates/demos/${JH_ARCH}/*.bin ${D}${INMATES_DIR}

	install -d ${D}/boot

	if [ -n "${JH_INMATE_DTB}" -a -n "${JH_LINUX_DEMO_CELL}" ]; then
		cd ${TOOLS_SRC_DIR}

		echo "#! /bin/sh" > ${D}${JH_DATADIR}/linux-demo.sh
		echo "jailhouse enable ${CELL_DIR}/${JH_SYSCONFIG_CELL}" >> ${D}${JH_DATADIR}/linux-demo.sh
		./jailhouse-cell-linux -w ${D}${JH_DATADIR}/${JH_INMATE_DTB} \
			-a ${JH_ARCH} -c "${JH_CMDLINE}" \
			-d ../configs/${JH_ARCH}/dts/${JH_INMATE_DTB} \
			${D}${CELL_DIR}/${JH_LINUX_DEMO_CELL} \
			${DEPLOY_DIR_IMAGE}/Image \
			| tr -cd '\11\12\15\40-\176' \
			>> ${D}${JH_DATADIR}/linux-demo.sh

		sed -i -e 's,^Modified device tree written.*,,g' ${D}${JH_DATADIR}/linux-demo.sh
		sed -i -e 's,\${D},,g' ${D}${JH_DATADIR}/linux-demo.sh
		sed -i -e 's, linux-loader.bin, ${JH_EXEC_DIR}/linux-loader.bin,g' ${D}${JH_DATADIR}/linux-demo.sh
		sed -i -e 's,\${DEPLOY_DIR_IMAGE},/boot,g' ${D}${JH_DATADIR}/linux-demo.sh
		sed -i -e '/^\s*$/d' ${D}${JH_DATADIR}/linux-demo.sh
		chmod +x ${D}${JH_DATADIR}/linux-demo.sh
	fi
}

PACKAGE_BEFORE_PN = "kernel-module-jailhouse"
FILES_${PN} = "${base_libdir}/firmware ${libexecdir} ${sbindir} ${JH_DATADIR} /boot"

INSANE_SKIP_${PN} = "ldflags"

KERNEL_MODULE_AUTOLOAD += "jailhouse"

# Any extra cells/inmates from external recipes/packages
CELLS = ""

python __anonymous () {
    d.appendVarFlag('do_install', 'depends', ' virtual/kernel:do_deploy')
    initrd = d.getVar('INITRAMFS_IMAGE', True)
    if initrd:
        d.appendVarFlag('do_install', 'depends', ' ${INITRAMFS_IMAGE}:do_image_complete')

    # Setup DEPENDS and RDEPENDS to included cells
    cells = d.getVar('CELLS', True) or ""
    for cell in cells.split():
        d.appendVar('DEPENDS', ' ' + cell)
        d.appendVar('RDEPENDS_${PN}', ' ' + cell)
}
