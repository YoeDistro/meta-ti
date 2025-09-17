# PowerVR Graphics require several patches that have not made their way
# upstream yet. This allows us to build the shims we need without completely
# clobbering mesa.

require recipes-graphics/mesa/mesa.inc

SUMMARY += " (with PowerVR for TI parts)"

LIC_FILES_CHKSUM = "file://docs/license.rst;md5=63779ec98d78d823a9dc533a0735ef10"

BRANCH = "powervr/${PV}"

SRC_URI = " \
    git://gitlab.freedesktop.org/StaticRocket/mesa.git;protocol=https;branch=${BRANCH} \
    file://0001-meson.build-check-for-all-linux-host_os-combinations.patch \
    file://0001-meson-misdetects-64bit-atomics-on-mips-clang.patch \
    file://0001-gallium-Fix-build-with-llvm-17.patch \
    file://0001-drisw-fix-build-without-dri3.patch \
    file://0002-glxext-don-t-try-zink-if-not-enabled-in-mesa.patch \
    file://0001-gallivm-Call-StringMapIterator-from-llvm-scope.patch \
    file://0001-Update-lp_bld_misc.cpp-to-support-llvm-19.patch \
"

S = "${WORKDIR}/git"

SRCREV = "68af6a102c2298569e77d1aa8bccc1ff61438b3e"
PV = "24.0.1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

GALLIUMDRIVERS:append = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-img-rogue-driver', ',pvr', '', d)}"
GALLIUMDRIVERS:append = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-sgx-ddk-km', ',sgx', '', d)}"

VULKAN_DRIVERS:append = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-img-rogue-driver', ',pvr', '', d)}"

do_install:append () {
    # remove pvr custom pkgconfig
    rm -rf ${D}${datadir}/pkgconfig
}

FILES:${PN}-dev += "${datadir}/mesa/wayland-drm.xml"
FILES:mesa-vulkan-drivers += "${libdir}/libpvr_mesa_wsi.so"

RRECOMMENDS:mesa-megadriver:append:class-target = " ${@d.getVar('PREFERRED_PROVIDER_virtual/gpudriver')}"
