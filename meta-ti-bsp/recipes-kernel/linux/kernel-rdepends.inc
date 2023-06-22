# Pull in the devicetree files into the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

# Add run-time dependency for PM firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:ti33x = " amx3-cm3"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:ti43x = " amx3-cm3"

# Add run-time dependency for VPE VPDMA firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:dra7xx = " vpdma-fw"

# Add run-time dependency for Goodix firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:dra7xx = " goodix-fw"

# Add run-time dependency for PRU Ethernet firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am57xx = " prueth-fw prusw-fw pruhsr-fw pruprp-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:ti43x = " prueth-fw pruhsr-fw pruprp-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:ti33x = " prueth-fw pruhsr-fw pruprp-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am65xx = " prueth-fw-am65x"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am65xx-evm = " prueth-fw-am65x-sr2 pruhsr-fw-am65x-sr2 prusw-fw-am65x-sr2"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am64xx = " prueth-fw-am65x-sr2 pruhsr-fw-am65x-sr2 prusw-fw-am65x-sr2"

# Add run-time dependency for Cadence MHDP firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j721e = " cadence-mhdp-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j721s2 = " cadence-mhdp-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j784s4 = " cadence-mhdp-fw"

# Add run-time dependency for Video Decoding firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j721e = " vxd-dec-fw"

# Add run-time dependency for Chips&Media Wave521 firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j721s2 = " cnm-wave-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j784s4 = " cnm-wave-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am62axx = " cnm-wave-fw"

# Add run-time dependency for TI ETH firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j721e = " ti-eth-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j7200 = " ti-eth-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j784s4 = " ti-eth-fw"
