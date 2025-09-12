OPTEE_TI_VERSION = ""
OPTEE_TI_VERSION:ti-soc = "${BPN}-ti-version.inc"

require ${OPTEE_TI_VERSION}

OPTEE_TI_OVERRIDES = ""
OPTEE_TI_OVERRIDES:ti-soc = "${BPN}-ti-overrides.inc"

SRC_URI:remove = " \
    file://0003-optee-enable-clang-support.patch \
"

require ${OPTEE_TI_OVERRIDES}
