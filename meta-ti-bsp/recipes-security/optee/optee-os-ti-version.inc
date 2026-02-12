PV = "4.9.0+git"
SRCREV = "c2b0684fcd89929976a8726e6e3af922b48dd2c7"
SRC_URI:remove = " \
    file://0001-checkconf.mk-do-not-use-full-path-to-generate-guard-.patch \
    file://0001-arm64.h-fix-compile-error-with-Clang.patch \
    file://0002-libutils-zlib-fix-Clang-warnings.patch \
"
