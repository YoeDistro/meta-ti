FILESEXTRAPATHS:prepend := "${THISDIR}/weston:"

PR:append = ".tisoc1"

SRC_URI += " \
        file://0001-Revert-require-GL_EXT_unpack_subimage-commit.patch \
        file://0002-surface-state-Fix-assert-firing-when-subsurfaces-hav.patch \
"
