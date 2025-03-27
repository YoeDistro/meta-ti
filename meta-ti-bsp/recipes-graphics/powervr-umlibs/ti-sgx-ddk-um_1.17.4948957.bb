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
SYSTEMD_SERVICE:${PN} = "pvrsrvctl.service"

# Prefer udev rules over everything, but we do have init services if necessary
PACKAGECONFIG ??= "udev"
PACKAGECONFIG[udev] = "UDEV=true,UDEV=false,,udev,,sysvinit systemd"
PACKAGECONFIG[systemd] = "SYSTEMD=true,SYSTEMD=false,,,,udev sysvinit"
PACKAGECONFIG[sysvinit] = ",,,,,udev systemd"

def pick_init(d):
    packageconfig = d.getVar('PACKAGECONFIG').split()
    if 'udev' not in packageconfig:
        if d.getVar('VIRTUAL-RUNTIME_init_manager') == 'sysvinit':
            return "update-rc.d"
        return "systemd"
    return ""

inherit ${@pick_init(d)}

TARGET_PRODUCT:ti33x = "ti335x_linux"
TARGET_PRODUCT:ti43x = "ti437x_linux"
TARGET_PRODUCT:omap-a15 = "ti572x_linux"
TARGET_PRODUCT:am65xx = "ti654x_linux"

RDEPENDS:${PN} += "libdrm"

RRECOMMENDS:${PN} += "ti-sgx-ddk-km"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT} ${PACKAGECONFIG_CONFARGS}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
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
