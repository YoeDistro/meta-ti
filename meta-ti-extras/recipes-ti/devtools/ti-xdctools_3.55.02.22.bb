SUMMARY = "TI XDCtools (RTSC - Real Time Software Components"
DESCRIPTION = "TI XDCtools (RTSC - Real Time Software Components - http://rtsc.eclipse.org)"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/rtsc"
SECTION = "devel"
LICENSE = "BSD-3-Clause & GPL-2.0-only"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

    install -d ${D}${XDC_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS}  ${S}/* ${D}${XDC_INSTALL_DIR_RECIPE}
}

FILES:${PN}-dev = "${XDC_INSTALL_DIR_RECIPE}"

BBCLASSEXTEND = "native nativesdk"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN}-dev = "libdir dev-elf arch file-rdeps"

# Prevent internal libs from getting picked up
PRIVATE_LIBS = " \
libncdb.so \
libcdb.so \
libjavaplugin_oji.so \
libjavaplugin_jni.so \
libjsound.so \
libinstrument.so \
libjawt.so \
libzip.so \
libjava_crw_demo.so \
libjavaplugin_nscp_gcc29.so \
libhprof.so \
libcmm.so \
libjdwp.so \
libmlib_image.so \
libjpeg.so \
libverify.so \
libjavaplugin_nscp.so \
libmanagement.so \
libunpack.so \
librmi.so \
libJdbcOdbc.so \
libawt.so \
libnet.so \
libjaas_unix.so \
libnio.so \
libdcpr.so \
libioser12.so \
libjsoundalsa.so \
libjava.so \
libfontmanager.so \
libdt_socket.so \
libmawt.so \
libjvm.so \
libhpi.so \
lib.so \
libcairo.so.2 \
libjli.so \
libawt_xawt.so \
"

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST:ti-soc = "(.*)"
COMPATIBLE_HOST:class-native = "(.*)"
COMPATIBLE_HOST:class-nativesdk = "(.*)"

S = "${UNPACKDIR}/xdctools_${PV}_core"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/rtsc/${PV}/exports/xdccore/xdctools_${PV}_core_linux.zip;name=xdczip"

PE = "1"
PV = "3_55_02_22"
PR = "r0"

LIC_FILES_CHKSUM = "file://docs/license/xdc/shelf/package.html;beginline=1;endline=140;md5=2e742b9757bc9ce0241aadba9f627ab8"

SRC_URI[xdczip.md5sum] = "bb9154d677ce4724a5eac73817b23a0f"
SRC_URI[xdczip.sha256sum] = "bf90dfbbe298458c736d81d5631db25b335e79146923a4a50fab217e02723e7b"
