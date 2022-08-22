DESCRIPTION = "Programmable Real-time Unit Software Package"
HOMEPAGE = "http://processors.wiki.ti.com/index.php/PRU-ICSS"
LICENSE = "BSD-3-Clause & PD"

LIC_FILES_CHKSUM = "file://PRU-Package-v6.1-Manifest.html;md5=1e37797ebe9254922f4278bb6047211c"

inherit update-alternatives

BRANCH = "master"
SRC_URI = "git://git.ti.com/git/pru-software-support-package/pru-software-support-package.git;protocol=https;branch=${BRANCH}"
SRCREV = "ae1decf0b494b2904e6157e1379691ffc468f0cf"

PV = "6.1.0"
PR = "r0"

require recipes-ti/includes/ti-paths.inc

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g|k3"
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
export PRU_SSP = "${S}"

SUBDIRS = "examples pru_cape/pru_fw lib/src labs"

PLATFORM_ti33x = "am335x"
PLATFORM_ti43x = "am437x"
PLATFORM_omap-a15 = "am572x"
PLATFORM_k2g = "k2g"
PLATFORM_am62xx = "am62x"
PLATFORM_am64xx = "am64x"
PLATFORM_am65xx = "am65x"
PLATFORM_j7 = "j721e"

do_compile() {
    for dir in ${SUBDIRS}
    do
        make -C ${S}/$dir
    done
}

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${base_libdir}/firmware/pru
    install -d ${D}${includedir}
    cp ${CP_ARGS} ${S}/include/* ${D}${includedir}
    install -d ${D}${libdir}
    install -m 0644 ${S}/lib/rpmsg_lib.lib ${D}${libdir}
}

FILES_${PN}-staticdev = "${libdir}"
FILES_${PN}-dev = "${includedir}"

do_install_append_ti33x() {
    install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU_Halt.out \
                   ${D}${base_libdir}/firmware/pru
    for i in 0 1
    do
        install -m 0644 ${S}/examples/am335x/PRU_RPMsg_Echo_Interrupt${i}/gen/PRU_RPMsg_Echo_Interrupt${i}.out \
                        ${D}${base_libdir}/firmware/pru
    done
}

do_install_append_ti43x() {
    install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU_Halt.out \
                   ${D}${base_libdir}/firmware/pru
    for i in 0 1
    do
        for j in 0 1
        do
            install -m 0644 ${S}/examples/am437x/PRU_RPMsg_Echo_Interrupt${i}_${j}/gen/PRU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru
        done
    done
}

do_install_append_omap-a15() {
    install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU_Halt.out \
                   ${D}${base_libdir}/firmware/pru
    for i in 1 2
    do
        for j in 0 1
        do
            install -m 0644 ${S}/examples/am572x/PRU_RPMsg_Echo_Interrupt${i}_${j}/gen/PRU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru
        done
    done
}

do_install_append_k2g() {
    install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU_Halt.out \
                   ${D}${base_libdir}/firmware/pru
    for i in 0 1
    do
        for j in 0 1
        do
            install -m 0644 ${S}/examples/k2g/PRU_RPMsg_Echo_Interrupt${i}_${j}/gen/PRU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru
        done
    done
}

do_install_append_am62xx() {
    install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU_Halt.out \
                   ${D}${base_libdir}/firmware/pru
    for i in 0 1
    do
        install -m 0644 ${S}/examples/${PLATFORM}/PRU_RPMsg_Echo_Interrupt${i}/gen/PRU_RPMsg_Echo_Interrupt${i}.out \
                        ${D}${base_libdir}/firmware/pru
    done
}

do_install_append_am64xx(){
    for i in 0 1
    do
        install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU${i}/PRU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
        install -m 644 ${S}/examples/${PLATFORM}/RTU_Halt/gen/RTU${i}/RTU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
        install -m 644 ${S}/examples/${PLATFORM}/TX_PRU_Halt/gen/TX_PRU${i}/TX_PRU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
    done
    for i in 0 1
    do
        for j in 0 1
        do
            install -m 0644 ${S}/examples/${PLATFORM}/PRU_RPMsg_Echo_Interrupt${j}/gen/icssg${i}/PRU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru
            install -m 0644 ${S}/examples/${PLATFORM}/RTU_RPMsg_Echo_Interrupt${j}/gen/icssg${i}/RTU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru
        done
    done
}

do_install_append_am65xx() {
    for i in 0 1 
    do
        install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU${i}/PRU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
        install -m 644 ${S}/examples/${PLATFORM}/RTU_Halt/gen/RTU${i}/RTU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
        install -m 644 ${S}/examples/${PLATFORM}/TX_PRU_Halt/gen/TX_PRU${i}/TX_PRU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
    done
    for i in 0 1 2
    do
        for j in 0 1
        do
            install -m 0644 ${S}/examples/am65x/PRU_RPMsg_Echo_Interrupt${j}/gen/icssg${i}/PRU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru
            install -m 0644 ${S}/examples/am65x/RTU_RPMsg_Echo_Interrupt${j}/gen/icssg${i}/RTU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru

        done
    done
}

do_install_append_j7() {
    for i in 0 1
    do
        install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU${i}/PRU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
        install -m 644 ${S}/examples/${PLATFORM}/RTU_Halt/gen/RTU${i}/RTU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
        install -m 644 ${S}/examples/${PLATFORM}/TX_PRU_Halt/gen/TX_PRU${i}/TX_PRU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
    done
    for i in 0 1
    do
        for j in 0 1
        do
            install -m 0644 ${S}/examples/j721e/PRU_RPMsg_Echo_Interrupt${j}/gen/icssg${i}/PRU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru
            install -m 0644 ${S}/examples/j721e/RTU_RPMsg_Echo_Interrupt${j}/gen/icssg${i}/RTU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru

        done
    done
}


FILES_${PN}-halt = "${base_libdir}/firmware/pru/PRU_Halt* ${base_libdir}/firmware/pru/RTU_Halt* ${base_libdir}/firmware/pru/TX_PRU_Halt*"
FILES_${PN}-rpmsg-echo = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt* ${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt*"

# Set up names for the firmwares
PRU_ICSS_ALTERNATIVES_ti33x    = "am335x-pru0-fw am335x-pru1-fw"
PRU_ICSS_ALTERNATIVES_ti43x    = "am437x-pru0_0-fw am437x-pru0_1-fw am437x-pru1_0-fw am437x-pru1_1-fw"
PRU_ICSS_ALTERNATIVES_omap-a15 = "am57xx-pru1_0-fw am57xx-pru1_1-fw am57xx-pru2_0-fw am57xx-pru2_1-fw"
PRU_ICSS_ALTERNATIVES_k2g      = "k2g-pru0_0-fw k2g-pru0_1-fw k2g-pru1_0-fw k2g-pru1_1-fw"
PRU_ICSS_ALTERNATIVES_am62xx   = "am62x-pru0-fw am62x-pru1-fw"
PRU_ICSS_ALTERNATIVES_am64xx   = "am64x-pru0_0-fw am64x-pru0_1-fw am64x-pru1_0-fw am64x-pru1_1-fw am64x-rtu0_0-fw am64x-rtu0_1-fw am64x-rtu1_0-fw am64x-rtu1_1-fw"
PRU_ICSS_ALTERNATIVES_am65xx   = "am65x-pru0_0-fw am65x-pru0_1-fw am65x-pru1_0-fw am65x-pru1_1-fw am65x-pru2_0-fw am65x-pru2_1-fw am65x-rtu0_0-fw am65x-rtu0_1-fw am65x-rtu1_0-fw am65x-rtu1_1-fw am65x-rtu2_0-fw am65x-rtu2_1-fw"
PRU_ICSS_ALTERNATIVES_j7       = "j7-pru0_0-fw j7-pru0_1-fw j7-pru1_0-fw j7-pru1_1-fw j7-rtu0_0-fw j7-rtu0_1-fw j7-rtu1_0-fw j7-rtu1_1-fw"

# Set up link names for the firmwares
ALTERNATIVE_LINK_NAME[am335x-pru0-fw] = "${base_libdir}/firmware/am335x-pru0-fw"
ALTERNATIVE_LINK_NAME[am335x-pru1-fw] = "${base_libdir}/firmware/am335x-pru1-fw"

ALTERNATIVE_LINK_NAME[am437x-pru0_0-fw] = "${base_libdir}/firmware/am437x-pru0_0-fw"
ALTERNATIVE_LINK_NAME[am437x-pru0_1-fw] = "${base_libdir}/firmware/am437x-pru0_1-fw"
ALTERNATIVE_LINK_NAME[am437x-pru1_0-fw] = "${base_libdir}/firmware/am437x-pru1_0-fw"
ALTERNATIVE_LINK_NAME[am437x-pru1_1-fw] = "${base_libdir}/firmware/am437x-pru1_1-fw"

ALTERNATIVE_LINK_NAME[am57xx-pru1_0-fw] = "${base_libdir}/firmware/am57xx-pru1_0-fw"
ALTERNATIVE_LINK_NAME[am57xx-pru1_1-fw] = "${base_libdir}/firmware/am57xx-pru1_1-fw"
ALTERNATIVE_LINK_NAME[am57xx-pru2_0-fw] = "${base_libdir}/firmware/am57xx-pru2_0-fw"
ALTERNATIVE_LINK_NAME[am57xx-pru2_1-fw] = "${base_libdir}/firmware/am57xx-pru2_1-fw"

ALTERNATIVE_LINK_NAME[k2g-pru0_0-fw] = "${base_libdir}/firmware/k2g-pru0_0-fw"
ALTERNATIVE_LINK_NAME[k2g-pru0_1-fw] = "${base_libdir}/firmware/k2g-pru0_1-fw"
ALTERNATIVE_LINK_NAME[k2g-pru1_0-fw] = "${base_libdir}/firmware/k2g-pru1_0-fw"
ALTERNATIVE_LINK_NAME[k2g-pru1_1-fw] = "${base_libdir}/firmware/k2g-pru1_1-fw"

ALTERNATIVE_LINK_NAME[am62x-pru0-fw] = "${base_libdir}/firmware/am62x-pru0-fw"
ALTERNATIVE_LINK_NAME[am62x-pru1-fw] = "${base_libdir}/firmware/am62x-pru1-fw"

ALTERNATIVE_LINK_NAME[am64x-pru0_0-fw] = "${base_libdir}/firmware/am64x-pru0_0-fw"
ALTERNATIVE_LINK_NAME[am64x-pru0_1-fw] = "${base_libdir}/firmware/am64x-pru0_1-fw"
ALTERNATIVE_LINK_NAME[am64x-pru1_0-fw] = "${base_libdir}/firmware/am64x-pru1_0-fw"
ALTERNATIVE_LINK_NAME[am64x-pru1_1-fw] = "${base_libdir}/firmware/am64x-pru1_1-fw"
ALTERNATIVE_LINK_NAME[am64x-rtu0_0-fw] = "${base_libdir}/firmware/am64x-rtu0_0-fw"
ALTERNATIVE_LINK_NAME[am64x-rtu0_1-fw] = "${base_libdir}/firmware/am64x-rtu0_1-fw"
ALTERNATIVE_LINK_NAME[am64x-rtu1_0-fw] = "${base_libdir}/firmware/am64x-rtu1_0-fw"
ALTERNATIVE_LINK_NAME[am64x-rtu1_1-fw] = "${base_libdir}/firmware/am64x-rtu1_1-fw"
ALTERNATIVE_LINK_NAME[am64x-txpru0_0-fw] = "${base_libdir}/firmware/am64x-txpru0_0-fw"
ALTERNATIVE_LINK_NAME[am64x-txpru0_1-fw] = "${base_libdir}/firmware/am64x-txpru0_1-fw"
ALTERNATIVE_LINK_NAME[am64x-txpru1_0-fw] = "${base_libdir}/firmware/am64x-txpru1_0-fw"
ALTERNATIVE_LINK_NAME[am64x-txpru1_1-fw] = "${base_libdir}/firmware/am64x-txpru1_1-fw"

ALTERNATIVE_LINK_NAME[am65x-pru0_0-fw] = "${base_libdir}/firmware/am65x-pru0_0-fw"
ALTERNATIVE_LINK_NAME[am65x-pru0_1-fw] = "${base_libdir}/firmware/am65x-pru0_1-fw"
ALTERNATIVE_LINK_NAME[am65x-pru1_0-fw] = "${base_libdir}/firmware/am65x-pru1_0-fw"
ALTERNATIVE_LINK_NAME[am65x-pru1_1-fw] = "${base_libdir}/firmware/am65x-pru1_1-fw"
ALTERNATIVE_LINK_NAME[am65x-pru2_0-fw] = "${base_libdir}/firmware/am65x-pru2_0-fw"
ALTERNATIVE_LINK_NAME[am65x-pru2_1-fw] = "${base_libdir}/firmware/am65x-pru2_1-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu0_0-fw] = "${base_libdir}/firmware/am65x-rtu0_0-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu0_1-fw] = "${base_libdir}/firmware/am65x-rtu0_1-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu1_0-fw] = "${base_libdir}/firmware/am65x-rtu1_0-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu1_1-fw] = "${base_libdir}/firmware/am65x-rtu1_1-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu2_0-fw] = "${base_libdir}/firmware/am65x-rtu2_0-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu2_1-fw] = "${base_libdir}/firmware/am65x-rtu2_1-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru0_0-fw] = "${base_libdir}/firmware/am65x-txpru0_0-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru0_1-fw] = "${base_libdir}/firmware/am65x-txpru0_1-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru1_0-fw] = "${base_libdir}/firmware/am65x-txpru1_0-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru1_1-fw] = "${base_libdir}/firmware/am65x-txpru1_1-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru2_0-fw] = "${base_libdir}/firmware/am65x-txpru2_0-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru2_1-fw] = "${base_libdir}/firmware/am65x-txpru2_1-fw"

ALTERNATIVE_LINK_NAME[j7-pru0_0-fw] = "${base_libdir}/firmware/j7-pru0_0-fw"
ALTERNATIVE_LINK_NAME[j7-pru0_1-fw] = "${base_libdir}/firmware/j7-pru0_1-fw"
ALTERNATIVE_LINK_NAME[j7-pru1_0-fw] = "${base_libdir}/firmware/j7-pru1_0-fw"
ALTERNATIVE_LINK_NAME[j7-pru1_1-fw] = "${base_libdir}/firmware/j7-pru1_1-fw"
ALTERNATIVE_LINK_NAME[j7-rtu0_0-fw] = "${base_libdir}/firmware/j7-rtu0_0-fw"
ALTERNATIVE_LINK_NAME[j7-rtu0_1-fw] = "${base_libdir}/firmware/j7-rtu0_1-fw"
ALTERNATIVE_LINK_NAME[j7-rtu1_0-fw] = "${base_libdir}/firmware/j7-rtu1_0-fw"
ALTERNATIVE_LINK_NAME[j7-rtu1_1-fw] = "${base_libdir}/firmware/j7-rtu1_1-fw"
ALTERNATIVE_LINK_NAME[j7-txpru0_0-fw] = "${base_libdir}/firmware/j7-txpru0_0-fw"
ALTERNATIVE_LINK_NAME[j7-txpru0_1-fw] = "${base_libdir}/firmware/j7-txpru0_1-fw"
ALTERNATIVE_LINK_NAME[j7-txpru1_0-fw] = "${base_libdir}/firmware/j7-txpru1_0-fw"
ALTERNATIVE_LINK_NAME[j7-txpru1_1-fw] = "${base_libdir}/firmware/j7-txpru1_1-fw"

# Create the pru-icss-halt firmware alternatives
ALTERNATIVE_pru-icss-halt = "${PRU_ICSS_ALTERNATIVES}"

# Only Halt firmware images are supported for the Tx_PRU cores
ALTERNATIVE_pru-icss-halt_append_am64xx = " am64x-txpru0_0-fw am64x-txpru0_1-fw am64x-txpru1_0-fw am64x-txpru1_1-fw"
ALTERNATIVE_pru-icss-halt_append_am65xx = " am65x-txpru0_0-fw am65x-txpru0_1-fw am65x-txpru1_0-fw am65x-txpru1_1-fw am65x-txpru2_0-fw am65x-txpru2_1-fw"
ALTERNATIVE_pru-icss-halt_append_j7 = " j7-txpru0_0-fw j7-txpru0_1-fw j7-txpru1_0-fw j7-txpru1_1-fw"

ALTERNATIVE_TARGET_pru-icss-halt[am335x-pru0-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am335x-pru1-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"

ALTERNATIVE_TARGET_pru-icss-halt[am437x-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am437x-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am437x-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am437x-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"

ALTERNATIVE_TARGET_pru-icss-halt[am57xx-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am57xx-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am57xx-pru2_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am57xx-pru2_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"

ALTERNATIVE_TARGET_pru-icss-halt[k2g-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[k2g-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[k2g-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[k2g-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"

ALTERNATIVE_TARGET_pru-icss-halt[am62x-pru0-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"
ALTERNATIVE_TARGET_pru-icss-halt[am62x-pru1-fw] = "${base_libdir}/firmware/pru/PRU_Halt.out"

ALTERNATIVE_TARGET_pru-icss-halt[am64x-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-rtu0_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-rtu0_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-rtu1_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-rtu1_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-txpru0_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-txpru0_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-txpru1_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am64x-txpru1_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"

ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru2_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru2_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu0_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu0_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu1_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu1_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu2_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu2_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru0_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru0_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru1_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru1_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru2_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru2_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"

ALTERNATIVE_TARGET_pru-icss-halt[j7-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-rtu0_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-rtu0_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-rtu1_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-rtu1_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-txpru0_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-txpru0_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-txpru1_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[j7-txpru1_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"


ALTERNATIVE_PRIORITY_pru-icss-halt = "50"

# Create the pru-icss-rpmsg-echo firmware alternatives
ALTERNATIVE_pru-icss-rpmsg-echo = "${PRU_ICSS_ALTERNATIVES}"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am335x-pru0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am335x-pru1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1.out"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am437x-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am437x-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am437x-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am437x-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_1.out"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am57xx-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am57xx-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am57xx-pru2_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt2_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am57xx-pru2_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt2_1.out"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[k2g-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[k2g-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[k2g-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[k2g-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_1.out"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am62x-pru0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am62x-pru1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1.out"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am64x-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am64x-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am64x-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am64x-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am64x-rtu0_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am64x-rtu0_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am64x-rtu1_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am64x-rtu1_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt1_1.out"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru2_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt2_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru2_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt2_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu0_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu0_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu1_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu1_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt1_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu2_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt2_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu2_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt2_1.out"

ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[j7-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[j7-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[j7-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[j7-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[j7-rtu0_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[j7-rtu0_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[j7-rtu1_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[j7-rtu1_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt1_1.out"

ALTERNATIVE_PRIORITY_pru-icss-rpmsg-echo = "100"

ALLOW_EMPTY_${PN} = "1"

# This installs PRU firmware, so skip "arch" QA check
INSANE_SKIP_${PN}-halt = "arch"
INSANE_SKIP_${PN}-rpmsg-echo = "arch"
