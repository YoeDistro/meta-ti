# Add TI_EXTRAS to machine overrides so we get access to e.g. 'jailhouse'

TI_EXTRAS ??= ""
MACHINEOVERRIDES =. "${@ ':'.join('${TI_EXTRAS}'.strip().split()) + ':' if d.getVar('TI_EXTRAS') else ''}"

# Variable defaults for the various recipes that make use of TI Extras
UBOOT_TI_TEST_BUILDS_MAKE_OPTS ?= ""
