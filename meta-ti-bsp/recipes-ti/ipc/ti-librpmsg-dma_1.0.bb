SUMMARY = "RPMsg DMA shared library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=744e63d2bb8c6151dcdd97f49aa02c53"

SRC_URI = "git://github.com/TexasInstruments/rpmsg-dma.git;protocol=https;branch=main"
SRCREV = "609fd72f458ba56f80d06810509ef88d010d2c03"

inherit cmake

DEPENDS = "ti-rpmsg-char"

EXTRA_OECMAKE += "-DBUILD_LIB=ON -DBUILD_EXAMPLE=OFF"

COMPATIBLE_MACHINE = "^(k3)$"
