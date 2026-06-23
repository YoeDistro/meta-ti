# PowerVR Graphics require several patches that have not made their way
# upstream yet. This allows us to build the shims we need without completely
# clobbering mesa.

require recipes-graphics/mesa/mesa-pvr-25.inc

SUMMARY += " (with PowerVR for TI parts)"

LIC_FILES_CHKSUM = "file://docs/license.rst;md5=ffe678546d4337b732cfd12262e6af11"

BRANCH = "powervr/${PV}"

SRC_URI = "git://github.com/TexasInstruments/mesa.git;protocol=https;branch=${BRANCH} \
           file://0001-meson-misdetects-64bit-atomics-on-mips-clang.patch"

SRCREV = "0cb5bad52580f156b02125f4c7121ca7198e1489"
PV = "25.2.8"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

GALLIUMDRIVERS:append = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-img-rogue-driver', ',pvr', '', d)}"
GALLIUMDRIVERS:append = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-sgx-ddk-km', ',sgx', '', d)}"

VULKAN_DRIVERS:append = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-img-rogue-driver', ',pvr', '', d)}"

PACKAGECONFIG = " \
        gallium \
        video-codecs \
        ${@bb.utils.filter('DISTRO_FEATURES', 'x11 vulkan wayland glvnd', d)} \
        ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl egl gles gbm virgl', '', d)} \
        ${@bb.utils.contains('DISTRO_FEATURES', 'opencl', 'opencl libclc gallium-llvm', '', d)} \
        ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'zink', '', d)} \
        xmlconfig \
"

GLPROVIDES = " \
    ${@bb.utils.contains('PACKAGECONFIG', 'opengl', 'virtual/libgl', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'gles', 'virtual/libgles1 virtual/libgles2 virtual/libgles3', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'virtual/egl', '', d)} \
"
PROVIDES = " \
    ${@bb.utils.contains('PACKAGECONFIG', 'glvnd', '', d.getVar('GLPROVIDES'), d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'gbm', 'virtual/libgbm', '', d)} \
    virtual/mesa \
"

BBCLASSEXTEND = "native nativesdk"

do_install:append () {
    # remove pvr custom pkgconfig
    rm -rf ${D}${datadir}/pkgconfig
}

FILES:${PN}-dev += "${datadir}/mesa/wayland-drm.xml"
FILES:mesa-vulkan-drivers += "${libdir}/libpvr_mesa_wsi.so"

RRECOMMENDS:mesa-megadriver:append:class-target = " ${@d.getVar('PREFERRED_PROVIDER_virtual/gpudriver')}"

INSANE_SKIP = "32bit-time"
