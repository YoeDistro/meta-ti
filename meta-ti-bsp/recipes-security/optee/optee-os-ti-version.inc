PV = "4.6.0+git"
SRCREV = "71785645fa6ce42db40dbf5a54e0eaedc4f61591"

SRC_URI:remove = " \
    file://0001-checkconf.mk-do-not-use-full-path-to-generate-guard-.patch \
    file://0001-arm64.h-fix-compile-error-with-Clang.patch \
    file://0002-libutils-zlib-fix-Clang-warnings.patch \
"
