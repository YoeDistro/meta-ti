python () {
    layers = d.getVar("BBFILE_COLLECTIONS", True)
    if not layers:
        return
    if "yoctobsp" in layers:
        bb.fatal("\nmeta-yocto-bsp has been detected in bblayers.conf, which provides basic reference BSP for beaglebone platform. " \
                "\nSince meta-ti provides official comprehensive BSP for TI SoCs, including beaglebone, there could be potential conflicts " \
                "\nbetween beaglebone machine definitions available in meta-yocto-bsp and meta-ti layers. It is recommended to remove " \
                "\nor disable meta-yocto-bsp in bblayers.conf, when using meta-ti and building for beaglebone platform. Alternatively, " \
                "\nit is possible to build for am335x-evm with meta-ti, which is a more generic platform for Sitara AM335x SoC and " \
                "\nalso covers beaglebone variants.")
}
