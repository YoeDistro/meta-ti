PV = "4.5.0+git"
SRCREV = "ef1ebdc23034a804a72da2207f1a825ce96a1464"

SRC_URI:remove = " \
    file://0001-mk-compile.mk-remove-absolute-build-time-paths.patch \
    file://0001-compile.mk-use-CFLAGS-from-environment.patch \
    file://0002-link.mk-use-CFLAGS-with-version.o.patch \
    file://0003-link.mk-generate-version.o-in-link-out-dir.patch \
"
