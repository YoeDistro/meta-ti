# Pull in the devicetree files into the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

# Add run-time dependency for PM firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:ti33x = " amx3-cm3"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:ti43x = " amx3-cm3"

# Add run-time dependency for VPE VPDMA firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:dra7xx = " vpdma-fw"

# Add run-time dependency for Goodix firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:dra7xx = " goodix-fw"

# Install boot-monitor skern file into /boot dir of rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:keystone = " boot-monitor"

# Install ti-sci-fw into /boot dir of rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:k2g = " ti-sci-fw"

# Add run-time dependency for SerDes firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:keystone = " serdes-fw"

# Add run-time dependency for QMSS PDSP firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:keystone = " qmss-pdsp-fw"

# Add run-time dependency for NETCP PA firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:k2hk = " netcp-pa-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:k2e = " netcp-pa-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:k2l = " netcp-pa-fw"

# Add run-time dependency for PRU Ethernet firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am57xx-evm = " prueth-fw prusw-fw pruhsr-fw pruprp-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am57xx-hs-evm = " prueth-fw prusw-fw pruhsr-fw pruprp-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:ti43x = " prueth-fw pruhsr-fw pruprp-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:ti33x = " prueth-fw pruhsr-fw pruprp-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am65xx = " prueth-fw-am65x"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am65xx-evm = " prueth-fw-am65x-sr2 prusw-fw-am65x-sr2"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am64xx-evm = " prueth-fw-am65x-sr2"

# Add run-time dependency for Cadence MHDP firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j7 = " cadence-mhdp-fw"

# Add run-time dependency for Video Decoding firmware to the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:j7 = " vxd-dec-fw"
