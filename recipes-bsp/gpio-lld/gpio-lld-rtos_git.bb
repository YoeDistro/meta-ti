SUMMARY = "TI RTOS driver for General Purpose IO (GPIO)"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://GPIO.h;beginline=1;endline=31;md5=8580f9c5c0de8d5d13518cf18a0122b8"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1|c667x-evm|c665x-evm|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

GPIO_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/gpio-lld.git"
GPIO_LLD_GIT_PROTOCOL = "git"
GPIO_LLD_GIT_BRANCH = "master"
GPIO_LLD_GIT_DESTSUFFIX = "git/ti/drv/gpio"

# Below commit ID corresponds to "DEV.GPIO_LLD.01.00.00.12"
GPIO_LLD_SRCREV = "49a25da3341c83bec5e5db586217e534de9e2063"

BRANCH = "${GPIO_LLD_GIT_BRANCH}"
SRC_URI = "${GPIO_LLD_GIT_URI};destsuffix=${GPIO_LLD_GIT_DESTSUFFIX};protocol=${GPIO_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${GPIO_LLD_SRCREV}"
PV = "01.00.00.12"
PR = "r0"

S = "${WORKDIR}/${GPIO_LLD_GIT_DESTSUFFIX}"

DEPENDS_append = " osal-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_GPIO_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "GPIO LLD"
