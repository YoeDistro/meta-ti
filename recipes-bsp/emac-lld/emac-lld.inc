EMAC_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/emac-lld.git"
EMAC_LLD_GIT_PROTOCOL = "git"
EMAC_LLD_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.EMAC_LLD.01.00.03.16B"
EMAC_LLD_SRCREV = "87ed1e8678c42bb3fdea8f6e7a0197cf159d75b6"

BRANCH ="${EMAC_LLD_GIT_BRANCH}"
SRC_URI = "${EMAC_LLD_GIT_URI};protocol=${EMAC_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${EMAC_LLD_SRCREV}"

# The LLD version
PV = "01.00.03.16+git${SRCPV}"
