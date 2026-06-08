SUMMARY = "initramfs support for DHCP network configuration before NFS root mount"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://83-netsetup"

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}/init.d
    install -m 0755 ${UNPACKDIR}/83-netsetup ${D}/init.d/83-netsetup
}

FILES:${PN} = "/init.d/83-netsetup"

RDEPENDS:${PN} = "\
    initramfs-framework-base \
"

RDEPENDS:${PN}:append:bsp-next = " \
    kernel-module-ti-am65-cpsw-nuss \
    kernel-module-k3-cppi-desc-pool \
    kernel-module-davinci-mdio \
    kernel-module-ti-cpsw-ale \
    kernel-module-ti-cpsw-sl \
    kernel-module-phylink \
    kernel-module-mdio-bitbang \
    kernel-module-phy-gmii-sel \
"
