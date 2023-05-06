DESCRIPTION = "Userspace libraries for PowerVR Rogue GPU on TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-umlibs"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=7232b98c1c58f99e3baa03de5207e76f"

inherit bin_package

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "j721e|j721s2|j784s4|am62xx"

PR = "r2"

BRANCH = "linuxws/kirkstone/k6.1/${PV}"
SRC_URI = "git://git.ti.com/git/graphics/ti-img-rogue-umlibs.git;protocol=https;branch=${BRANCH}"
SRCREV = "452b0f50d2984171c81d5a0d3b22198177d919ad"
S = "${WORKDIR}/git/targetfs/${TARGET_PRODUCT}/${PVR_WS}/${PVR_BUILD}"

TARGET_PRODUCT:j721e = "j721e_linux"
TARGET_PRODUCT:j721s2 = "j721s2_linux"
TARGET_PRODUCT:j784s4 = "j784s4_linux"
TARGET_PRODUCT:am62xx = "am62_linux"
PVR_BUILD = "release"
PVR_WS = "lws-generic"

RDEPENDS:${PN} += "mesa-megadriver libdrm ti-img-rogue-driver"

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', 'true', 'false', d)}; then
        mv ${D}/lib/firmware ${D}${nonarch_base_libdir}
        rmdir ${D}/lib
    fi
    rm -rf "${D}/etc/init.d"
}

PACKAGES = "${PN}-vulkan ${PN}-tools ${PN}-firmware ${PN}"

FILES:${PN}-vulkan = " \
    ${datadir}/vulkan \
    ${libdir}/libVK_IMG.so* \
"
RDEPENDS:${PN}-vulkan += " \
    mesa-vulkan-drivers \
    libdrm \
    ti-img-rogue-driver \
    libx11-xcb \
    wayland \
"
INSANE_SKIP:${PN}-vulkan += " \
    already-stripped \
    dev-so \
"

FILES:${PN}-tools = "${bindir}/"
RDEPENDS:${PN}-tools = "python3-core"

FILES:${PN}-firmware = "${base_libdir}/firmware/*"
INSANE_SKIP:${PN}-firmware += "arch"

RRECOMMENDS:${PN} += "${PN}-vulkan ${PN}-tools"
RDEPENDS:${PN} += " ${PN}-firmware"

INSANE_SKIP:${PN} += "already-stripped dev-so"
