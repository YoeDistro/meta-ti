SUMMARY = "TI Unified WIC builder"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92b3423643fe55420ad98ab5bef7c799"

inherit deploy

SRC_URI = " \
    file://ti_unified_wic.sh;name=ti_unified_wic \
    file://LICENSE;name=license \
"

SRC_URI[ti_unified_wic.sha256sum] = "aecf38ebca7f25263d4656d12b210db1197aca92d311d44f924906bb86982b7a"
SRC_URI[license.sha256sum] = "2a0aebb221c0b2b97b907baf8eb103fb3474cfcadb78667859e028a5e52e984c"

S = "${WORKDIR}"

do_deploy() {

    install -m 0755 ${S}/ti_unified_wic.sh ${DEPLOYDIR}

    loCfg="${DEPLOYDIR}/ti_unified_wic.cfg"

    echo "[machine]" > $loCfg
    echo "${MACHINE}" >> $loCfg
    echo "" >> $loCfg
    echo "[variants]" >> $loCfg
    echo "${UBOOT_CONFIG}" >> $loCfg
    echo "" >> $loCfg
    echo "[multiconfigs]" >> $loCfg
    echo "${TI_MULTICONFIGS}" >> $loCfg
    echo "" >> $loCfg
    echo "[bootloaders]" >> $loCfg
    echo "${TI_BOOTLOADERS}" >> $loCfg
}

addtask deploy after do_install before do_build

