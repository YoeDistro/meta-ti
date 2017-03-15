SUMMARY = "Linux Kernel Selftests"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "https://www.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz"

SRC_URI[md5sum] = "0a68ef3615c64bd5ee54a3320e46667d"
SRC_URI[sha256sum] = "029098dcffab74875e086ae970e3828456838da6e0ba22ce3f64ef764f3d7f1a"

S = "${WORKDIR}/linux-${PV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "libcap popt rsync-native"

inherit kernel-arch

# Filter out arch specific tests
TARGETS = " \
	${@bb.utils.contains_any("TARGET_ARCH", [ "x86", "x86-64" ], "breakpoints", "", d)} \
	capabilities \
	cpu-hotplug \
	efivarfs \
	exec \
	firmware \
	ftrace \
	futex \
	${@bb.utils.contains_any("TARGET_ARCH", [ "x86", "x86-64" ], "ipc", "", d)} \
	kcmp \
	lib \
	membarrier \
	memfd \
	memory-hotplug \
	mount \
	mqueue \
	net \
	nsfs \
	${@bb.utils.contains_any("TARGET_ARCH", [ "powerpc", "powerpc64" ], "powerpc", "", d)} \
	pstore \
	ptrace \
	seccomp \
	sigaltstack \
	size \
	static_keys \
	sysctl \
	timers \
	user \
	vm \
	${@bb.utils.contains_any("TARGET_ARCH", [ "x86", "x86-64" ], "x86", "", d)} \
	zram \
"

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
	chown -R root:root ${D}
}

PACKAGE_BEFORE_PN = " \
	${PN}-breakpoints \
	${PN}-capabilities \
	${PN}-cpu-hotplug \
	${PN}-efivarfs \
	${PN}-exec \
	${PN}-firmware \
	${PN}-ftrace \
	${PN}-futex \
	${PN}-ipc \
	${PN}-kcmp \
	${PN}-lib \
	${PN}-membarrier \
	${PN}-memfd \
	${PN}-memory-hotplug \
	${PN}-mount \
	${PN}-mqueue \
	${PN}-net \
	${PN}-nsfs \
	${PN}-powerpc \
	${PN}-pstore \
	${PN}-ptrace \
	${PN}-seccomp \
	${PN}-sigaltstack \
	${PN}-size \
	${PN}-static-keys \
	${PN}-sysctl \
	${PN}-timers \
	${PN}-user \
	${PN}-vm \
	${PN}-x86 \
	${PN}-zram \
"

FILES_${PN}-breakpoints = "${bindir}/kselftests/breakpoints"
FILES_${PN}-capabilities = "${bindir}/kselftests/capabilities"
FILES_${PN}-cpu-hotplug = "${bindir}/kselftests/cpu-hotplug"
FILES_${PN}-efivarfs = "${bindir}/kselftests/efivarfs"
FILES_${PN}-exec = "${bindir}/kselftests/exec"
FILES_${PN}-firmware = "${bindir}/kselftests/firmware"
FILES_${PN}-ftrace = "${bindir}/kselftests/ftrace"
FILES_${PN}-futex = "${bindir}/kselftests/futex"
FILES_${PN}-ipc = "${bindir}/kselftests/ipc"
FILES_${PN}-kcmp = "${bindir}/kselftests/kcmp"
FILES_${PN}-lib = "${bindir}/kselftests/lib"
FILES_${PN}-membarrier = "${bindir}/kselftests/membarrier"
FILES_${PN}-memfd = "${bindir}/kselftests/memfd"
FILES_${PN}-memory-hotplug = "${bindir}/kselftests/memory-hotplug"
FILES_${PN}-mount = "${bindir}/kselftests/mount"
FILES_${PN}-mqueue = "${bindir}/kselftests/mqueue"
FILES_${PN}-net = "${bindir}/kselftests/net"
FILES_${PN}-nsfs = "${bindir}/kselftests/nsfs"
FILES_${PN}-powerpc = "${bindir}/kselftests/powerpc"
FILES_${PN}-pstore = "${bindir}/kselftests/pstore"
FILES_${PN}-ptrace = "${bindir}/kselftests/ptrace"
FILES_${PN}-seccomp = "${bindir}/kselftests/seccomp"
FILES_${PN}-sigaltstack = "${bindir}/kselftests/sigaltstack"
FILES_${PN}-size = "${bindir}/kselftests/size"
FILES_${PN}-static-keys = "${bindir}/kselftests/static_keys"
FILES_${PN}-sysctl = "${bindir}/kselftests/sysctl"
FILES_${PN}-timers = "${bindir}/kselftests/timers"
FILES_${PN}-user = "${bindir}/kselftests/user"
FILES_${PN}-vm = "${bindir}/kselftests/vm"
FILES_${PN}-x86 = "${bindir}/kselftests/x86"
FILES_${PN}-zram = "${bindir}/kselftests/zram"
FILES_${PN}-dbg += "${bindir}/kselftests/*/.debug"

RDEPENDS_${PN}-cpu-hotplug += "bash"
RDEPENDS_${PN}-efivarfs += "bash"
RDEPENDS_${PN}-memory-hotplug += "bash"
RDEPENDS_${PN}-net += "bash"
RDEPENDS_${PN}-vm += "bash"
RDEPENDS_${PN}-zram += "bash"
RDEPENDS_${PN} += "bash \
	${PN}-capabilities \
	${PN}-cpu-hotplug \
	${PN}-efivarfs \
	${PN}-exec \
	${PN}-firmware \
	${PN}-ftrace \
	${PN}-futex \
	${PN}-kcmp \
	${PN}-lib \
	${PN}-membarrier \
	${PN}-memfd \
	${PN}-memory-hotplug \
	${PN}-mount \
	${PN}-mqueue \
	${PN}-net \
	${PN}-nsfs \
	${PN}-pstore \
	${PN}-ptrace \
	${PN}-seccomp \
	${PN}-sigaltstack \
	${PN}-size \
	${PN}-static-keys \
	${PN}-sysctl \
	${PN}-timers \
	${PN}-user \
	${PN}-vm \
	${PN}-zram \
"

RDEPENDS_${PN}_append_x86 = " ${PN}-breakpoints ${PN}-ipc ${PN}-x86"
RDEPENDS_${PN}_append_x86-64 = " ${PN}-breakpoints ${PN}-ipc ${PN}-x86"
RDEPENDS_${PN}_append_powerpc = " ${PN}-powerpc"
RDEPENDS_${PN}_append_powerpc64 = " ${PN}-powerpc"

INSANE_SKIP_${PN} = "already-stripped"
