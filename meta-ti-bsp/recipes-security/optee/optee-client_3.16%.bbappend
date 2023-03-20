PV:ti-soc = "3.20.0+git${SRCPV}"
SRCREV:ti-soc = "dd2d39b49975d2ada7870fe2b7f5a84d0d3860dc"

inherit pkgconfig
DEPENDS:append:ti-soc = " util-linux"

EXTRA_OEMAKE:append:ti-soc = " PKG_CONFIG=pkg-config"
