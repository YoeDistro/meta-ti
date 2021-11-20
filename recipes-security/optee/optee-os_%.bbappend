do_compile:prepend:ti-soc() {
    export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
}

do_compile:append:k3() {
    ( cd ${B}/core/; \
        cp tee-pager_v2.bin ${B}/bl32.bin; \
        cp tee.elf ${B}/bl32.elf; \
    )
}

# Signing procedure for legacy HS devices
optee_sign_legacyhs() {
    ( cd ${B}/core/; \
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee.bin tee.bin.signed; \
        normfl=`echo ${OPTEEFLAVOR} | tr "_" "-"`
        mv tee.bin.signed ${B}/$normfl.optee; \
    )

    if [ "${OPTEEPAGER}" = "y" ]; then
        oe_runmake clean
        oe_runmake all CFG_TEE_TA_LOG_LEVEL=0 CFG_WITH_PAGER=y
        ( cd ${B}/core/; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee.bin tee.bin.signed; \
            normfl=`echo ${OPTEEFLAVOR} | tr "_" "-"`
            mv tee.bin.signed ${B}/$normfl-pager.optee; \
        )
    fi
}

# Signing procedure for K3 HS devices
optee_sign_k3hs() {
    ( cd ${B}/core/; \
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee-pager_v2.bin tee-pager.bin.signed; \
        mv tee-pager.bin.signed ${B}/bl32.bin; \
        cp tee.elf ${B}/bl32.elf; \
    )
}

do_compile:append:ti43x() {
    optee_sign_legacyhs
}

do_compile:append:dra7xx() {
    optee_sign_legacyhs
}

do_compile:append:am65xx-hs-evm() {
    optee_sign_k3hs
}

do_compile:append:am64xx-hs-evm() {
    optee_sign_k3hs
}

do_compile:append:j7-hs-evm() {
    optee_sign_k3hs
}

do_compile:append:j7200-hs-evm() {
    optee_sign_k3hs
}

do_install:append:ti-soc() {
    install -m 644 ${B}/*.optee ${D}${nonarch_base_libdir}/firmware/ || true
    install -m 644 ${B}/bl32.bin ${D}${nonarch_base_libdir}/firmware/ || true
    install -m 644 ${B}/bl32.elf ${D}${nonarch_base_libdir}/firmware/ || true
}

optee_deploy_legacyhs() {
    cd ${DEPLOYDIR}/
    for f in optee/*.optee; do
        ln -sf $f ${DEPLOYDIR}/
    done
}

do_deploy:append:ti43x() {
    optee_deploy_legacyhs
}

do_deploy:append:dra7xx() {
    optee_deploy_legacyhs
}

do_deploy:append:k3() {
    ln -sf optee/bl32.bin ${DEPLOYDIR}/
    ln -sf optee/bl32.elf ${DEPLOYDIR}/
}

# This is needed for bl32.elf
INSANE_SKIP:${PN}:append:k3 = " textrel"
