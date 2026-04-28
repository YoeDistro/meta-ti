SUMMARY = "Benchmarks for checking various OpenSSL performance functions"
HOMEPAGE = "https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/tree/cryptos_apps_scripts/"
LICENSE = "CC-BY-SA-3.0"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/LICENSE;md5=6e0ae7214f6c74c149cb25f373057fa9"

PV = "1.0"

SRC_URI = " \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/cryptos_apps_scripts/openssl_perf_scripts/openssl_perf.sh;name=openssl_perf \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/cryptos_apps_scripts/openssl_aes256_encdec_scripts/openssl_aes256_encdec.sh;name=openssl_aes256_encdec \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/cryptos_apps_scripts/openssl_display_cert_scripts/openssl_display_cert.sh;name=openssl_display_cert \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/cryptos_apps_scripts/openssl_gen_cert_scripts/openssl_gen_cert.sh;name=openssl_gen_cert \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/cryptos_apps_scripts/openssl_gen_pubkey_scripts/openssl_gen_pubkey.sh;name=openssl_gen_pubkey \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/cryptos_apps_scripts/openssl_gen_sha1hash_scripts/openssl_gen_sha1hash.sh;name=openssl_gen_sha1hash \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/cryptos_apps_scripts/openssl_start_server_scripts/openssl_start_server.sh;name=openssl_start_server \
    https://git.ti.com/cgit/matrix-gui-v2/matrix-gui-v2-apps/plain/LICENSE;name=license \
"
SRC_URI[openssl_perf.sha256sum] = "5769e841b977d6a858da38826c2cf47c288ed642614c5b6f802409cb3601ab29"
SRC_URI[openssl_aes256_encdec.sha256sum] = "1a488e3ba98f6f2e5f73c14681b0452ba902aecbb174e4e74b524dda4c5eda7a"
SRC_URI[openssl_display_cert.sha256sum] = "fbe80fe1afe29f777e346ecd86d89e1f27cedc1bb745a9a9bc5cd38f2d566a3e"
SRC_URI[openssl_gen_cert.sha256sum] = "47bfc80b88edebcc4d82ee3816bc05b95ca0d42c42e7f3b83e74fa6f9c6c01ef"
SRC_URI[openssl_gen_pubkey.sha256sum] = "4581e2329afeb814602c4874ffa038d95a4bcc8b23df24d7be25822d0dd6c433"
SRC_URI[openssl_gen_sha1hash.sha256sum] = "f338f8c3067ebb1c5710b16b91e8b9e38685b05fcc072060ec0c62bd10cefd97"
SRC_URI[openssl_start_server.sha256sum] = "e23499210f701ef831d39a8c732f34f4b06fb3ba2d069c4a95ef25ae59f3c93e"
SRC_URI[license.sha256sum] = "7febd1df714fa4b1e44fe0b0f73ceac7f9b9f97326695a0cc7074bd6c8d263e3"

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/openssl_perf.sh ${D}${bindir}/openssl_perf.sh
    install -m 0755 ${S}/openssl_aes256_encdec.sh ${D}${bindir}/openssl_aes256_encdec.sh
    install -m 0755 ${S}/openssl_display_cert.sh ${D}${bindir}/openssl_display_cert.sh
    install -m 0755 ${S}/openssl_gen_cert.sh ${D}${bindir}/openssl_gen_cert.sh
    install -m 0755 ${S}/openssl_gen_pubkey.sh ${D}${bindir}/openssl_gen_pubkey.sh
    install -m 0755 ${S}/openssl_gen_sha1hash.sh ${D}${bindir}/openssl_gen_sha1hash.sh
    install -m 0755 ${S}/openssl_start_server.sh ${D}${bindir}/openssl_start_server.sh
}

FILES:${PN} = "\
    ${bindir}/openssl_perf.sh \
    ${bindir}/openssl_aes256_encdec.sh \
    ${bindir}/openssl_display_cert.sh \
    ${bindir}/openssl_gen_cert.sh \
    ${bindir}/openssl_gen_pubkey.sh \
    ${bindir}/openssl_gen_sha1hash.sh \
    ${bindir}/openssl_start_server.sh \
"

