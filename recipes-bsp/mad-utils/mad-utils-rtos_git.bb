SUMMARY = "Utility for loading and running multicore applications"
DESCRIPTION = "Multicore Application Deployment (MAD) utility is used \
for loading and running a multicore application on an embedded \
processor with multiple cores. This utility package contains the \
scripts to combine multiple applications for different cores into a \
single binary as well as loadable application to parse and execute the applications on different cores."

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://README.txt;md5=44b2180aec0657f6b0e42e5611c0bafb"

require recipes-ti/includes/ti-paths.inc

DEPENDS = "ti-cgt6x-7-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "c66x"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/mad-utils.git;protocol=git;branch=${BRANCH}"
SRCREV = "2458510f76eddfa837c2b83448965e233156944b"
PR = "r0"

S = "${WORKDIR}/git"

export C6X_BASE_DIR="${TI_CGT6X_7_INSTALL_DIR}"
PATH_prepend = "${TI_CGT6X_7_INSTALL_DIR}/bin:"

DEVICE = ""
DEVICE_c665x-evm = "C6657"
DEVICE_c667x-evm = "C6678"

ENDIAN = ""
ENDIAN_c665x-evm = "little big"
ENDIAN_c667x-evm = "little big"

do_compile() {
    cd mad-loader
    for e in ${ENDIAN}
    do
        mkdir -p bin/${DEVICE}/${e}
        make -C mal/malLib/build clean all DEVICE=${DEVICE} ENDIAN=${e} C_DIR=${C6X_BASE_DIR}
        make -C mal/malApp/build clean all DEVICE=${DEVICE} ENDIAN=${e} C_DIR=${C6X_BASE_DIR}
        cp mal/malApp/build/mal_app.exe bin/${DEVICE}/${e}
        make -C nmlLoader/build clean all DEVICE=${DEVICE} ENDIAN=${e} C_DIR=${C6X_BASE_DIR}
        cp nmlLoader/build/nml.exe bin/${DEVICE}/${e}
    done
    cd -
}

do_install() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/boot/mad-utils
    cp -rP --preserve=mode,links,timestamps --no-preserve=ownership * ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/boot/mad-utils
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages/ti/boot/mad-utils"

INSANE_SKIP_${PN} += "arch staticdev ldflags file-rdeps"
