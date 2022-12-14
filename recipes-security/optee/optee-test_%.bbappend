PV_ti-soc = "3.19.0+git${SRCPV}"
SRCREV_ti-soc = "ab9863cc187724e54c032b738c28bd6e9460a4db"
SRC_URI_ti-soc = "git://github.com/OP-TEE/optee_test.git;protocol=https"

DEPENDS_append_ti-soc = " python3-cryptography-native"
