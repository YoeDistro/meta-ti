SUMMARY = "Benchmark for checking DDR bandwidth"
HOMEPAGE = "https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/tree/arm_apps/arm_ddr_bandwidth/runLmDDRBandwidth.sh"
LICENSE = "CC-BY-SA-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6e0ae7214f6c74c149cb25f373057fa9"

PV = "1.0"

SRC_URI = " \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/arm_apps/arm_ddr_bandwidth/runLmDDRBandwidth.sh;name=runLmDDRBandwidth \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/LICENSE;name=license \
"
SRC_URI[runLmDDRBandwidth.sha256sum] = "67997d721b351344d8788200a15bb3640093045bc25f2b5307dd331ebd1556a3"
SRC_URI[license.sha256sum] = "7febd1df714fa4b1e44fe0b0f73ceac7f9b9f97326695a0cc7074bd6c8d263e3"

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}${datadir}/matrix-gui-2.0/apps

    install -d ${D}${bindir}
    install -m 0755 ${S}/runLmDDRBandwidth.sh ${D}${bindir}/runLmDDRBandwidth.sh
}

FILES:${PN} = " \
    ${datadir}/matrix-gui-2.0/apps \
    ${bindir}/runLmDDRBandwidth.sh \
"

