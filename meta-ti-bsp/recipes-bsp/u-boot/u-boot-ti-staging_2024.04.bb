require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2024.04"

SRC_URI += "file://0001-scripts-dtc-pylibfdt-libfdt.i_shipped-Use-SWIG_Appen.patch"

SRCREV_uboot = "29d0c23d67ee7b88e46fe1753cd020e2b04c2ef6"

# u-boot/lib/rsa/rsa-sign.c uses the OpenSSL engine API, but this has been
# removed from OpenSSL 4.  Upstream u-boot has been fixed but we can enable
# the stub engine API in OpenSSL until this recipe is removed.
BUILD_CFLAGS += "-DOPENSSL_ENGINE_STUBS"
