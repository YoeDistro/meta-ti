DESCRIPTION = "NETCP SA firmware for Keystone"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${NETCP_SA_FW_VERSION}"
PR = "${INC_PR}.0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "keystone"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

TARGET = "sa_mci.fw"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 ${S}/ti-keystone/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} = "${base_libdir}/firmware"
