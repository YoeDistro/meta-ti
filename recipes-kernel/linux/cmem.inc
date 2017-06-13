# Add concept of machine variants to split DTBs into subsets
#
# Use these for temporary overrides
CMEM_MACHINE = "${MACHINE}"
CMEM_MACHINE_am57xx-evm = "am571x am572x"
CMEM_MACHINE_am57xx-hs-evm = "am571x am572x"
CMEM_MACHINE_dra7xx-evm = "dra71x dra72x dra74x"
CMEM_MACHINE_dra7xx-hs-evm = "dra71x dra72x dra74x"

# Set cmem.dtsi per machine or machine variant
CMEM_DTSI = "cmem.dtsi"
CMEM_DTSI_am571x = "cmem-am571x.dtsi"
CMEM_DTSI_dra71x = "cmem-dra71x.dtsi"
CMEM_DTSI_dra72x = "cmem-dra72x.dtsi"

# Split device trees between variants
CMEM_DEVICETREE = "${KERNEL_DEVICETREE}"
CMEM_DEVICETREE_am571x = "am571x-idk.dtb am571x-idk-lcd-osd101t2045.dtb am571x-idk-lcd-osd101t2587.dtb"
CMEM_DEVICETREE_am572x = "am57xx-beagle-x15.dtb am57xx-beagle-x15-revb1.dtb \
                          am57xx-evm.dtb am57xx-evm-cam-mt9t111.dtb am57xx-evm-cam-ov10635.dtb \
                          am57xx-evm-reva3.dtb am57xx-evm-reva3-cam-mt9t111.dtb am57xx-evm-reva3-cam-ov10635.dtb \
                          am572x-idk.dtb am572x-idk-lcd-osd101t2045.dtb am572x-idk-lcd-osd101t2587.dtb"
CMEM_DEVICETREE_dra71x = "dra71-evm.dtb dra71-evm-lcd-auo-g101evn01.0.dtb"
CMEM_DEVICETREE_dra72x = "dra72-evm.dtb dra72-evm-lcd-lg.dtb dra72-evm-lcd-osd101t2045.dtb dra72-evm-lcd-osd101t2587.dtb \
                          dra72-evm-revc.dtb dra72-evm-revc-lcd-osd101t2045.dtb dra72-evm-revc-lcd-osd101t2587.dtb"
CMEM_DEVICETREE_dra74x = "dra7-evm.dtb dra7-evm-lcd-lg.dtb dra7-evm-lcd-osd101t2045.dtb dra7-evm-lcd-osd101t2587.dtb"

# Flag to enable CMEM injection
RESERVE_CMEM ?= "0"

# Add correct cmem.dtsi to SRC_URI for each variant for a given machine
python do_unpack() {
    old_overrides = d.getVar('OVERRIDES', False)

    # Initialize with empty string to simplify logic to append to SRC_URI
    cmem_dtsi = set([''])

    for cmem_machine in (d.getVar('CMEM_MACHINE', True) or '').split():
        # Create copy of data for additional override
        localdata = bb.data.createCopy(d)
        localdata.setVar('OVERRIDES', '%s:%s' % (cmem_machine, old_overrides))
        bb.data.update_data(localdata)

        cmem_dtsi.add(localdata.getVar('CMEM_DTSI', True))

    d.appendVar('SRC_URI', ' file://'.join(cmem_dtsi))
    bb.build.exec_func('base_do_unpack', d)
}

python do_setup_cmem() {
    import shutil

    old_overrides = d.getVar('OVERRIDES', False)

    if d.getVar('RESERVE_CMEM', True) is '1':
        for cmem_machine in (d.getVar('CMEM_MACHINE', True) or '').split():
            # Create copy of data for additional override
            localdata = bb.data.createCopy(d)
            localdata.setVar('OVERRIDES', '%s:%s' % (cmem_machine, old_overrides))
            bb.data.update_data(localdata)

            # Get source directory and dtsi filename
            src_dir  = localdata.getVar('WORKDIR', True)
            src_dtsi = localdata.getVar('CMEM_DTSI', True)

            # Get destination directory and destination dtsi filename which adds
            #   the MACHINE prefix.
            dst_dir  = os.path.join(localdata.getVar('S', True), 'arch/arm/boot/dts')
            dst_dtsi = localdata.expand('${MACHINE}-${CMEM_DTSI}')

            # Copy cmem.dtsi into source tree
            shutil.copy(os.path.join(src_dir,src_dtsi), os.path.join(dst_dir,dst_dtsi))

            # Inject dtsi into each dts in list
            for dtb in (localdata.getVar('CMEM_DEVICETREE', True) or '').split():
                dts = dtb[:-4] + '.dts'

                with open(os.path.join(dst_dir,dts), 'a') as dts_file:
                    dts_file.write('\n#include "%s"\n' % dst_dtsi)
}

do_patch[postfuncs] += "do_setup_cmem"
