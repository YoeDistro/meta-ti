# PowerVR Graphics require several patches that have not made their way
# upstream yet. This allows us to build the shims we need without completely
# clobbering mesa.

require recipes-graphics/mesa/mesa-pvr.inc

SUMMARY += " (with PowerVR for TI parts)"

LIC_FILES_CHKSUM = "file://docs/license.rst;md5=63779ec98d78d823a9dc533a0735ef10"

BRANCH = "powervr/kirkstone/${PV}"

SRC_URI = " \
    git://gitlab.freedesktop.org/StaticRocket/mesa.git;protocol=https;branch=${BRANCH} \
    file://0001-meson.build-check-for-all-linux-host_os-combinations.patch \
    file://0001-meson-misdetects-64bit-atomics-on-mips-clang.patch \
    file://0001-util-format-Check-for-NEON-before-using-it.patch \
    file://0001-gbm-backend-fix-gbm-compile-without-dri.patch \
    file://0001-freedreno-pm4-Use-unsigned-instead-of-uint-to-fix-mu.patch \
    file://0001-gallium-Fix-build-with-llvm-17.patch \
    file://0001-fix-gallivm-limit-usage-of-LLVMContextSetOpaquePoint.patch \
    file://0001-fix-gallivm-fix-LLVM-include-of-Host-h-moved-to-TargetParser.patch \
    file://0001-gallium-Fix-build-with-llvm-18-and-19.patch \
"

PACKAGECONFIG:append = " \
    ${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-img-rogue-driver', 'pvr', '', d)} \
    ${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-sgx-ddk-km', 'sgx', '', d)} \
"

SRCREV = "c9f0919367589b38f5682183846de9d60eec082d"
PV = "22.3.5"

PVR_DISPLAY_CONTROLLER_ALIAS ??= "tidss"
PACKAGECONFIG[pvr] = "-Dgallium-pvr-alias=${PVR_DISPLAY_CONTROLLER_ALIAS},"
PACKAGECONFIG[sgx] = "-Dgallium-sgx-alias=${PVR_DISPLAY_CONTROLLER_ALIAS},"

PACKAGECONFIG:remove = "video-codecs"
PACKAGECONFIG[video-codecs] = ""
PACKAGECONFIG:remove = "elf-tls"
PACKAGECONFIG[elf-tls] = ""
PACKAGECONFIG:remove = "xvmc"
PACKAGECONFIG[xvmc] = ""

PACKAGE_ARCH = "${MACHINE_ARCH}"

GALLIUMDRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'pvr', ',pvr', '', d)}"
GALLIUMDRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'sgx', ',sgx', '', d)}"

VULKAN_DRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'pvr', ',pvr', '', d)}"

do_install:append () {
    # remove pvr custom pkgconfig
    rm -rf ${D}${datadir}/pkgconfig
}

FILES:${PN}-dev += "${datadir}/mesa/wayland-drm.xml"
FILES:mesa-vulkan-drivers += "${libdir}/libpvr_mesa_wsi.so"

RRECOMMENDS:mesa-megadriver:append:class-target = " ${@d.getVar('PREFERRED_PROVIDER_virtual/gpudriver')}"
