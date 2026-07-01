FILESEXTRAPATHS:prepend := "${THISDIR}/libsdl2:"

SRC_URI:append = " \
    file://0001-test-New-SDL_TESTS_SHARED_LIB-option-for-dynamically.patch \
"

EXTRA_OECMAKE += "-DSDL_TESTS=ON \
                  -DSDL_INSTALL_TESTS=ON \
                  -DSDL_TESTS_SHARED_LIB=ON \
                 "

PACKAGE_BEFORE_PN = "${PN}-tests"
FILES:${PN}-tests += "${libexecdir} ${datadir}/installed-tests/SDL2"
