require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2026.01"

SRCREV_uboot = "17e4757f59260e07e3af17387a5dc0fca3ca05df"

# u-boot/lib/rsa/rsa-sign.c uses the OpenSSL engine API, but this has been
# removed from OpenSSL 4.  Upstream u-boot has been fixed but we can enable
# the stub engine API in OpenSSL until this recipe is removed.
BUILD_CFLAGS += "-DOPENSSL_ENGINE_STUBS"
