PV = "4.7.0+git"
SRCREV = "a9690ae39995af36a31b7a4f446f27ea0787e3a4"
SRC_URI:remove = " \
    file://0001-checkconf.mk-do-not-use-full-path-to-generate-guard-.patch \
    file://0001-arm64.h-fix-compile-error-with-Clang.patch \
    file://0002-libutils-zlib-fix-Clang-warnings.patch \
"
