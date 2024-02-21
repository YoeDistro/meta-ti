require ti-jailhouse.inc

RDEPENDS:${PN} += "\
        python3-curses\
        python3-datetime\
        python3-mmap\
"

JH_CELL_FILES ?= "*.cell"
JH_CELL_FILES:k3 ?= "k3-*.cell"
JH_CELL_FILES:am62xx ?= "k3-am625-*.cell"
JH_CELL_FILES:am62pxx ?= "k3-am62p5-*.cell"

JH_INMATE_DTB ?= ""
JH_INMATE_DTB:am62xx ?= "inmate-k3-am625-sk.dtb"
JH_INMATE_DTB:am65xx ?= "inmate-k3-am654-idk.dtb"
JH_INMATE_DTB:am62pxx ?= "inmate-k3-am62p5-sk.dtb"
JH_INMATE_DTB:j7 ?= "inmate-k3-j721e-evm.dtb"
JH_INMATE_DTB:j7200-evm ?= "inmate-k3-j7200-evm.dtb"

JH_LINUX_DEMO_CELL ?= ""
JH_LINUX_DEMO_CELL:am62xx ?= "k3-am625-sk-linux-demo.cell"
JH_LINUX_DEMO_CELL:am65xx ?= "k3-am654-idk-linux-demo.cell"
JH_LINUX_DEMO_CELL:am62pxx ?= "k3-am62p5-sk-linux-demo.cell"
JH_LINUX_DEMO_CELL:j7 ?= "k3-j721e-evm-linux-demo.cell"
JH_LINUX_DEMO_CELL:j7200-evm ?= "k3-j7200-evm-linux-demo.cell"

INITRAMFS_IMAGE ?= ""
JH_RAMFS_IMAGE ?= "${INITRAMFS_IMAGE}"

JH_CMDLINE ?= ""
JH_CMDLINE:am62xx ?= "console=ttyS3,115200n8 earlycon=ns16550a,mmio32,0x02810000"
JH_CMDLINE:am62pxx ?= "console=ttyS1,115200n8"
JH_CMDLINE:am65xx ?= "console=ttyS1,115200n8"
JH_CMDLINE:j7 ?= "console=ttyS3,115200n8"
JH_CMDLINE:j7200-evm ?= "console=ttyS3,115200n8"

do_install() {

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

	rm ${D}${JH_DATADIR}/root-cell-config.c.tmpl
	rm ${D}${JH_DATADIR}/jailhouse-config-collect.tmpl
}

PACKAGE_BEFORE_PN = "kernel-module-jailhouse pyjailhouse ${PN}-tools"

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
    /boot/* \
    /usr/libexec \
    /usr/sbin/* \
    /usr/libexec/* \
    /usr/share/* \
    /lib/firmware/* \
"
