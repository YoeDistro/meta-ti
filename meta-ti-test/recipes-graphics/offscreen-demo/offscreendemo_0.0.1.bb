SUMMARY = "Lightweight Render Example"
DESCRIPTION = "Lightweight Render Example. Heavily inspired by Eduardo Lima's gpu-playground, this attempts to act as the smallest demo of offscreen rendering."

HOMEPAGE = "https://github.com/TexasInstruments/graphics-tests.git"

SRC_URI = "git://github.com/TexasInstruments/graphics-tests.git;protocol=https;branch=master"
SRCREV = "eac40e0073cb5f81688aee890e0a67f99678a290"

LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=65d3616852dbf7b1a6d4b53b00626032"

DEPENDS += "virtual/egl virtual/libgles2"

inherit pkgconfig meson features_check

REQUIRED_DISTRO_FEATURES = "opengl"
