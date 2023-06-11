# Add TI_EXTRAS to machine overrides so we get access to e.g. 'jailhouse'

TI_EXTRAS ??= ""
MACHINEOVERRIDES =. "${@ '${TI_EXTRAS}:' if d.getVar('TI_EXTRAS').startswith('tie-') else ''}"

