SUMMARY = "TI FFT Coprocessor (FFTC) low level driver "

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.fftc"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://fftc.h;beginline=1;endline=39;md5=2f1010c47d364414644caf6d30a2b7df"

COMPATIBLE_MACHINE = "k2hk|k2l"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

DEPENDS_append= " qmss-lld-rtos \
                  cppi-lld-rtos \
"

# HTML doc link params
PDK_COMP_LINK_TEXT = "FFTC LLD"
