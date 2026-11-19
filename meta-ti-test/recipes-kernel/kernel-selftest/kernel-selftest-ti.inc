# Tests not enabled:
#   openat2 has buildpath errors

TEST_LIST += "\
    arm64 \
    breakpoints \
    capabilities \
    cgroup \
    clone3 \
    core \
    cpu-hotplug \
    cpufreq \
    efivarfs \
    exec \
    filesystems \
    filesystems/binderfs \
    filesystems/epoll \
    firmware \
    fpu \
    ftrace \
    futex \
    gpio \
    intel_pstate \
    ipc \
    kcmp \
    kvm \
    livepatch \
    membarrier \
    memfd \
    mqueue \
    mincore \
    net \
    net/mptcp \
    ptrace \
    resq \
    rtc \
    seccomp \
    sigaltstack \
    size \
    tc-testing \
    timers \
    tmpfs \
    tpm2 \
    user_events \
    vDSO \
    watchdog \
"

TEST_LIST:append:bsp-ti-6_18 = " \
    pci_endpoint \
"

INSANE_SKIP:${PN} += "staticdev textrel"

SRC_URI:remove:bsp-ti-6_6 = "\
    file://0001-selftests-filelock-fix-the-test-plan-count-in-ofdloc.patch \
    file://0001-selftests-cgroup-memcontrol-keep-anon-page-touches-f.patch \
    file://0001-selftests-filesystems-idmapped_tmpfile-chown-the-lay.patch \
    file://0001-selftests-core-fix-unshare_test-with-large-fs.nr_ope.patch \
    file://0001-selftests-proc-proc-empty-vm-treat-pkey_alloc-ENOSPC.patch \
    file://0001-selftests-gpio-expect-gpio-mockup-to-reject-an-empty.patch \
    file://0001-selftests-gpio-skip-the-gpio-cdev-uaf-v1-tests-witho.patch \
    file://0001-selftests-lsm-check-lsm_get_self_attr-errno-before-a.patch \
    file://0001-selftests-lsm-skip-correct_lsm_list_modules-without-.patch \
    file://0001-selftests-cgroup-skip-the-cpu.max-tests-without-CONF.patch \
    file://0001-selftests-kvm-skip-nx_huge_pages_test.sh-without-KVM.patch \
    file://0001-selftests-net-udpgro_fwd-don-t-hang-when-the-iperf3-.patch \
    file://0001-selftests-net-udpgro_fwd-skip-the-UDP-tunnel-tests-w.patch \
    file://0001-selftests-net-icmp-skip-without-tcpdump.patch \
"
SRC_URI:remove:bsp-ti-6_12 = "\
    file://0001-selftests-filelock-fix-the-test-plan-count-in-ofdloc.patch \
    file://0001-selftests-cgroup-memcontrol-keep-anon-page-touches-f.patch \
    file://0001-selftests-filesystems-idmapped_tmpfile-chown-the-lay.patch \
    file://0001-selftests-gpio-expect-gpio-mockup-to-reject-an-empty.patch \
    file://0001-selftests-gpio-skip-the-gpio-cdev-uaf-v1-tests-witho.patch \
    file://0001-selftests-cgroup-skip-the-cpu.max-tests-without-CONF.patch \
    file://0001-selftests-kvm-skip-nx_huge_pages_test.sh-without-KVM.patch \
    file://0001-selftests-net-udpgro_fwd-don-t-hang-when-the-iperf3-.patch \
"
SRC_URI:remove:bsp-ti-6_18 = "\
    file://0001-selftests-filelock-fix-the-test-plan-count-in-ofdloc.patch \
    file://0001-selftests-cgroup-memcontrol-keep-anon-page-touches-f.patch \
    file://0001-selftests-filesystems-idmapped_tmpfile-chown-the-lay.patch \
    file://0001-selftests-core-fix-unshare_test-with-large-fs.nr_ope.patch \
    file://0001-selftests-proc-proc-empty-vm-treat-pkey_alloc-ENOSPC.patch \
    file://0001-selftests-proc-read-tolerate-tasks-exiting-during-th.patch \
    file://0001-selftests-sched-install-cs_prctl_test-from-the-outpu.patch \
    file://0001-selftests-sched-skip-cs_prctl_test-when-core-schedul.patch \
    file://0001-selftests-gpio-expect-gpio-mockup-to-reject-an-empty.patch \
    file://0001-selftests-gpio-skip-the-gpio-cdev-uaf-v1-tests-witho.patch \
    file://0001-selftests-splice-skip-the-kernfs-checks-without-test.patch \
    file://0001-selftests-syscall_user_dispatch-build-sud_benchmark-.patch \
    file://0001-selftests-lsm-check-lsm_get_self_attr-errno-before-a.patch \
    file://0001-selftests-lsm-skip-correct_lsm_list_modules-without-.patch \
    file://0001-selftests-cgroup-skip-the-cpu.max-tests-without-CONF.patch \
    file://0001-selftests-filesystems-binderfs-skip-binderfs_stress-.patch \
    file://0001-selftests-kvm-skip-nx_huge_pages_test.sh-without-KVM.patch \
    file://0001-selftests-watchdog-skip-when-there-is-no-watchdog-de.patch \
    file://0001-selftests-net-udpgro_fwd-don-t-hang-when-the-iperf3-.patch \
    file://0001-selftests-net-udpgro_fwd-skip-the-UDP-tunnel-tests-w.patch \
    file://0001-selftests-net-icmp-skip-without-tcpdump.patch \
"
SRC_URI:remove:bsp-bb_org-6_6 = "\
    file://0001-selftests-filelock-fix-the-test-plan-count-in-ofdloc.patch \
    file://0001-selftests-cgroup-memcontrol-keep-anon-page-touches-f.patch \
    file://0001-selftests-filesystems-idmapped_tmpfile-chown-the-lay.patch \
    file://0001-selftests-core-fix-unshare_test-with-large-fs.nr_ope.patch \
    file://0001-selftests-proc-proc-empty-vm-treat-pkey_alloc-ENOSPC.patch \
    file://0001-selftests-gpio-expect-gpio-mockup-to-reject-an-empty.patch \
    file://0001-selftests-gpio-skip-the-gpio-cdev-uaf-v1-tests-witho.patch \
    file://0001-selftests-lsm-check-lsm_get_self_attr-errno-before-a.patch \
    file://0001-selftests-lsm-skip-correct_lsm_list_modules-without-.patch \
    file://0001-selftests-cgroup-skip-the-cpu.max-tests-without-CONF.patch \
    file://0001-selftests-kvm-skip-nx_huge_pages_test.sh-without-KVM.patch \
    file://0001-selftests-net-udpgro_fwd-don-t-hang-when-the-iperf3-.patch \
    file://0001-selftests-net-udpgro_fwd-skip-the-UDP-tunnel-tests-w.patch \
    file://0001-selftests-net-icmp-skip-without-tcpdump.patch \
"
SRC_URI:remove:bsp-bb_org-6_12 = "\
    file://0001-selftests-filelock-fix-the-test-plan-count-in-ofdloc.patch \
    file://0001-selftests-cgroup-memcontrol-keep-anon-page-touches-f.patch \
    file://0001-selftests-filesystems-idmapped_tmpfile-chown-the-lay.patch \
    file://0001-selftests-gpio-expect-gpio-mockup-to-reject-an-empty.patch \
    file://0001-selftests-gpio-skip-the-gpio-cdev-uaf-v1-tests-witho.patch \
    file://0001-selftests-cgroup-skip-the-cpu.max-tests-without-CONF.patch \
    file://0001-selftests-kvm-skip-nx_huge_pages_test.sh-without-KVM.patch \
    file://0001-selftests-net-udpgro_fwd-don-t-hang-when-the-iperf3-.patch \
"
