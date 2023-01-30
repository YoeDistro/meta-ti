# PowerVR Graphics require several patches that have not made their way
# upstream yet. This allows us to build the shims we need without completely
# clobbering mesa.

BRANCH = "powervr/kirkstone/22.0"

SRC_URI = " \
    git://gitlab.freedesktop.org/StaticRocket/mesa.git;protocol=https;branch=${BRANCH} \
    file://0001-meson.build-check-for-all-linux-host_os-combinations.patch \
    file://0002-meson.build-make-TLS-ELF-optional.patch \
    file://0001-meson-misdetects-64bit-atomics-on-mips-clang.patch \
    file://0001-futex.h-Define-__NR_futex-if-it-does-not-exist.patch \
    file://0001-util-format-Check-for-NEON-before-using-it.patch \
    file://0001-Revert-egl-wayland-deprecate-drm_handle_format-and-d.patch \
"

S = "${WORKDIR}/git"

SRCREV = "44b82a7b6fa8a86243911b1b0d6a07b0fd8ecfba"

PACKAGECONFIG:append = " \
    ${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-img-rogue-driver', 'pvr', '', d)} \
    ${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gpudriver', 'ti-sgx-ddk-km', 'sgx', '', d)} \
"

PVR_DISPLAY_CONTROLLER_ALIAS ??= "tidss"
PACKAGECONFIG[pvr] = "-Dgallium-pvr-alias=${PVR_DISPLAY_CONTROLLER_ALIAS},"
PACKAGECONFIG[sgx] = "-Dgallium-sgx-alias=${PVR_DISPLAY_CONTROLLER_ALIAS},"

PV:append = "+pvr"

GALLIUMDRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'pvr', ',pvr', '', d)}"
GALLIUMDRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'sgx', ',sgx', '', d)}"

do_install:append () {
    # remove pvr custom pkgconfig
    rm -rf ${D}${datadir}/pkgconfig
}

RRECOMMENDS:mesa-megadriver:class-target:append = "${@d.getVar('PREFERRED_PROVIDER_virtual/gpudriver')}"
