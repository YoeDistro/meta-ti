PV_ti-soc = "3.20.0+git${SRCPV}"
SRCREV_ti-soc = "a98d01e1b9168eaed96bcd0bac0df67c44a81081"
SRC_URI_ti-soc = "git://github.com/linaro-swg/optee_examples.git;protocol=https"

DEPENDS_append_ti-soc = " python3-cryptography-native"
