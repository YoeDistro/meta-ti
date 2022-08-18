PV_ti-soc = "3.17.0+git${SRCPV}"
SRCREV_ti-soc = "65fc74309e12189ad5b6ce3ffec37c8011088a5a"
SRC_URI_ti-soc = "git://github.com/linaro-swg/optee_examples.git;protocol=https"

DEPENDS_append_ti-soc = " python3-cryptography-native"
