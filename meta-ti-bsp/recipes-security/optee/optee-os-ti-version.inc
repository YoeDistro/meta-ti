PV = "4.5.0+git"
SRCREV = "ef1ebdc23034a804a72da2207f1a825ce96a1464"

SRC_URI:remove = " \
    file://0001-checkconf.mk-do-not-use-full-path-to-generate-guard-.patch \
    file://0001-arm64.h-fix-compile-error-with-Clang.patch \
    file://0002-libutils-zlib-fix-Clang-warnings.patch \
"
