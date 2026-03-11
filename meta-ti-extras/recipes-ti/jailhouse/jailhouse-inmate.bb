require ti-jailhouse.inc

# Inmate binaries are bare-metal; no debug package needed
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_install() {

	install -d ${D}${bindir}
	install -m 0755 ${TOOLS_SRC_DIR}/demos/ivshmem-demo ${D}${bindir}

}

FILES:${PN} = " \
    ${bindir}/ivshmem-demo \
"
