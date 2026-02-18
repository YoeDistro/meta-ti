SUMMARY = "Userspace libraries for PowerVR Rogue GPU on TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-umlibs"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7232b98c1c58f99e3baa03de5207e76f"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "j721e|j721s2|j784s4|j742s2|am62xx|am62pxx|j722s"

BRANCH = "linuxws/scarthgap/k6.18/${PV}"
SRC_URI = "git://git.ti.com/git/graphics/ti-img-rogue-umlibs.git;protocol=https;branch=${BRANCH}"
SRCREV = "2ecc98c61aedd931d8acf6fb4a1150ebf57fb178"

TARGET_PRODUCT:j721e = "j721e_linux"
TARGET_PRODUCT:j721s2 = "j721s2_linux"
TARGET_PRODUCT:j784s4 = "j784s4_linux"
TARGET_PRODUCT:j742s2 = "j784s4_linux"
TARGET_PRODUCT:am62xx = "am62_linux"
TARGET_PRODUCT:am62pxx = "am62p_linux"
TARGET_PRODUCT:j722s = "j722s_linux"
PVR_BUILD = "release"
PVR_WS = "lws-generic"

RDEPENDS:${PN} = " \
    libdrm \
    ti-img-rogue-driver \
    ${PN}-firmware \
"

PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan wayland', 'vulkan', '', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'opengl opencl', d)} \
"

PACKAGECONFIG[opengl] = ",,,,${GLES_PACKAGES}"
PACKAGECONFIG[vulkan] = ",,,,${VULKAN_PACKAGES}"
PACKAGECONFIG[opencl] = ",,,,${OPENCL_PACKAGES}"

def get_file_list(package_list_var, d):
    file_list = []
    package_list = d.getVar(package_list_var)
    prefix = f"{d.getVar('D')}/"
    if package_list:
        for package in package_list.split():
            package_file_string = d.getVar(f"FILES:{package}")
            if package_file_string:
                for package_file in package_file_string.split():
                    file_list.append(f"{prefix}{package_file}")
    return " ".join(file_list)

EXTRA_OEMAKE += 'BUILD=${PVR_BUILD} TARGET_PRODUCT=${TARGET_PRODUCT} WINDOW_SYSTEM=${PVR_WS}'

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install() {
    oe_runmake 'DESTDIR=${D}' install
    if ${@bb.utils.contains('PACKAGECONFIG', 'opengl', 'false', 'true', d)}; then
        for file in ${@get_file_list('GLES_PACKAGES',  d)}; do
            rm -rf ${file}
        done
    fi
    if ${@bb.utils.contains('PACKAGECONFIG', 'vulkan', 'false', 'true', d)}; then
        for file in ${@get_file_list('VULKAN_PACKAGES', d)}; do
            rm -rf ${file}
        done
    fi
    if ${@bb.utils.contains('PACKAGECONFIG', 'opencl', 'false', 'true', d)}; then
        for file in ${@get_file_list('OPENCL_PACKAGES', d)}; do
            rm -rf ${file}
        done
    fi
    if ${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', 'true', 'false', d)}; then
        if [ -e ${D}/lib/firmware ]; then
            mv ${D}/lib/firmware ${D}${nonarch_base_libdir}
        fi
    fi

    # clean up any empty directories
    find "${D}" -empty -type d -delete

    # fix permissions
    chown -R root:root "${D}"
}

GLES_PACKAGES = "libgles1-rogue libgles2-rogue libgles3-rogue"
VULKAN_PACKAGES = "libvk-rogue"
OPENCL_PACKAGES = "libopencl-rogue libopencl-rogue-tools"

PACKAGES = " \
    ${@bb.utils.contains('PACKAGECONFIG', 'opengl', d.getVar('GLES_PACKAGES'), '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'vulkan', d.getVar('VULKAN_PACKAGES'), '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'opencl', d.getVar('OPENCL_PACKAGES'), '', d)} \
    ${PN}-tools \
    ${PN}-firmware \
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
        d.setVar("INSANE_SKIP:" + fullp, "dev-so ldflags")
        d.appendVar("RRECOMMENDS:" + fullp, " ${MLPREFIX}ti-img-rogue-umlibs" + suffix)
}

# gles specific shared objects
FILES:libgles1-rogue = "${libdir}/libGLESv1*.so*"
FILES:libgles2-rogue = "${libdir}/libGLESv2*.so*"
RDEPENDS:libgles1-rogue += "mesa-megadriver"
RDEPENDS:libgles2-rogue += "mesa-megadriver"

# vulkan specific shared objects and configs
FILES:libvk-rogue = "${libdir}/libVK_IMG.so* ${datadir}/vulkan"
RDEPENDS:libvk-rogue += "vulkan-loader wayland libdrm"

# opencl specific shared objects and configs
FILES:libopencl-rogue = "${libdir}/libPVROCL.so* ${sysconfdir}/OpenCL"
RDEPENDS:libopencl-rogue += "opencl-icd-loader"
RRECOMMENDS:libopencl-rogue += "libopencl-rogue-tools"
FILES:libopencl-rogue-tools += "${bindir}/ocl*"
DEBIAN_NOAUTONAME:libopencl-rogue-tools = "1"
INSANE_SKIP:libopencl-rogue-tools = "ldflags"

# optional tools and tests
FILES:${PN}-tools = "${bindir}/"
RDEPENDS:${PN}-tools = "python3-core libdrm ${PN}"
INSANE_SKIP:${PN}-tools = "ldflags"

# required firmware
FILES:${PN}-firmware = "${base_libdir}/firmware/*"
INSANE_SKIP:${PN}-firmware += "arch"

# common libraries
FILES:${PN} = "${libdir}"

RRECOMMENDS:${PN} += " \
    ${PN}-tools \
"

INSANE_SKIP:${PN} += "already-stripped dev-so ldflags"
