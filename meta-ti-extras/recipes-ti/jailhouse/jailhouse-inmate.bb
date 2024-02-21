require ti-jailhouse.inc

do_install() {

	install -d ${D}${bindir}
	install -m 0755 ${TOOLS_SRC_DIR}/demos/ivshmem-demo ${D}${bindir}

}

FILES:${PN} = " \
    ${bindir}/ivshmem-demo \
"
