DESCRIPTION = "TI Unified Instrumentation Architecture in C (cUIA)"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://cuia_${CUIA_PV}_manifest.html;md5=0eea94e3bb94de4ddee77653eba1c7c5"

CUIA_PV = "1_00_00_13"
CUIA_PVExtra = ""

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_ccstudio/UIA/SCMCSDK/cuia_${CUIA_PV}${CUIA_PVExtra}.tar.gz;name=cuia"
SRC_URI[cuia.md5sum] = "ef53989ac70c191841cd656cdc8770be"
SRC_URI[cuia.sha256sum] = "f16d138a9146fdc7d52cba77bd30596da65835f6ffe4092baf2b33d1c1a4fb0d"

S = "${WORKDIR}/cuia_${CUIA_PV}${CUIA_PVExtra}"

CLEANBROKEN = "1"

do_compile () {
	cd ${S}
	oe_runmake clean
	oe_runmake all
}

do_install() {
	cd ${S}
	oe_runmake DESTDIR=${D} libdir=${libdir} includedir=${includedir} install
}

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"

INSANE_SKIP_${PN} += "textrel"
