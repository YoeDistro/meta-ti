PV = "4.6.0+git"
SRCREV = "71785645fa6ce42db40dbf5a54e0eaedc4f61591"

SRC_URI:remove = " \
    file://0001-mk-compile.mk-remove-absolute-build-time-paths.patch \
    file://0001-compile.mk-use-CFLAGS-from-environment.patch \
    file://0002-link.mk-use-CFLAGS-with-version.o.patch \
    file://0003-link.mk-generate-version.o-in-link-out-dir.patch \
"
