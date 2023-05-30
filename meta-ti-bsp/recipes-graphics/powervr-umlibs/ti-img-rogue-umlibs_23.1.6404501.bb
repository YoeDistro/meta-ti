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
SRCREV = "bfacba50b004d0e93ee1f8b42d2217131453f5fd"
S = "${WORKDIR}/git/targetfs/${TARGET_PRODUCT}/${PVR_WS}/${PVR_BUILD}"

TARGET_PRODUCT:j721e = "j721e_linux"
TARGET_PRODUCT:j721s2 = "j721s2_linux"
TARGET_PRODUCT:j784s4 = "j784s4_linux"
TARGET_PRODUCT:am62xx = "am62_linux"
PVR_BUILD = "release"
PVR_WS = "lws-generic"

RDEPENDS:${PN} = " \
    libdrm \
    ti-img-rogue-driver \
    ${PN}-firmware \
"

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', 'true', 'false', d)}; then
        mv ${D}/lib/firmware ${D}${nonarch_base_libdir}
        rmdir ${D}/lib
    fi
}

PACKAGES = " \
    libgles1-rogue libgles1-rogue-dev \
    libgles2-rogue libgles2-rogue-dev \
    libgles3-rogue libgles3-rogue-dev \
    libvk-rogue libvk-rogue-dev \
    libopencl-rogue libopencl-rogue-dev \
    libopencl-rogue-tools \
    ${PN}-tools \
    ${PN}-firmware \
    ${PN}-dev \
    ${PN} \
"

# Inject variables so that packages don't get Debian-renamed (which would
# remove the -rogue suffix), but don't RPROVIDEs/RCONFLICTs on the generic
# libgl name to prevent colliding with swrast libs
python __anonymous() {
    suffix = ""
    if "-native" in d.getVar("PN"):
        suffix = "-native"
    for p in (("vulkan", "libvk",),
              ("gles", "libgles1", "libglesv1-cm1"),
              ("gles", "libgles2", "libglesv2-2"),
              ("gles", "libgles3",),
              ("opencl", "libopencl",)):
        mlprefix = d.getVar("MLPREFIX")
        fullp = mlprefix + p[1] + "-rogue" + suffix
        mlprefix = d.getVar("MLPREFIX")
        pkgs = " " + " ".join(mlprefix + x + suffix for x in p[1:])
        d.setVar("DEBIAN_NOAUTONAME:" + fullp, "1")
        d.appendVar("RRECOMMENDS:" + fullp, " ${MLPREFIX}ti-img-rogue-umlibs" + suffix)

        # For -dev, the first element is both the Debian and original name
        fullp = mlprefix + p[1] + "-rogue-dev" + suffix
        pkgs = " " + mlprefix + p[1] + "-dev" + suffix
        d.setVar("DEBIAN_NOAUTONAME:" + fullp, "1")
}

# gles specific shared objects
FILES:libgles1-rogue = "${libdir}/libGLESv1*.so.*"
FILES:libgles1-rogue-dev = "${libdir}/libGLESv1*.so"
FILES:libgles2-rogue = "${libdir}/libGLESv2*.so.*"
FILES:libgles2-rogue-dev = "${libdir}/libGLESv2*.so"
RDEPENDS:libgles1-rogue += "mesa-megadriver"
RDEPENDS:libgles2-rogue += "mesa-megadriver"
RDEPENDS:libgles3-rogue-dev += "libgles2-rogue-dev"

# vulkan specific shared objects and configs
FILES:libvk-rogue = "${libdir}/libVK_IMG.so.* ${datadir}/vulkan"
FILES:libvk-rogue-dev = "${libdir}/libVK_IMG.so"
RDEPENDS:libvk-rogue += "vulkan-loader libx11-xcb wayland libdrm"

# opencl specific shared objects and configs
FILES:libopencl-rogue = "${libdir}/libPVROCL.so.* ${sysconfdir}/OpenCL"
FILES:libopencl-rogue-dev = "${libdir}/libPVROCL.so"
RDEPENDS:libopencl-rogue += "opencl-icd-loader"
RRECOMMENDS:libopencl-rogue += "libopencl-rogue-tools"
FILES:libopencl-rogue-tools += "${bindir}/ocl*"

# optional tools and tests
FILES:${PN}-tools = "${bindir}/"
RDEPENDS:${PN}-tools = "python3-core ${PN}"

# required firmware
FILES:${PN}-firmware = "${base_libdir}/firmware/*"
INSANE_SKIP:${PN}-firmware += "arch"

RRECOMMENDS:${PN} += " \
    ${@bb.utils.contains("DISTRO_FEATURES", "opengl", "libgles1-rogue libgles2-rogue", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "vulkan x11 wayland", "libvk-rogue", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "opencl", "libopencl-rogue", "", d)} \
    ${PN}-tools \
"

INSANE_SKIP:${PN} += "already-stripped dev-so"
