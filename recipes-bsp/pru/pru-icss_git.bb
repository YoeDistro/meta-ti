DESCRIPTION = "Programmable Real-time Unit Software Package"
HOMEPAGE = "http://processors.wiki.ti.com/index.php/PRU-ICSS"
LICENSE = "BSD-3-Clause & GPL-2.0 & PD"

LIC_FILES_CHKSUM = "file://PRU-Package-v4.0-Manifest.html;md5=5ea937e4ff2c924a735d42e61ad8cbe3"

inherit update-alternatives

BRANCH = "master"
SRC_URI = "git://git.ti.com/pru-software-support-package/pru-software-support-package.git;protocol=git;branch=${BRANCH}"
SRCREV = "804b548e69ef9fdf44445f6d0968d81cb8a1e7b4"

PV = "4.0.2"
PR = "r2"

require recipes-ti/includes/ti-paths.inc

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES_prepend = " \
    ${PN}-halt \
    ${PN}-rpmsg-echo \
"

RDEPENDS_${PN}_append = " \
    ${PN}-halt \
    ${PN}-rpmsg-echo \
"

DEPENDS = "ti-cgt-pru-native"

S = "${WORKDIR}/git"

export PRU_CGT = "${TI_CGT_PRU_INSTALL_DIR}"

SUBDIRS = "examples pru_cape/pru_fw lib/src labs"

PLATFORM_ti33x = "am335x"
PLATFORM_ti43x = "am437x"
PLATFORM_omap-a15 = "am572x"

do_compile() {
    for dir in ${SUBDIRS}
    do
        make -C ${S}/$dir
    done
}

do_install() {
    install -d ${D}/lib/firmware/pru
    install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU_Halt.out \
                   ${D}/lib/firmware/pru
}

do_install_append_ti33x() {
    for i in 0 1
    do
        install -m 0644 ${S}/examples/am335x/PRU_RPMsg_Echo_Interrupt${i}/gen/PRU_RPMsg_Echo_Interrupt${i}.out \
                        ${D}/lib/firmware/pru
    done
}

do_install_append_ti43x() {
    for i in 0 1
    do
        install -m 0644 ${S}/examples/am437x/PRU_RPMsg_Echo_Interrupt1_${i}/gen/PRU_RPMsg_Echo_Interrupt1_${i}.out \
                        ${D}/lib/firmware/pru
    done
}

do_install_append_omap-a15() {
    for i in 1 2
    do
        for j in 0 1
        do
            install -m 0644 ${S}/examples/am572x/PRU_RPMsg_Echo_Interrupt${i}_${j}/gen/PRU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}/lib/firmware/pru
        done
    done
}

FILES_${PN}-halt = "/lib/firmware/pru/PRU_Halt.out"
FILES_${PN}-rpmsg-echo = "/lib/firmware/pru/PRU_RPMsg_Echo_Interrupt*"

# Set up names for the firmwares
PRU_ICSS_ALTERNATIVES_ti33x    = "am335x-pru0-fw am335x-pru1-fw"
PRU_ICSS_ALTERNATIVES_ti43x    = "am437x-pru1_0-fw am437x-pru1_1-fw"
PRU_ICSS_ALTERNATIVES_omap-a15 = "am57xx-pru1_0-fw am57xx-pru1_1-fw am57xx-pru2_0-fw am57xx-pru2_1-fw"

# Set up link names for the firmwares
ALTERNATIVE_LINK_NAME[am335x-pru0-fw] = "/lib/firmware/am335x-pru0-fw"
ALTERNATIVE_LINK_NAME[am335x-pru1-fw] = "/lib/firmware/am335x-pru1-fw"

ALTERNATIVE_LINK_NAME[am437x-pru1_0-fw] = "/lib/firmware/am437x-pru1_0-fw"
ALTERNATIVE_LINK_NAME[am437x-pru1_1-fw] = "/lib/firmware/am437x-pru1_1-fw"

ALTERNATIVE_LINK_NAME[am57xx-pru1_0-fw] = "/lib/firmware/am57xx-pru1_0-fw"
ALTERNATIVE_LINK_NAME[am57xx-pru1_1-fw] = "/lib/firmware/am57xx-pru1_1-fw"
ALTERNATIVE_LINK_NAME[am57xx-pru2_0-fw] = "/lib/firmware/am57xx-pru2_0-fw"
ALTERNATIVE_LINK_NAME[am57xx-pru2_1-fw] = "/lib/firmware/am57xx-pru2_1-fw"

# Create the pru-icss-halt firmware alternatives
ALTERNATIVE_pru-icss-halt = "${PRU_ICSS_ALTERNATIVES}"

ALTERNATIVE_TARGET_pru-icss-halt[am335x-pru0-fw] = "/lib/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am335x-pru1-fw] = "/lib/firmware/pru/PRU_Halt.out"

ALTERNATIVE_TARGET_pru-icss-halt[am437x-pru1_0-fw] = "/lib/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am437x-pru1_1-fw] = "/lib/firmware/pru/PRU_Halt.out"

ALTERNATIVE_TARGET_pru-icss-halt[am57xx-pru1_0-fw] = "/lib/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am57xx-pru1_1-fw] = "/lib/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am57xx-pru2_0-fw] = "/lib/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am57xx-pru2_1-fw] = "/lib/firmware/pru/PRU_Halt.out"

ALTERNATIVE_PRIORITY_pru-icss-halt = "50"

# Craete the pru-icss-rpmsg-echo firmware alternatives
ALTERNATIVE_pru-icss-rpmsg-echo = "${PRU_ICSS_ALTERNATIVES}"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am335x-pru0-fw] = "/lib/firmware/pru/PRU_RPMsg_Echo_Interrupt0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am335x-pru1-fw] = "/lib/firmware/pru/PRU_RPMsg_Echo_Interrupt1.out"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am437x-pru1_0-fw] = "/lib/firmware/pru/PRU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am437x-pru1_1-fw] = "/lib/firmware/pru/PRU_RPMsg_Echo_Interrupt1_1.out"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am57xx-pru1_0-fw] = "/lib/firmware/pru/PRU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am57xx-pru1_1-fw] = "/lib/firmware/pru/PRU_RPMsg_Echo_Interrupt1_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am57xx-pru2_0-fw] = "/lib/firmware/pru/PRU_RPMsg_Echo_Interrupt2_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am57xx-pru2_1-fw] = "/lib/firmware/pru/PRU_RPMsg_Echo_Interrupt2_1.out"

ALTERNATIVE_PRIORITY_pru-icss-rpmsg-echo = "100"

ALLOW_EMPTY_${PN} = "1"

# This installs PRU firmware, so skip "arch" QA check
INSANE_SKIP_${PN}-halt = "arch"
INSANE_SKIP_${PN}-rpmsg-echo = "arch"
