PV = "4.4.0+git"
SRCREV = "8f645256efc0dc66bd5c118778b0b50c44469ae1"

SRC_URI:remove = " \
    file://0001-checkconf.mk-do-not-use-full-path-to-generate-guard-.patch \
    file://0001-arm64.h-fix-compile-error-with-Clang.patch \
    file://0002-libutils-zlib-fix-Clang-warnings.patch \
"
