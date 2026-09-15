require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "4ca322ca563a21cccad8c9ba65e386b9fd34dd16"

SRC_URI += "file://0001-binman-migrate-form-pkg_resources-to-importlib.patch"

# u-boot/lib/rsa/rsa-sign.c uses the OpenSSL engine API, but this has been
# removed from OpenSSL 4.  Upstream u-boot has been fixed but we can enable
# the stub engine API in OpenSSL until this recipe is removed.
BUILD_CFLAGS += "-DOPENSSL_ENGINE_STUBS"
