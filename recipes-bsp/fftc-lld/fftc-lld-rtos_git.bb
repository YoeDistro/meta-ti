SUMMARY = "TI FFT Coprocessor (FFTC) low level driver "

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://fftc.h;beginline=1;endline=39;md5=2f1010c47d364414644caf6d30a2b7df"

COMPATIBLE_MACHINE = "k2hk-evm|k2l-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

FFTC_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/fftc-lld.git"
FFTC_LLD_GIT_PROTOCOL = "git"
FFTC_LLD_GIT_BRANCH = "master"
FFTC_LLD_GIT_DESTSUFFIX = "git/ti/drv/fftc"

# Below commit ID corresponds to "DEV.FFTC_LLD.02.02.00.06A"
FFTC_LLD_SRCREV = "96e8839a2de5f15ba8765f4a6a4fd499689c496d"

BRANCH = "${FFTC_LLD_GIT_BRANCH}"
SRC_URI = "${FFTC_LLD_GIT_URI};destsuffix=${FFTC_LLD_GIT_DESTSUFFIX};protocol=${FFTC_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${FFTC_LLD_SRCREV}"
PV = "02.02.00.06A"
PR = "r0"

S = "${WORKDIR}/${FFTC_LLD_GIT_DESTSUFFIX}"


DEPENDS_append= " qmss-lld-rtos \
                  cppi-lld-rtos \
"

# HTML doc link params
PDK_COMP_LINK_TEXT = "FFTC LLD"
