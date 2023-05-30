DESCRIPTION = "Userspace libraries for PowerVR SGX chipset on TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/omap5-sgx-ddk-um-linux"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://TI-Linux-Graphics-DDK-UM-Manifest.doc;md5=b17390502bc89535c86cfbbae961a2a8"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|am65xx"

PR = "r38"

BRANCH = "ti-img-sgx/kirkstone-mesa/${PV}"

SRC_URI = " \
    git://git.ti.com/git/graphics/omap5-sgx-ddk-um-linux.git;protocol=https;branch=${BRANCH} \
    file://pvrsrvkm.rules \
"
SRCREV = "bbae7217051341f515515ec190e165119102f45a"

TARGET_PRODUCT:ti33x = "ti335x"
TARGET_PRODUCT:ti43x = "ti437x"
TARGET_PRODUCT:omap-a15 = "ti572x"
TARGET_PRODUCT:am65xx = "ti654x"

INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "defaults 8"

PACKAGECONFIG ??= "udev"
PACKAGECONFIG[udev] = ",,,udev"

def use_initscript(d):
    sysvinit = bb.utils.contains('DISTRO_FEATURES', 'sysvinit', True, False, d)
    udev = bb.utils.contains('PACKAGECONFIG', 'udev', True, False, d)
    return sysvinit and not udev

inherit ${@oe.utils.ifelse(use_initscript(d), 'update-rc.d', '')}

RDEPENDS:${PN} += "libdrm"

RRECOMMENDS:${PN} += "ti-sgx-ddk-km"

S = "${WORKDIR}/git"

do_install () {
    oe_runmake install DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT}

    without_sysvinit=${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'false', 'true', d)}
    with_udev=${@bb.utils.contains('PACKAGECONFIG', 'udev', 'true', 'false', d)}

    # Delete initscript if it is not needed or would conflict with the udev rules
    if ${@oe.utils.ifelse(use_initscript(d), 'false', 'true')}; then
        rm -rf ${D}${sysconfdir}/init.d
        rmdir --ignore-fail-on-non-empty ${D}${sysconfdir}
    fi

    if $with_udev; then
        install -m644 -D ${WORKDIR}/pvrsrvkm.rules \
            ${D}${nonarch_base_libdir}/udev/rules.d/80-pvrsrvkm.rules
    fi

    chown -R root:root ${D}
}

FILES:${PN} =  "${bindir}/*"
FILES:${PN} += " ${libdir}/*"
FILES:${PN} +=  "${includedir}/*"
FILES:${PN} +=  "${sysconfdir}/*"
FILES:${PN} +=  "${datadir}/*"
FILES:${PN} += "${nonarch_base_libdir}/udev/rules.d"

INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "already-stripped"
