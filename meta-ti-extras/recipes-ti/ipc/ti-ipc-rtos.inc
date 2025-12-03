require recipes-ti/includes/ti-paths.inc

TI_IPC_EXAMPLES_GIT_URI = "git://git.ti.com/git/ipc/ipc-examples.git"
TI_IPC_EXAMPLES_DEST_SUFFIX = "${BB_GIT_DEFAULT_DESTSUFFIX}/ipc-examples"
TI_IPC_EXAMPLES_GIT_PROTOCOL = "https"
TI_IPC_EXAMPLES_GIT_BRANCH = "master"
TI_IPC_EXAMPLES_NAME = "ipc-examples"

SRC_URI += "${TI_IPC_EXAMPLES_GIT_URI};\
destsuffix=${TI_IPC_EXAMPLES_DEST_SUFFIX};\
protocol=${TI_IPC_EXAMPLES_GIT_PROTOCOL};\
branch=${TI_IPC_EXAMPLES_GIT_BRANCH};\
name=${TI_IPC_EXAMPLES_NAME}"

TI_IPC_METADATA_GIT_URI = "git://git.ti.com/git/ipc/ipc-metadata.git"
TI_IPC_METADATA_DEST_SUFFIX = "${BB_GIT_DEFAULT_DESTSUFFIX}/ipc-metadata"
TI_IPC_METADATA_GIT_PROTOCOL = "https"
TI_IPC_METADATA_GIT_BRANCH = "master"
TI_IPC_METADATA_NAME = "ipc-metadata"

SRC_URI += "${TI_IPC_METADATA_GIT_URI};\
destsuffix=${TI_IPC_METADATA_DEST_SUFFIX};\
protocol=${TI_IPC_METADATA_GIT_PROTOCOL};\
branch=${TI_IPC_METADATA_GIT_BRANCH};\
name=${TI_IPC_METADATA_NAME}"

# Corresponds to tag: 3.51.00.00
TI_IPC_METADATA_SRCREV = "1bf668fd6b0ec2ef6956fa55b8484d0b21a9f9e2"
# Corresponds to tag: 3.51.00.00
TI_IPC_EXAMPLES_SRCREV = "4707fcbbe0d136e781c4a7ffdc072be407c34358"

SRCREV_FORMAT = "default"
SRCREV_ipc-metadata = "${TI_IPC_METADATA_SRCREV}"
SRCREV_ipc-examples = "${TI_IPC_EXAMPLES_SRCREV}"

S_ipc-examples = "${S}/ipc-examples"
S_ipc-metadata = "${S}/ipc-metadata"

RELEASE_TYPE = "GA"
RELEASE_SUFFIX = ""

PR = "${INC_PR}.r0"

DEPENDS:append:am57xx = " ti-cgt6x-native \
                          ti-cgt-arm-native \
                          gcc-arm-baremetal-native \
"
DEPENDS:append:omapl138 = " ti-cgt6x-native \
"

IPC_TARGETS = ""

IPC_TARGETS:am57xx = "\
    gnu.targets.arm.A15F="${GCC_ARM_NONE_TOOLCHAIN}" \
    ti.targets.elf.C66="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.elf.C66_big_endian="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.arm.elf.M4="${M4_TOOLCHAIN_INSTALL_DIR}" \
"

IPC_TARGETS:omapl138 = "\
    ti.targets.elf.C674="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
"

PARALLEL_XDC = "${@oe.utils.parallel_make_argument(d, '--jobs=%d')}"
EXTRA_OEMAKE = "\
    PLATFORM=${PLATFORM} \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR}" \
    ${IPC_TARGETS} \
    ${PARALLEL_XDC} \
    JAVA_TOOL_OPTIONS=-Xss2560k \
"

