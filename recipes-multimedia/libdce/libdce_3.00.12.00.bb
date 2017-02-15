DESCRIPTION = "Library used for remotely invoking the hw accelerated codec on IVA-HD"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://libdce.h;beginline=1;endline=31;md5=0a398cf815b8b5f31f552266cd453dae"

inherit autotools pkgconfig

DEPENDS = "libdrm ti-ipc"

SRC_URI = "git://git.omapzoom.org/repo/libdce.git;protocol=git"
SRCREV = "398dd27e7f3181fff9e3d910b418c69f1493f52f"

PR = "r4"

S = "${WORKDIR}/git"
EXTRA_OECONF += "IPC_HEADERS=${STAGING_INCDIR}/ti/ipc/mm"
