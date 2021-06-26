FILESEXTRAPATHS_prepend_ti-soc := "${THISDIR}/${PN}:"

PV_ti-soc = "3.12.0+git${SRCPV}"

SRCREV_ti-soc = "3d47a131bca1d9ed511bfd516aa5e70269e12c1d"

SRC_URI_ti-soc = " \
    git://github.com/OP-TEE/optee_os.git \
    file://0006-allow-setting-sysroot-for-libgcc-lookup.patch \
    file://0007-allow-setting-sysroot-for-clang.patch \
"

do_compile_prepend_ti-soc() {
    export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
}

do_compile_append_k3() {
    ( cd out/arm-plat-${OPTEEOUTPUTMACHINE}/core/; \
        cp tee-pager_v2.bin ${B}/bl32.bin; \
        cp tee.elf ${B}/bl32.elf; \
    )
}

# Signing procedure for legacy HS devices
optee_sign_legacyhs() {
    ( cd out/arm-plat-${OPTEEOUTPUTMACHINE}/core/; \
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee.bin tee.bin.signed; \
        normfl=`echo ${OPTEEFLAVOR} | tr "_" "-"`
        mv tee.bin.signed ${B}/$normfl.optee; \
    )

    if [ "${OPTEEPAGER}" = "y" ]; then
        rm -rf out/
        oe_runmake all CFG_TEE_TA_LOG_LEVEL=0 CFG_WITH_PAGER=y
        ( cd out/arm-plat-${OPTEEOUTPUTMACHINE}/core/; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee.bin tee.bin.signed; \
            normfl=`echo ${OPTEEFLAVOR} | tr "_" "-"`
            mv tee.bin.signed ${B}/$normfl-pager.optee; \
        )
    fi
}

# Signing procedure for K3 HS devices
optee_sign_k3hs() {
    ( cd out/arm-plat-${OPTEEOUTPUTMACHINE}/core/; \
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee-pager_v2.bin tee-pager.bin.signed; \
        mv tee-pager.bin.signed ${B}/bl32.bin; \
        cp tee.elf ${B}/bl32.elf; \
    )
}

do_compile_append_ti43x() {
    optee_sign_legacyhs
}

do_compile_append_dra7xx() {
    optee_sign_legacyhs
}

do_compile_append_am65xx-hs-evm() {
    optee_sign_k3hs
}

do_compile_append_j7-hs-evm() {
    optee_sign_k3hs
}

do_install_append_ti-soc() {
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

do_deploy_append_ti43x() {
    optee_deploy_legacyhs
}

do_deploy_append_dra7xx() {
    optee_deploy_legacyhs
}

do_deploy_append_k3() {
    ln -sf optee/bl32.bin ${DEPLOYDIR}/
    ln -sf optee/bl32.elf ${DEPLOYDIR}/
}

# This is needed for bl32.elf
INSANE_SKIP_${PN}_append_k3 = " textrel"
