SUMMARY = "TI Testing packagegroup"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES += " \
    ${PN}-audio \
    ${PN}-extras \
    ${PN}-graphics \
    ${PN}-multimedia \
    ${PN}-networking \
    ${PN}-storage \
    ${PN}-ti-tools \
"

TI_TEST_BASE = "\
    bc \
    bonnie++ \
    cryptodev-tests \
    devmem2 \
    dma-heap-tests \
    evtest \
    git \
    i2c-tools \
    kernel-modules \
    kernel-selftest \
    kmsxx \
    kmsxx-python \
    libdrm-tests \
    linuxptp \
    lmbench \
    lsof \
    media-ctl \
    memtester \
    nbench-byte \
    openntpd \
    ${@"optee-test" if d.getVar('OPTEEMACHINE') else ""} \
    pcitest \
    pciutils \
    perf \
    powertop \
    procps \
    rng-tools \
    rt-tests \
    rwmem \
    smcroute \
    stream \
    stress \
    stress-ng \
"

TI_TEST_BASE:remove:bsp-ti-6_18 = "pcitest"

TI_TEST_BASE:append:armv7a = " \
    cpuburn-neon \
"

TI_TEST_BASE:append:armv7ve = " \
    cpuburn-neon \
"

TI_TEST_AUDIO = "\
    alsa-utils \
    pulseaudio-misc \
"

TI_TEST_EXTRAS_OPENCL = " \
    opencl-cts \
"

TI_TEST_EXTRAS = " \
    python3-pillow \
    pytesseract \
    python3-numpy \
    python3-requests \
    python3-websocket-client \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencl', '${TI_TEST_EXTRAS_OPENCL}', '', d)} \
"

TI_TEST_EXTRAS:append:ti-soc = " \
    libsdl2-tests \
"

TI_TEST_GRAPHICS_OPENGL = " \
    piglit \
    offscreendemo \
"

TI_TEST_GRAPHICS = " \
    wayland-utils \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '${TI_TEST_GRAPHICS_OPENGL}', '', d)} \
"

TI_TEST_MULTIMEDIA = "\
    gst-devtools \
    v4l-utils \
    yavta \
"

TI_TEST_MULTIMEDIA:append:ti-soc = " \
    mpv \
    ffmpeg \
"

TI_TEST_NETWORKING = "\
    bridge-utils \
    ethtool \
    iperf3 \
    iproute2-bridge \
    iproute2-devlink \
    iproute2-tc \
    lldpd \
    mstpd \
    netperf \
    strongswan \
    tcpdump \
    xdp-tools-ti \
"

TI_TEST_STORAGE = "\
    dosfstools \
    fio \
    hdparm \
    iozone3 \
    mtd-utils \
    mtd-utils-ubifs \
"

TI_TEST_STORAGE:append:ti-soc = " \
    mtd-utils-ubifs-tests \
"

TI_TEST_TI_TOOLS = " \
    arm-benchmarks \
    arm-ddr-bandwidth \
    coremark \
    hwspinlocktest \
    input-utils \
    ltp-ddt \
    openssl-perf \
    uvc-gadget \
"

TI_TEST_TI_TOOLS:append:ti33x = " \
    omapconf \
    pru-icss \
    switch-config \
"

TI_TEST_TI_TOOLS:append:ti43x = " \
    omapconf \
    pru-icss \
    switch-config \
"

TI_TEST_TI_TOOLS:append:am57xx = " \
    omapconf \
    pru-icss \
    switch-config \
    ti-ipc-test \
"

TI_TEST_TI_TOOLS:append:k3 = " \
    k3conf \
    switch-config \
    ti-rtos-echo-test-fw \
    ti-rpmsg-char \
    ti-rpmsg-char-examples \
"

TI_TEST_TI_TOOLS:append:am62xx   = " \
    pru-icss \
"

TI_TEST_TI_TOOLS:append:am64xx   = " \
    pru-icss \
"

TI_TEST_TI_TOOLS:append:am65xx   = " \
    pru-icss \
"

# Disable due to breakage
#    viddec-test-app 
TI_TEST_TI_TOOLS:append:j721e = " \
    pru-icss \
    ufs-utils \
    videnc-test-app \
"

TI_TEST_TI_TOOLS:append:j784s4 = " \
    ufs-utils \
"

TI_TEST_TI_TOOLS:append:j742s2 = " \
    ufs-utils \
"

TI_TEST_TI_TOOLS:append:omapl138 = " \
    ti-ipc-test \
"

TI_TEST_TI_TOOLS:remove:bsp-mainline = "ti-ipc-test"
TI_TEST_TI_TOOLS:remove:bsp-next = "ti-ipc-test"

RDEPENDS:${PN}-audio = "${TI_TEST_AUDIO}"
RDEPENDS:${PN}-extras = "${TI_TEST_EXTRAS}"
RDEPENDS:${PN}-graphics = "${TI_TEST_GRAPHICS}"
RDEPENDS:${PN}-multimedia = "${TI_TEST_MULTIMEDIA}"
RDEPENDS:${PN}-networking = "${TI_TEST_NETWORKING}"
RDEPENDS:${PN}-storage = "${TI_TEST_STORAGE}"
RDEPENDS:${PN}-ti-tools = "${TI_TEST_TI_TOOLS}"

RDEPENDS:${PN} = "\
    ${TI_TEST_BASE} \
    ${PN}-networking \
    ${PN}-storage \
    ${PN}-ti-tools \
"

RDEPENDS:${PN}-extras += "\
    ${PN}-audio \
    ${PN}-graphics \
    ${PN}-multimedia \
"
