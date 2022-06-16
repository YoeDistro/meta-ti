PV_ti-soc = "3.17.0+git${SRCPV}"
SRCREV_ti-soc = "8a698baf9e8e010e4d8d52e6aded42dfc31e5b25"
SRC_URI_ti-soc = "git://github.com/OP-TEE/optee_test.git;protocol=https"

DEPENDS_append_ti-soc = " python3-cryptography-native"
