PV = "4.7.0+git"
SRCREV = "a9690ae39995af36a31b7a4f446f27ea0787e3a4"

SRC_URI:remove = " \
    file://0001-mk-compile.mk-remove-absolute-build-time-paths.patch \
    file://0001-compile.mk-use-CFLAGS-from-environment.patch \
    file://0002-link.mk-use-CFLAGS-with-version.o.patch \
    file://0003-link.mk-generate-version.o-in-link-out-dir.patch \
"
