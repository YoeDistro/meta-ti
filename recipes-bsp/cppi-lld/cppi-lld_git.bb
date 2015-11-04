DESCRIPTION = "TI CPPI Module low level driver"
COMPATIBLE_MACHINE = "keystone"

DEPENDS = "common-csl-ip rm-lld qmss-lld"

include cppi-lld.inc

PR = "${INC_PR}.0"

SRC_URI += "file://init_cppi.sh"

inherit update-rc.d

INITSCRIPT_NAME = "init_cppi.sh"
INITSCRIPT_PARAMS = "defaults 10"

do_compile () {
#   Now build the lld in the updated directory
	make -f makefile_armv7 clean PDK_INSTALL_PATH=${STAGING_INCDIR}
	for device in ${DEVICELIST}
	do
		make -f makefile_armv7 lib PDK_INSTALL_PATH=${STAGING_INCDIR}  DEVICE="${device}"
	done
}

do_install () {
	make -f makefile_armv7 install PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_INC_BASE_DIR=${D}/${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
#   Set the generic device library symbolic link to default k2h
    cd ${D}${libdir}
    ln -sf libcppi_k2h.so.1.0.0 libcppi_device.so.1
    ln -sf libcppi_device.so.1 libcppi_device.so
#   Copy init scripts
    install -d ${D}${sysconfdir}/init.d/
    install -c -m 755 ${WORKDIR}/init_cppi.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}
