TI_PDK_GIT_URI ?= "git://git.ti.com/processor-sdk/pdk.git"
TI_PDK_GIT_BRANCH ?= "release/PROCESSOR-SDK_06.02.00"
TI_PDK_GIT_PROTOCOL ?= "git"
TI_PDK_SRCREV ?= "2357d1436f1b2c3b5f1d0c9c94e045d33b63f3fe"

TI_PDK_VERSION ?= "2019.12.2"

PV = "${TI_PDK_VERSION}"

TI_PDK_COMP ?= ""
TI_PDK_COMP_PATH = "${@'${TI_PDK_COMP}'.replace('.','/')}"

TI_PDK_SOURCE_PN = "ti-pdk-source"
TI_PDK_SOURCE_WORKDIR = "${TMPDIR}/work-shared/ti-pdk-${PV}"
TI_PDK_SOURCE = "${TI_PDK_SOURCE_WORKDIR}/git"

S = "${WORKDIR}/git/${TI_PDK_COMP_PATH}"

# Hard-link only required sources from PDK
python do_unpack_append() {
    if len(d.getVar('TI_PDK_COMP') or '') > 0:
        import shutil

        # Get src/dst paths
        src = os.path.join(d.getVar('TI_PDK_SOURCE'),'packages',d.getVar('TI_PDK_COMP_PATH'))
        s = d.getVar('S')

        # Set up the directory structure, except for the root of the sources
        # hard-linked.
        bb.utils.mkdirhier(s)
        os.rmdir(s)

        # Recursively hard-link the sources
        shutil.copytree(src, s, copy_function=os.link)
}

# Make sure that ti-pdk-source is unpacked before we set up the hardlinks.
python __anonymous () {
    pn = d.getVar('PN')
    pdk_src_pn = d.getVar('TI_PDK_SOURCE_PN')
    if pn != pdk_src_pn:
        d.appendVarFlag('do_unpack', 'depends', ' ${TI_PDK_SOURCE_PN}:do_unpack')
}
