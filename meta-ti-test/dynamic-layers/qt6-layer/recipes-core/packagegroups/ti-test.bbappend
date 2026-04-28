# Include the examples for Qt6 related tests
# Include qtwayland for wayland support if the distro enables it

TI_TEST_EXTRAS:append = " \
    qtbase-examples \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
"
