DESCRIPTION = "Userspace setup for beaglebone capes"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r4"

inherit allarch

SRC_URI = "file://cape.service \
           file://cape.sh \
           file://cape-stop.sh \
          "

do_install() {
	install -d ${D}${base_libdir}/systemd/system/
	install -m 0644 ${UNPACKDIR}/cape.service ${D}${base_libdir}/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants
	ln -sf ../cape.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	install -d ${D}${bindir}
	install -m 0755 ${UNPACKDIR}/cape*.sh ${D}${bindir}
}

FILES:${PN} += "${base_libdir}/systemd/system"
