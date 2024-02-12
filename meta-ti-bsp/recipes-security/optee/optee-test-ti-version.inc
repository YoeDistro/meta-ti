PV = "4.1.0+git${SRCPV}"
SRCREV = "2e1e7a9c9d659585566a75fc8802f4758c42bcb2"

# Fixes for pointing to 4.0.0 before upstream meta-arm
SRC_URI:remove = "file://0001-xtest-regression_1000-remove-unneeded-stat.h-include.patch"

