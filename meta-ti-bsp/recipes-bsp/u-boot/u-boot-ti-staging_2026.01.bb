require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2026.01"

SRCREV_uboot = "2a85f4bcffc50ddc8b443d8e4162e9e46ed0f200"

# u-boot/lib/rsa/rsa-sign.c uses the OpenSSL engine API, but this has been
# removed from OpenSSL 4.  Upstream u-boot has been fixed but we can enable
# the stub engine API in OpenSSL until this recipe is removed.
BUILD_CFLAGS += "-DOPENSSL_ENGINE_STUBS"
