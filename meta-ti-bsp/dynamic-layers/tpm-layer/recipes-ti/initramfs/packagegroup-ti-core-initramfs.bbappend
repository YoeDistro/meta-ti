LUKS_ENCRYPTION ?= "${@bb.utils.contains('MACHINE_FEATURES', 'optee-ftpm', 'initramfs-module-luks-ftpm', '', d)}"

RDEPENDS:${PN}:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'luks', '${LUKS_ENCRYPTION}', '', d)}"
