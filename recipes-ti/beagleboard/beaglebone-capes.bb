DESCRIPTION = "Userspace setup for beaglebone capes"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PR = "r4"

inherit allarch

SRC_URI = "file://cape.service \
           file://cape.sh \
           file://cape-stop.sh \
          "

do_install() {
	install -d ${D}${base_libdir}/systemd/system/
	install -m 0644 ${WORKDIR}/cape.service ${D}${base_libdir}/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants
	ln -sf ../cape.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/cape*.sh ${D}${bindir}
}

FILES_${PN} += "${base_libdir}/systemd/system"
