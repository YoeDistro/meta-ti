SUMMARY = "Linux Kernel Selftests"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "\
    https://www.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz \
    file://0001-selftests-lib-allow-to-override-CC-in-the-top-level-Makefile.patch \
    file://0001-selftests-timers-use-LDLIBS-to-link-against-libpthread.patch \
    file://0001-selftests-sigaltstack-fix-packaging.patch \
    file://0001-selftests-seccomp-use-LDLIBS-to-link-against-libpthread.patch \
    file://0001-selftests-gpio-use-pkg-config.patch \
    file://0001-selftests-net-use-LDLIBS-to-link-against-libnuma.patch \
    file://0001-selftests-breakpoints-allow-to-cross-compile-for-aar.patch;apply=no \
    file://0001-selftests-add-ptp-to-TARGETS.patch \
"

SRC_URI[md5sum] = "b5e7f6b9b2fe1b6cc7bc56a3a0bfc090"
SRC_URI[sha256sum] = "3c95d9f049bd085e5c346d2c77f063b8425f191460fcd3ae9fe7e94e0477dc4b"

S = "${WORKDIR}/linux-${PV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "libcap libcap-ng popt rsync-native util-linux pkgconfig-native \
"

inherit kernel-arch

EXTRA_OEMAKE += "V=1 -C ${S}/tools/testing/selftests INSTALL_PATH=${D}${bindir}/kselftests CC="${CC}" LD="${LD}""

do_compile () {
	# Make sure to install the user space API used by some tests
	# but not properly declared as a build dependency
	${MAKE} -C ${S} headers_install
	oe_runmake
}

do_install () {
	oe_runmake install
	chown -R root:root ${D}
	# fixup run_kselftest.sh due to spurious lines starting by "make[1]:"
	sed -i '/^make/d' ${D}${bindir}/kselftests/run_kselftest.sh
}

PACKAGE_BEFORE_PN = " \
	${PN}-bpf \
	${PN}-breakpoints \
	${PN}-capabilities \
	${PN}-cpu-hotplug \
	${PN}-efivarfs \
	${PN}-exec \
	${PN}-firmware \
	${PN}-ftrace \
	${PN}-futex \
	${PN}-gpio \
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
	${PN}-ptp \
	${PN}-ptrace \
	${PN}-seccomp \
	${PN}-sigaltstack \
	${PN}-size \
	${PN}-static-keys \
	${PN}-sync \
	${PN}-sysctl \
	${PN}-timers \
	${PN}-user \
	${PN}-vm \
	${PN}-x86 \
	${PN}-zram \
"

FILES_${PN}-bpf = "${bindir}/kselftests/bpf"
FILES_${PN}-breakpoints = "${bindir}/kselftests/breakpoints"
FILES_${PN}-capabilities = "${bindir}/kselftests/capabilities"
FILES_${PN}-cpu-hotplug = "${bindir}/kselftests/cpu-hotplug"
FILES_${PN}-efivarfs = "${bindir}/kselftests/efivarfs"
FILES_${PN}-exec = "${bindir}/kselftests/exec"
FILES_${PN}-firmware = "${bindir}/kselftests/firmware"
FILES_${PN}-ftrace = "${bindir}/kselftests/ftrace"
FILES_${PN}-futex = "${bindir}/kselftests/futex"
FILES_${PN}-gpio = "${bindir}/kselftests/gpio"
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
FILES_${PN}-ptp = "${bindir}/kselftests/ptp"
FILES_${PN}-ptrace = "${bindir}/kselftests/ptrace"
FILES_${PN}-seccomp = "${bindir}/kselftests/seccomp"
FILES_${PN}-sigaltstack = "${bindir}/kselftests/sigaltstack"
FILES_${PN}-size = "${bindir}/kselftests/size"
FILES_${PN}-static-keys = "${bindir}/kselftests/static_keys"
FILES_${PN}-sync = "${bindir}/kselftests/sync"
FILES_${PN}-sysctl = "${bindir}/kselftests/sysctl"
FILES_${PN}-timers = "${bindir}/kselftests/timers"
FILES_${PN}-user = "${bindir}/kselftests/user"
FILES_${PN}-vm = "${bindir}/kselftests/vm"
FILES_${PN}-x86 = "${bindir}/kselftests/x86"
FILES_${PN}-zram = "${bindir}/kselftests/zram"
FILES_${PN}-dbg += "${bindir}/kselftests/*/.debug"

# FIXME bpf target is failing to build and need to be fixed:
# In file included from test_verifier.c:23:0:
# ../../../../usr/include/linux/bpf_perf_event.h:14:17: error: field 'regs' has incomplete type
#   struct pt_regs regs;
#                  ^~~~
# make[1]: *** [test_verifier] Error 1
ALLOW_EMPTY_${PN}-bpf = "1"

# gcc 7.x fails to build seccomp
ALLOW_EMPTY_${PN}-seccomp = "1"

# FIXME net target builds most of the binaries, but reuseport_bpf_numa depends on libnuma,
# which is not availbale on ARM, failing entire test case
ALLOW_EMPTY_${PN}-net = "1"

RDEPENDS_${PN}-cpu-hotplug += "bash"
RDEPENDS_${PN}-efivarfs += "bash"
RDEPENDS_${PN}-futex += "bash ncurses"
RDEPENDS_${PN}-gpio += "bash"
RDEPENDS_${PN}-memory-hotplug += "bash"
RDEPENDS_${PN}-net += "bash"
RDEPENDS_${PN}-vm += "bash sudo"
RDEPENDS_${PN}-zram += "bash bc"
RDEPENDS_${PN} += "bash \
	${PN}-bpf \
	${PN}-capabilities \
	${PN}-cpu-hotplug \
	${PN}-efivarfs \
	${PN}-exec \
	${PN}-firmware \
	${PN}-ftrace \
	${PN}-futex \
	${PN}-gpio \
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
	${PN}-ptp \
	${PN}-ptrace \
	${PN}-seccomp \
	${PN}-sigaltstack \
	${PN}-size \
	${PN}-static-keys \
	${PN}-sync \
	${PN}-sysctl \
	${PN}-timers \
	${PN}-user \
	${PN}-vm \
	${PN}-zram \
"

RDEPENDS_${PN}_append_aarch64 = " ${PN}-breakpoints ${PN}-ipc"
RDEPENDS_${PN}_append_x86 = " ${PN}-breakpoints ${PN}-ipc ${PN}-x86"
RDEPENDS_${PN}_append_x86-64 = " ${PN}-breakpoints ${PN}-ipc ${PN}-x86"
RDEPENDS_${PN}_append_powerpc = " ${PN}-powerpc"
RDEPENDS_${PN}_append_powerpc64 = " ${PN}-powerpc"

INSANE_SKIP_${PN} = "already-stripped"
INSANE_SKIP_${PN}-exec = "ldflags"
INSANE_SKIP_${PN}-ipc = "ldflags"
INSANE_SKIP_${PN}-mount = "ldflags"
INSANE_SKIP_${PN}-vm = "ldflags"

COMPATIBLE_MACHINE_riscv64 = "(null)"
COMPATIBLE_MACHINE_riscv32 = "(null)"
