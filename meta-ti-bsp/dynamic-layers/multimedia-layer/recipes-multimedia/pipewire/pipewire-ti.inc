PR:append = ".ti0"

FILESEXTRAPATHS:prepend := "${THISDIR}/pipewire:"

# AM62D-specific config files
SRC_URI:append:am62dxx-evm = " \
    file://90-pipewire-sink.conf \
    file://91-pipewire-source.conf \
"

do_install:append:am62dxx-evm() {
    install -d ${D}${sysconfdir}/pipewire/pipewire.conf.d
    install -m 0644 ${UNPACKDIR}/90-pipewire-sink.conf ${D}${sysconfdir}/pipewire/pipewire.conf.d/
    install -m 0644 ${UNPACKDIR}/91-pipewire-source.conf ${D}${sysconfdir}/pipewire/pipewire.conf.d/
}

FILES:${PN}:append:am62dxx-evm = " \
    ${sysconfdir}/pipewire/pipewire.conf.d/90-pipewire-sink.conf \
    ${sysconfdir}/pipewire/pipewire.conf.d/91-pipewire-source.conf \
"
