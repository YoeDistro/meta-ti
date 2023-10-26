PV = "4.0.0+git${SRCPV}"
SRCREV = "1c3d6be5eaa6174e3dbabf60928d15628e39b994"

# Fixes for pointing to 4.0.0 before upstream meta-arm
SRC_URI:remove = "file://0001-xtest-regression_1000-remove-unneeded-stat.h-include.patch"

