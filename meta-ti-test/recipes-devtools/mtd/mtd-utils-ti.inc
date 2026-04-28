PR:append = ".ti2"

SRC_URI:remove = "git://git.infradead.org/mtd-utils.git;branch=master \
"

SRC_URI:prepend = "git://git.infraroot.at/mtd-utils.git;branch=master;protocol=https \
"

EXTRA_OEMAKE = "'CC=${CC} ${CFLAGS} ${@bb.utils.contains('PACKAGECONFIG', 'xattr', '', '-DWITHOUT_XATTR', d)} -I${S}/include' 'RANLIB=${RANLIB}' 'AR=${AR}' 'BUILDDIR=${S}'"

do_compile:append() {
	oe_runmake tests
}

do_install:append() {
	install -m 0755 io_update ${D}${sbindir}/
	install -m 0755 volrefcnt ${D}${sbindir}/
	install -m 0755 integ ${D}${sbindir}/
	install -m 0755 io_paral ${D}${sbindir}/
	install -m 0755 io_read ${D}${sbindir}/
	install -m 0755 io_basic ${D}${sbindir}/
	install -m 0755 mkvol_basic ${D}${sbindir}/
	install -m 0755 mkvol_bad ${D}${sbindir}/
	install -m 0755 mkvol_paral ${D}${sbindir}/
	install -m 0755 rsvol ${D}${sbindir}/
	install -m 0755 tests/ubi-tests/runubitests.sh ${D}${sbindir}/
	install -m 0755 tests/ubi-tests/ubi-stress-test.sh ${D}${sbindir}/
}

PACKAGES =+ "mtd-utils-ubifs-tests"

FILES:mtd-utils-ubifs-tests = " \
${sbindir}/io_update \
${sbindir}/volrefcnt \
${sbindir}/integ \
${sbindir}/io_paral \
${sbindir}/io_read \
${sbindir}/io_basic \
${sbindir}/mkvol_basic \
${sbindir}/mkvol_bad \
${sbindir}/mkvol_paral \
${sbindir}/rsvol \
${sbindir}/runubitests.sh \
${sbindir}/ubi-stress-test.sh \
"
