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

COMPATIBLE_MACHINE = "am62xx"

TARGET_CC_ARCH += "${LDFLAGS}"

PV = "0.12+git${SRCPV}"
SRCREV = "e57d1eff6d55aeed5f977fe4e2acfb6ccbdd7560"
BRANCH = "master"

SRC_URI = " \
    git://github.com/siemens/jailhouse.git;protocol=https;branch=${BRANCH} \
    file://0001-configs-arm64-k3-am625-sk-Add-crypto-memory-region.patch \
    file://0002-configs-arm64-k3-am625-sk-Switch-inmate-boot-console.patch \
    file://0003-configs-arm64-k3-am625-sk-Add-VTM-memory-region.patch \
    file://0001-configs-k3-use-intx-for-bar_mask.patch \
    file://0004-configs-arm64-k3-am625-sk-Add-GPMC-memory-region.patch \
"

DEPENDS = "virtual/kernel dtc-native python3-mako-native python3-mako make-native"
RDEPENDS:${PN} += "\
	python3-curses\
	python3-datetime\
	python3-mmap\
"

require jailhouse-arch.inc
inherit module python3native bash-completion deploy setuptools3

S = "${WORKDIR}/git"
B = "${S}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(ti-soc)"

JH_DATADIR ?= "${datadir}/jailhouse"
JH_EXEC_DIR ?= "${libexecdir}/jailhouse"
CELL_DIR ?= "${JH_DATADIR}/cells"
CELLCONF_DIR ?= "${JH_DATADIR}/configs"
INMATES_DIR ?= "${JH_DATADIR}/inmates"

JH_CELL_FILES ?= "*.cell"
JH_CELL_FILES:k3 ?= "k3-*.cell"

JH_INMATE_DTB ?= ""
JH_INMATE_DTB:am62xx ?= "inmate-k3-am625-sk.dtb"
JH_INMATE_DTB:am65xx ?= "inmate-k3-am654-idk.dtb"
JH_INMATE_DTB:j7 ?= "inmate-k3-j721e-evm.dtb"
JH_INMATE_DTB:j7200-evm ?= "inmate-k3-j7200-evm.dtb"

JH_LINUX_DEMO_CELL ?= ""
JH_LINUX_DEMO_CELL:am62xx ?= "k3-am625-sk-linux-demo.cell"
JH_LINUX_DEMO_CELL:am65xx ?= "k3-am654-idk-linux-demo.cell"
JH_LINUX_DEMO_CELL:j7 ?= "k3-j721e-evm-linux-demo.cell"
JH_LINUX_DEMO_CELL:j7200-evm ?= "k3-j7200-evm-linux-demo.cell"

JH_SYSCONFIG_CELL ?= ""
JH_SYSCONFIG_CELL:am62xx ?= "k3-am625-sk.cell"
JH_SYSCONFIG_CELL:am65xx ?= "k3-am654-idk.cell"
JH_SYSCONFIG_CELL:j7 ?= "k3-j721e-evm.cell"
JH_SYSCONFIG_CELL:j7200-evm ?= "k3-j7200-evm.cell"

INITRAMFS_IMAGE ?= ""
JH_RAMFS_IMAGE ?= "${INITRAMFS_IMAGE}"

JH_CMDLINE ?= ""
JH_CMDLINE:am62xx ?= "console=ttyS3,115200n8 earlycon=ns16550a,mmio32,0x02810000"
JH_CMDLINE:am65xx ?= "console=ttyS1,115200n8"
JH_CMDLINE:j7 ?= "console=ttyS3,115200n8"
JH_CMDLINE:j7200-evm ?= "console=ttyS3,115200n8"

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

do_install() {
	# Install pyjailhouse python modules needed by the tools
	# distutils3_do_install

	# We want to install the python tools, but we do not want to use pip...
	# At least with v0.10, we can work around this with
	# 'PIP=":" PYTHON_PIP_USEABLE=yes'
	oe_runmake PIP=: PYTHON=python3 PYTHON_PIP_USEABLE=yes DESTDIR=${D} install

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
FILES:${PN} = "${base_libdir}/firmware ${libexecdir} ${sbindir} ${JH_DATADIR} /boot"
FILES:pyjailhouse = "${PYTHON_SITEPACKAGES_DIR}"
FILES:${PN}-tools = "${libexecdir}/${BPN}/${BPN}-*"

RDEPENDS:${PN}-tools = "pyjailhouse python3-mmap python3-math python3-datetime python3-curses python3-compression"
RDEPENDS:pyjailhouse = "python3-core python3-ctypes python3-fcntl python3-shell"

RRECOMMENDS:${PN} = "${PN}-tools"

INSANE_SKIP:${PN} = "ldflags"

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


FILES:${PN} = " \
    /boot \
    /usr/libexec \
    /usr/sbin/ivshmem-demo \
    /usr/sbin/jailhouse \
    /usr/libexec/jailhouse \
    /usr/libexec/jailhouse/jailhouse-config-check \
    /usr/libexec/jailhouse/jailhouse-config-collect \
    /usr/libexec/jailhouse/jailhouse-hardware-check \
    /usr/libexec/jailhouse/jailhouse-cell-stats \
    /usr/libexec/jailhouse/linux-loader.bin \
    /usr/libexec/jailhouse/jailhouse-cell-linux \
    /usr/libexec/jailhouse/jailhouse-config-create \
    /usr/share/jailhouse \
    /usr/share/jailhouse/linux-demo.sh \
    /usr/share/jailhouse/inmate-k3-am625-sk.dtb \
    /usr/share/jailhouse/inmates \
    /usr/share/jailhouse/cells \
    /usr/share/jailhouse/inmates/gic-demo.bin \
    /usr/share/jailhouse/inmates/ivshmem-demo.bin \
    /usr/share/jailhouse/inmates/uart-demo.bin \
    /usr/share/jailhouse/cells/k3-am625-sk.cell \
    /usr/share/jailhouse/cells/k3-am625-sk-inmate-demo.cell \
    /usr/share/jailhouse/cells/k3-am625-sk-linux-demo.cell \
    /lib/firmware \
    /lib/firmware/jailhouse.bin \
    /boot/tisdk-tiny-image-am62xx-evm.cpio \
"
