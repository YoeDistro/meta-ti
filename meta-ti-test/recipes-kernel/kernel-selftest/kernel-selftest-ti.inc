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
