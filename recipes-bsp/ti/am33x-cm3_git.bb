DESCRIPTION = "Cortex-M3 binary blob for suspend-resume"

LICENSE = "TI-BSD"
LIC_FILES_CHKSUM = "file://License.txt;md5=7bdc54a749ab7a7dea999d25d99a41b8"

PV = "05.00.00.02"
PR = "r2"

# Make package machine specific due to different init scripts
PACKAGE_ARCH = "${MACHINE_ARCH}"

# SRCREV corresponds to tag v05.00.00.02
SRCREV = "11107db2f1e9e58ee75d4fe9cc38423c9a6e4365"
BRANCH ?= "master"

# This init script is only used for older kernels that do not support
# hotplug of the firmware.  Newer kernels do not require the initscript
# package.
INITSCRIPT_NAME = "am335x-pm-firmware-load"
INITSCRIPT_PARAMS = "defaults 96"

inherit update-rc.d

UPDATERCPN = "${PN}-initscript"

RDEPENDS_${PN}-initscript = "am33x-cm3"

SRC_URI = "git://arago-project.org/git/projects/am33x-cm3.git;protocol=git;branch=${BRANCH} \
           file://init-am33x-cm3 \
           file://init-am43x-cm3 \
          "

SCRIPT_ti33x = "init-am33x-cm3"
SCRIPT_ti43x = "init-am43x-cm3"

S = "${WORKDIR}/git"

do_compile() {
	make CROSS_COMPILE="${TARGET_PREFIX}"
}

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 bin/am335x-pm-firmware.bin ${D}${base_libdir}/firmware/

	# Install the init script to load the PM firmware at boot
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/${SCRIPT} ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

PACKAGES =+ "${PN}-initscript"

FILES_${PN} += "${base_libdir}/firmware"

FILES_${PN}-initscript = "${sysconfdir}/*"
