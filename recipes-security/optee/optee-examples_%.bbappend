PV_ti-soc = "3.19.0+git${SRCPV}"
SRCREV_ti-soc = "f301ee9df2129c0db683e726c91dc2cefe4cdb65"
SRC_URI_ti-soc = "git://github.com/linaro-swg/optee_examples.git;protocol=https"

DEPENDS_append_ti-soc = " python3-cryptography-native"
