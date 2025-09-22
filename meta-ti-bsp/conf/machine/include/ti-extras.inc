# Add TI_EXTRAS to machine overrides so we get access to e.g. 'jailhouse'

TI_EXTRAS ??= ""
MACHINEOVERRIDES =. "${@ ':'.join('${TI_EXTRAS}'.strip().split()) + ':' if d.getVar('TI_EXTRAS') else ''}"
