# Add concept of machine variants to split DTBs into subsets
#
# Use these for temporary overrides
CMEM_MACHINE = "${MACHINE}"
CMEM_MACHINE_am57xx-evm = "am571x am572x am574x"
CMEM_MACHINE_am57xx-hs-evm = "am571x am572x"
CMEM_MACHINE_dra7xx-evm = "dra71x dra72x dra74x"
CMEM_MACHINE_dra7xx-hs-evm = "dra71x dra72x dra74x"

# Set cmem.dtsi per machine or machine variant
CMEM_DTSI = "cmem.dtsi"
CMEM_DTSI_am571x = "cmem-am571x.dtsi"
CMEM_DTSI_am574x = "cmem-am574x.dtsi"
CMEM_DTSI_dra71x = "cmem-dra71x.dtsi"
CMEM_DTSI_dra72x = "cmem-dra72x.dtsi"

# Split device trees between variants
CMEM_DEVICETREE = "${KERNEL_DEVICETREE}"
CMEM_DEVICETREE_am571x = "am571x-idk.dtb am571x-idk-lcd-osd101t2045.dtb am571x-idk-lcd-osd101t2587.dtb"
CMEM_DEVICETREE_am572x = "am57xx-beagle-x15.dtb am57xx-beagle-x15-revc.dtb \
                          am572x-idk.dtb am572x-idk-lcd-osd101t2045.dtb am572x-idk-lcd-osd101t2587.dtb"
CMEM_DEVICETREE_am574x = "am574x-idk.dtb am574x-idk-lcd-osd101t2587.dtb"
CMEM_DEVICETREE_dra71x = "dra71-evm.dtb"
CMEM_DEVICETREE_dra72x = "dra72-evm.dtb dra72-evm-lcd-osd101t2045.dtb dra72-evm-lcd-osd101t2587.dtb \
                          dra72-evm-revc.dtb dra72-evm-revc-lcd-osd101t2045.dtb dra72-evm-revc-lcd-osd101t2587.dtb"
CMEM_DEVICETREE_dra74x = "dra7-evm.dtb dra7-evm-lcd-osd101t2045.dtb dra7-evm-lcd-osd101t2587.dtb"

# Flag to enable CMEM injection
RESERVE_CMEM ?= "0"

# Variables which influence setup_cmem
CMEM_VARS  = "RESERVE_CMEM CMEM_MACHINE"
CMEM_VARS += "CMEM_DTSI ${@' '.join(map(lambda s: 'CMEM_DTSI_' + s, (d.getVar('CMEM_MACHINE') or '').split()))}"
CMEM_VARS += "CMEM_DEVICETREE ${@' '.join(map(lambda s: 'CMEM_DEVICETREE_' + s, (d.getVar('CMEM_MACHINE') or '').split()))}"

# Add correct cmem.dtsi to SRC_URI for each variant for a given machine
python do_unpack() {
    old_overrides = d.getVar('OVERRIDES', False)

    # Initialize with empty string to simplify logic to append to SRC_URI
    cmem_dtsi = set([''])

    for cmem_machine in (d.getVar('CMEM_MACHINE') or '').split():
        # Create copy of data for additional override
        localdata = bb.data.createCopy(d)
        localdata.setVar('OVERRIDES', '%s:%s' % (cmem_machine, old_overrides))
        bb.data.update_data(localdata)

        cmem_dtsi.add(localdata.getVar('CMEM_DTSI'))

    d.appendVar('SRC_URI', ' file://'.join(cmem_dtsi))
    bb.build.exec_func('base_do_unpack', d)
}

python do_setup_cmem() {
    import shutil

    old_overrides = d.getVar('OVERRIDES', False)

    if d.getVar('RESERVE_CMEM') is '1':
        for cmem_machine in (d.getVar('CMEM_MACHINE') or '').split():
            # Create copy of data for additional override
            localdata = bb.data.createCopy(d)
            localdata.setVar('OVERRIDES', '%s:%s' % (cmem_machine, old_overrides))
            bb.data.update_data(localdata)

            # Get source directory and dtsi filename
            src_dir  = localdata.getVar('WORKDIR')
            src_dtsi = localdata.getVar('CMEM_DTSI')

            # Get destination directory and destination dtsi filename which adds
            #   the MACHINE prefix.
            dst_dir  = os.path.join(localdata.getVar('S'), 'arch/arm/boot/dts')
            dst_dtsi = localdata.expand('${MACHINE}-${CMEM_DTSI}')

            # Copy cmem.dtsi into source tree
            shutil.copy(os.path.join(src_dir,src_dtsi), os.path.join(dst_dir,dst_dtsi))

            # Inject dtsi into each dts in list
            for dtb in (localdata.getVar('CMEM_DEVICETREE') or '').split():
                dts = dtb[:-4] + '.dts'

                with open(os.path.join(dst_dir,dts), 'a') as dts_file:
                    dts_file.write('\n#include "%s"\n' % dst_dtsi)
}

do_patch[postfuncs] += "do_setup_cmem"
do_patch[vardeps] += "${CMEM_VARS}"
