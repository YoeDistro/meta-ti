SUMMARY = "Watchdog Timer (wdtimer) test example"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://WatchdogTimer.xdc;beginline=1;endline=31;md5=837fb1761f89c7671e79bd5992bcf9e4"

inherit ti-pdk

COMPATIBLE_MACHINE = "k2hk|k2e|k2l"
PACKAGE_ARCH = "${MACHINE_ARCH}"

WATCHDOG_TIMER_GIT_URI = "git://git.ti.com/keystone-rtos/wdtimer.git"
WATCHDOG_TIMER_GIT_PROTOCOL = "git"
WATCHDOG_TIMER_GIT_BRANCH = "master"
WATCHDOG_TIMER_GIT_DESTSUFFIX = "git/ti/instrumentation/wdtimer"

# Below commit ID corresponds to "DEV.WDTIMER.01.00.00.04"
WATCHDOG_TIMER_SRCREV = "e25220ea8cb1c1d4bd5f5fb4fba37407d5730ffb"

BRANCH = "${WATCHDOG_TIMER_GIT_BRANCH}"
SRC_URI = "${WATCHDOG_TIMER_GIT_URI};destsuffix=${WATCHDOG_TIMER_GIT_DESTSUFFIX};protocol=${WATCHDOG_TIMER_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${WATCHDOG_TIMER_SRCREV}"
PV = "01.00.00.04"
PR = "r0"

S = "${WORKDIR}/git/ti/instrumentation/wdtimer"

# HTML doc link params
PDK_COMP_LINK_TEXT = "Watchdog Timer"
