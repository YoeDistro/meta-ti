PV = "4.3.0+git"
SRCREV = "1c0d52ace3c237ca6276cafb5c73f699a75c1d40"

SRC_URI:remove = " \
    file://0001-checkconf.mk-do-not-use-full-path-to-generate-guard-.patch \
    file://0001-arm64.h-fix-compile-error-with-Clang.patch \
    file://0002-libutils-zlib-fix-Clang-warnings.patch \
"
