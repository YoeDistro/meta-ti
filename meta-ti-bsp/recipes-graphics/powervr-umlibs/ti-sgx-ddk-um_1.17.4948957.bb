DESCRIPTION = "Userspace libraries for PowerVR SGX chipset on TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/omap5-sgx-ddk-um-linux"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7232b98c1c58f99e3baa03de5207e76f"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|am65xx"

PR = "r38"

BRANCH = "${PV}/mesa/glibc-2.35"

SRC_URI = " \
    git://git.ti.com/git/graphics/omap5-sgx-ddk-um-linux.git;protocol=https;branch=${BRANCH} \
"
SRCREV = "84a396a4fb379f10931421e489ac8a199d6a9f2c"

INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "defaults 8"

PACKAGECONFIG ??= "udev"
PACKAGECONFIG[udev] = "UDEV=true,,,udev"

def use_sysvinit(d):
    return d.getVar('VIRTUAL-RUNTIME_init_manager') == 'sysvinit'

inherit ${@oe.utils.ifelse(use_sysvinit(d), 'update-rc.d', '')}

TARGET_PRODUCT:ti33x = "ti335x_linux"
TARGET_PRODUCT:ti43x = "ti437x_linux"
TARGET_PRODUCT:omap-a15 = "ti572x_linux"
TARGET_PRODUCT:am65xx = "ti654x_linux"

RDEPENDS:${PN} += "libdrm"

RRECOMMENDS:${PN} += "ti-sgx-ddk-km"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT} ${PACKAGECONFIG_CONFARGS}"

do_install() {
    oe_runmake install
}

FILES:${PN} =  "${bindir}/*"
FILES:${PN} += " ${libdir}/*"
FILES:${PN} +=  "${includedir}/*"
FILES:${PN} +=  "${sysconfdir}/*"
FILES:${PN} +=  "${datadir}/*"
FILES:${PN} += "${nonarch_base_libdir}/udev/rules.d"
FILES:${PN} += "${nonarch_base_libdir}/systemd/system"

# No debug or dev packages for this recipe
PACKAGES = "${PN}"

INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "already-stripped dev-so"
