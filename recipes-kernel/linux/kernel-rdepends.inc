# Pull in the devicetree files into the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

# Add run-time dependency for PM firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_ti33x = " amx3-cm3"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_ti43x = " amx3-cm3"

# Add run-time dependency for VPE VPDMA firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_dra7xx = " vpdma-fw"

# Add run-time dependency for Goodix firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_dra7xx = " goodix-fw"

# Install boot-monitor skern file into /boot dir of rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_keystone = " boot-monitor"

# Install ti-sci-fw into /boot dir of rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_k2g = " ti-sci-fw"

# Add run-time dependency for SerDes firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_keystone = " serdes-fw"

# Add run-time dependency for QMSS PDSP firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_keystone = " qmss-pdsp-fw"

# Add run-time dependency for NETCP PA firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_k2hk = " netcp-pa-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_k2e = " netcp-pa-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_k2l = " netcp-pa-fw"

# Add run-time dependency for PRU Ethernet firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am57xx-evm = " prueth-fw prusw-fw pruhsr-fw pruprp-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am57xx-hs-evm = " prueth-fw prusw-fw pruhsr-fw pruprp-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_ti43x = " prueth-fw pruhsr-fw pruprp-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_ti33x = " prueth-fw pruhsr-fw pruprp-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am65xx = " prueth-fw-am65x"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am65xx-evm = " prueth-fw-am65x-sr2 prusw-fw-am65x-sr2"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am64xx = " prueth-fw-am65x-sr2"

# Add run-time dependency for Cadence MHDP firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j7-evm = " cadence-mhdp-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j7-hs-evm = " cadence-mhdp-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j721s2-evm = " cadence-mhdp-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j721s2-hs-evm = " cadence-mhdp-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j784s4-evm = " cadence-mhdp-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j784s4-hs-evm = " cadence-mhdp-fw"

# Add run-time dependency for Video Decoding firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j7 = " vxd-dec-fw"

# Add run-time dependency for Chips&Media Wave521 firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j721s2-evm = " cnm-wave-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j721s2-hs-evm = " cnm-wave-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j784s4-evm = " cnm-wave-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_j784s4-hs-evm = " cnm-wave-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am62axx = " cnm-wave-fw"

# Add run-time dependency for TIFS Low Power Module stub
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am62xx-evm = " tifs-lpm-stub"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am62xx-lp-evm = " tifs-lpm-stub"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am62axx = " tifs-lpm-stub"

