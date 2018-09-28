SUMMARY = "TI RTOS Low level driver for Video Processing Subsystem (VPS) "

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;beginline=1;endline=32;md5=4303d306ea53eb66132983d6901c137d"

COMPATIBLE_MACHINE = "omap-a15"
PACKAGE_ARCH = "${MACHINE_ARCH}"

VPS_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/vps.git"
VPS_LLD_GIT_PROTOCOL = "git"
VPS_LLD_GIT_BRANCH = "master"
VPS_LLD_GIT_DESTSUFFIX = "git/ti/drv/vps"

# Below commit ID corresponds to "DEV.VPS_LLD.01.00.01.13
VPS_LLD_SRCREV = "58020ff07c993e6efe01d78531bc7459b9edeaf2"

BRANCH = "${VPS_LLD_GIT_BRANCH}"
SRC_URI = "${VPS_LLD_GIT_URI};destsuffix=${VPS_LLD_GIT_DESTSUFFIX};protocol=${VPS_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${VPS_LLD_SRCREV}"
PV = "01.00.01.13"
PR = "r0"

S = "${WORKDIR}/${VPS_LLD_GIT_DESTSUFFIX}"

DEPENDS = " ti-sysbios \
            osal-rtos \
            edma3-lld-rtos \
            board-rtos \
            uart-lld-rtos \
            i2c-lld-rtos \
            pm-lld-rtos \
"
export PDK_VPS_COMP_PATH = "${WORKDIR}/build"
export DEST_ROOT="${S}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

do_compile_append() {
    # Delete archive created by XDC release command since it does not contain all content
    find -name "*.tar" -exec rm -f {} \;

    # Archive from build/ to capture ti/drv/pm/ in archive
    cd ${B}
    tar -cf pm_lld.tar --exclude='*.tar' ./*
}

INSANE_SKIP_${PN} = "arch ldflags"
