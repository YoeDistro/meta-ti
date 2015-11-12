SUMMARY = "Linux Kernel Selftests"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "https://www.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz"

SRC_URI[md5sum] = "fe9dc0f6729f36400ea81aa41d614c37"
SRC_URI[sha256sum] = "caf51f085aac1e1cea4d00dbbf3093ead07b551fc07b31b2a989c05f8ea72d9f"

S = "${WORKDIR}/linux-${PV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "virtual/kernel popt"

inherit kernel-arch

TARGETS = "cpu-hotplug efivarfs exec firmware ftrace kcmp memfd memory-hotplug \
	mount mqueue net ptrace size sysctl timers user vm"

# Arch specific tests
TARGETS_append_x86 = " breakpoints ipc x86"
TARGETS_append_x86-64 = " breakpoints ipc x86"
TARGETS_append_powerpc = " powerpc"
TARGETS_append_powerpc64 = " powerpc"

EXTRA_OEMAKE += "-C tools/testing/selftests TARGETS="${TARGETS}" INSTALL_PATH=${D}${bindir}/kselftests CC="${CC}""

# Their Makefiles are so sloppy, let's clean up a bit
do_configure () {
	sed "s|^CC := .*||g" -i ${S}/tools/testing/selftests/lib.mk
	sed "s|^CC = .*||g" -i ${S}/tools/testing/selftests/timers/Makefile
	sed "s|^CC = .*||g" -i ${S}/tools/testing/selftests/memfd/Makefile
	sed "s|^CC := .*||g" -i ${S}/tools/testing/selftests/powerpc/switch_endian/Makefile
	sed "s|gcc|\$(CC)|g" -i ${S}/tools/testing/selftests/breakpoints/Makefile
	sed "s|^LDFLAGS += -lrt -lpthread|LDLIBS += -lrt -lpthread|g" -i ${S}/tools/testing/selftests/timers/Makefile
}

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install
}

PACKAGE_BEFORE_PN = "${PN}-breakpoints ${PN}-cpu-hotplug ${PN}-efivarfs ${PN}-exec ${PN}-firmware ${PN}-ftrace \
	${PN}-ipc ${PN}-kcmp ${PN}-memfd ${PN}-memory-hotplug ${PN}-mount ${PN}-mqueue ${PN}-net ${PN}-powerpc \
	${PN}-ptrace ${PN}-size ${PN}-sysctl ${PN}-timers ${PN}-user ${PN}-vm ${PN}-x86"

FILES_${PN}-breakpoints = "${bindir}/kselftests/breakpoints"
FILES_${PN}-cpu-hotplug = "${bindir}/kselftests/cpu-hotplug"
FILES_${PN}-efivarfs = "${bindir}/kselftests/efivarfs"
FILES_${PN}-exec = "${bindir}/kselftests/exec"
FILES_${PN}-firmware = "${bindir}/kselftests/firmware"
FILES_${PN}-ftrace = "${bindir}/kselftests/ftrace"
FILES_${PN}-ipc = "${bindir}/kselftests/ipc"
FILES_${PN}-kcmp = "${bindir}/kselftests/kcmp"
FILES_${PN}-memfd = "${bindir}/kselftests/memfd"
FILES_${PN}-memory-hotplug = "${bindir}/kselftests/memory-hotplug"
FILES_${PN}-mount = "${bindir}/kselftests/mount"
FILES_${PN}-mqueue = "${bindir}/kselftests/mqueue"
FILES_${PN}-net = "${bindir}/kselftests/net"
FILES_${PN}-powerpc = "${bindir}/kselftests/powerpc"
FILES_${PN}-ptrace = "${bindir}/kselftests/ptrace"
FILES_${PN}-size = "${bindir}/kselftests/size"
FILES_${PN}-sysctl = "${bindir}/kselftests/sysctl"
FILES_${PN}-timers = "${bindir}/kselftests/timers"
FILES_${PN}-user = "${bindir}/kselftests/user"
FILES_${PN}-vm = "${bindir}/kselftests/vm"
FILES_${PN}-x86 = "${bindir}/kselftests/x86"
FILES_${PN}-dbg += "${bindir}/kselftests/*/.debug"

RDEPENDS_${PN}-cpu-hotplug += "bash"
RDEPENDS_${PN}-efivarfs += "bash"
RDEPENDS_${PN}-memory-hotplug += "bash"
RDEPENDS_${PN}-net += "bash"
RDEPENDS_${PN}-vm += "bash"
RDEPENDS_${PN} += "bash ${PN}-cpu-hotplug ${PN}-efivarfs ${PN}-exec ${PN}-firmware ${PN}-ftrace \
	${PN}-kcmp ${PN}-memfd ${PN}-memory-hotplug ${PN}-mount ${PN}-mqueue ${PN}-net ${PN}-ptrace \
	${PN}-size ${PN}-sysctl ${PN}-timers ${PN}-user ${PN}-vm"

RDEPENDS_${PN}_append_x86 = " ${PN}-breakpoints ${PN}-ipc ${PN}-x86"
RDEPENDS_${PN}_append_x86-64 = " ${PN}-breakpoints ${PN}-ipc ${PN}-x86"
RDEPENDS_${PN}_append_powerpc = " ${PN}-powerpc"
RDEPENDS_${PN}_append_powerpc64 = " ${PN}-powerpc"

INSANE_SKIP_${PN} = "already-stripped"
