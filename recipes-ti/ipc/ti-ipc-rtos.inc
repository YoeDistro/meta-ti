require recipes-ti/includes/ti-paths.inc

TI_IPC_EXAMPLES_GIT_URI = "git://git.ti.com/ipc/ipc-examples.git"
TI_IPC_EXAMPLES_DEST_SUFFIX = "git/ipc-examples"
TI_IPC_EXAMPLES_GIT_PROTOCOL = "git"
TI_IPC_EXAMPLES_GIT_BRANCH = "master"
TI_IPC_EXAMPLES_NAME = "ipc-examples"

SRC_URI += "${TI_IPC_EXAMPLES_GIT_URI};\
destsuffix=${TI_IPC_EXAMPLES_DEST_SUFFIX};\
protocol=${TI_IPC_EXAMPLES_GIT_PROTOCOL};\
branch=${TI_IPC_EXAMPLES_GIT_BRANCH};\
name=${TI_IPC_EXAMPLES_NAME}"

TI_IPC_METADATA_GIT_URI = "git://git.ti.com/ipc/ipc-metadata.git"
TI_IPC_METADATA_DEST_SUFFIX = "git/ipc-metadata"
TI_IPC_METADATA_GIT_PROTOCOL = "git"
TI_IPC_METADATA_GIT_BRANCH = "master"
TI_IPC_METADATA_NAME = "ipc-metadata"

SRC_URI += "${TI_IPC_METADATA_GIT_URI};\
destsuffix=${TI_IPC_METADATA_DEST_SUFFIX};\
protocol=${TI_IPC_METADATA_GIT_PROTOCOL};\
branch=${TI_IPC_METADATA_GIT_BRANCH};\
name=${TI_IPC_METADATA_NAME}"

# Corresponds to tag: 3.50.02.02
TI_IPC_METADATA_SRCREV = "171ed5b4cfc73fd2ebfeefe42249bec18f475613"
# Corresponds to tag: 3.50.01.01
TI_IPC_EXAMPLES_SRCREV = "71f6e59a6b2c7e20c2656e5000f318339ded2f3c"

SRCREV_ipc-metadata = "${TI_IPC_METADATA_SRCREV}"
SRCREV_ipc-examples = "${TI_IPC_EXAMPLES_SRCREV}"

S_ipc-examples = "${WORKDIR}/git/ipc-examples"
S_ipc-metadata = "${WORKDIR}/git/ipc-metadata"

RELEASE_TYPE = "GA"
RELEASE_SUFFIX = ""

PR = "${INC_PR}.r0"

DEPENDS_append_keystone = " ti-cgt6x-native \
                            gcc-arm-none-eabi-native \
"
DEPENDS_append_omap-a15 = " ti-cgt6x-native \
                            ti-cgt-arm-native \
                            gcc-arm-none-eabi-native \
"
DEPENDS_append_omapl138 = " ti-cgt6x-native \
"
DEPENDS_append_c66x = " ti-cgt6x-native \
"

DEPENDS_append_am65xx-evm = " ti-cgt-arm-native \
                              gcc-linaro-baremetal-aarch64-native"

IPC_TARGETS = ""

IPC_TARGETS_omap-a15 = "\
    gnu.targets.arm.A15F="${GCC_ARM_NONE_TOOLCHAIN}" \
    ti.targets.elf.C66="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.elf.C66_big_endian="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.arm.elf.M4="${M4_TOOLCHAIN_INSTALL_DIR}" \
"

IPC_TARGETS_omapl138 = "\
    ti.targets.elf.C674="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
"

IPC_TARGETS_keystone = " \
    gnu.targets.arm.A15F="${GCC_ARM_NONE_TOOLCHAIN}" \
    ti.targets.elf.C66="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.elf.C66_big_endian="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
"
IPC_TARGETS_c66x = " \
    ti.targets.elf.C66="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
    ti.targets.elf.C66_big_endian="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
"

IPC_TARGETS_am65xx-evm = "\
    gnu.targets.arm.A53F="${GCC_LINARO_BAREMETAL_AARCH64_TOOLCHAIN}" \
    ti.targets.arm.elf.R5F="${M4_TOOLCHAIN_INSTALL_DIR}" \
"

EXTRA_OEMAKE = "\
    PLATFORM=${PLATFORM} \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR}" \
    ${IPC_TARGETS} \
    JOBS="--jobs=${BB_NUMBER_THREADS}" \
    JAVA_TOOL_OPTIONS=-Xss2560k \
"
EXTRA_OEMAKE_append_am65xx-evm = " PDK_INSTALL_DIR=${PDK_INSTALL_DIR}"

