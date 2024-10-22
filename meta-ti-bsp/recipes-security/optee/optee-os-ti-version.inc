PV = "4.4.0+git"
SRCREV = "8f645256efc0dc66bd5c118778b0b50c44469ae1"

SRC_URI:remove = " \
    file://0001-mk-compile.mk-remove-absolute-build-time-paths.patch \
    file://0001-compile.mk-use-CFLAGS-from-environment.patch \
    file://0002-link.mk-use-CFLAGS-with-version.o.patch \
    file://0003-link.mk-generate-version.o-in-link-out-dir.patch \
"
