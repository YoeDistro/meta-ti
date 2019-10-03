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
SRCREV = "b3a68ac89d7ed955546eae91954b184ec1583ce3"
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
inherit module pythonnative bash-completion deploy setuptools

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

INITRAMFS_IMAGE ?= ""
JH_RAMFS_IMAGE ?= "${INITRAMFS_IMAGE}"

JH_CMDLINE ?= ""
JH_CMDLINE_k3 ?= "console=ttyS1,115200n8"

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

EXTRA_OEMAKE = "ARCH=${JH_ARCH} CROSS_COMPILE=${TARGET_PREFIX} CC="${CC}" KDIR=${STAGING_KERNEL_BUILDDIR}"

do_compile() {
	oe_runmake V=1
}

do_install() {
	# Install pyjailhouse python modules needed by the tools
	distutils_do_install

	# We want to install the python tools, but we do not want to use pip...
	# At least with v0.10, we can work around this with
	# 'PIP=":" PYTHON_PIP_USEABLE=yes'
	oe_runmake PIP=: PYTHON_PIP_USEABLE=yes DESTDIR=${D} install

	install -d ${D}${CELL_DIR}
	install -m 0644 ${B}/configs/${JH_ARCH}/${JH_CELL_FILES} ${D}${CELL_DIR}/

	install -d ${D}${INMATES_DIR}
	install -m 0644 ${B}/inmates/demos/${JH_ARCH}/*.bin ${D}${INMATES_DIR}

	install -d ${D}/boot
	if [ -n "${JH_RAMFS_IMAGE}" ]
	then
		if [ -f ${DEPLOY_DIR_IMAGE}/${JH_RAMFS_IMAGE}-${MACHINE}.cpio ]
		then
			install -m 0644 ${DEPLOY_DIR_IMAGE}/${JH_RAMFS_IMAGE}-${MACHINE}.cpio ${D}/boot
		else
			bberror "Could not find JH_RAMFS_IMAGE (${JH_RAMFS_IMAGE}-${MACHINE}.cpio)!"
			bberror "Please make sure that \"cpio\" is in IMAGE_FSTYPES."
		fi
	fi

	if [ -n "${JH_INMATE_DTB}" -a -n "${JH_LINUX_DEMO_CELL}" ]; then
		cd ${TOOLS_SRC_DIR}

		echo "#! /bin/sh" > ${D}${JH_DATADIR}/linux-demo.sh
		echo "jailhouse enable ${CELL_DIR}/${JH_SYSCONFIG_CELL}" >> ${D}${JH_DATADIR}/linux-demo.sh
		./jailhouse-cell-linux -w ${D}${JH_DATADIR}/${JH_INMATE_DTB} \
			-a ${JH_ARCH} -c "${JH_CMDLINE}" \
			-d ../configs/${JH_ARCH}/dts/${JH_INMATE_DTB} \
			-i ${D}/boot/${JH_RAMFS_IMAGE}-${MACHINE}.cpio \
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

PACKAGE_BEFORE_PN = "kernel-module-jailhouse pyjailhouse ${PN}-tools"
FILES_${PN} = "${base_libdir}/firmware ${libexecdir} ${sbindir} ${JH_DATADIR} /boot"
FILES_pyjailhouse = "${PYTHON_SITEPACKAGES_DIR}"
FILES_${PN}-tools = "${libexecdir}/${BPN}/${BPN}-*"

RDEPENDS_${PN}-tools = "pyjailhouse python-mmap python-math python-argparse python-datetime python-curses python-compression"
RDEPENDS_pyjailhouse = "python-core python-ctypes python-fcntl python-shell"

RRECOMMENDS_${PN} = "${PN}-tools"

INSANE_SKIP_${PN} = "ldflags"

KERNEL_MODULE_AUTOLOAD += "jailhouse"

# Any extra cells/inmates from external recipes/packages
CELLS = ""

python __anonymous () {
    d.appendVarFlag('do_install', 'depends', ' virtual/kernel:do_deploy')
    ramfs = d.getVar('JH_RAMFS_IMAGE', True)
    if ramfs:
        d.appendVarFlag('do_install', 'depends', ' ${JH_RAMFS_IMAGE}:do_image_complete')

    # Setup DEPENDS and RDEPENDS to included cells
    cells = d.getVar('CELLS', True) or ""
    for cell in cells.split():
        d.appendVar('DEPENDS', ' ' + cell)
        d.appendVar('RDEPENDS_${PN}', ' ' + cell)
}
