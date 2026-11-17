require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2026.01"

SRCREV_uboot = "3bb6367dd98310c1651974402f23ad03fb408ab4"

# u-boot/lib/rsa/rsa-sign.c uses the OpenSSL engine API, but this has been
# removed from OpenSSL 4.  Upstream u-boot has been fixed but we can enable
# the stub engine API in OpenSSL until this recipe is removed.
BUILD_CFLAGS += "-DOPENSSL_ENGINE_STUBS"
