EMAC_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/emac-lld.git"
EMAC_LLD_GIT_PROTOCOL = "git"
EMAC_LLD_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.EMAC_LLD.01.00.03.16"
EMAC_LLD_SRCREV = "f90f832dc63b60b234c3f5ae2ff4d774728e83bf"

BRANCH ="${EMAC_LLD_GIT_BRANCH}"
SRC_URI = "${EMAC_LLD_GIT_URI};protocol=${EMAC_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${EMAC_LLD_SRCREV}"

# The LLD version
PV = "01.00.03.16+git${SRCPV}"
