SUMMARY = "RPMsg DMA shared library"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=91dc4ee6d125d0aaba4e5bd2fcc50ed3"

SRC_URI = "git://github.com/TexasInstruments/rpmsg-dma.git;protocol=https;branch=scarthgap"
SRCREV = "153951932925a72346b76d98b2774952c48a82fc"

inherit cmake

DEPENDS = "ti-rpmsg-char"

EXTRA_OECMAKE += "-DBUILD_LIB=ON -DBUILD_AUDIO_OFFLOAD_EXAMPLE=OFF -DBUILD_2DFFT_OFFLOAD_EXAMPLE=OFF"

COMPATIBLE_MACHINE = "^(k3)$"
