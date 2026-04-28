SUMMARY = "Linux Kernel PCI test"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

BRANCH = "ti-linux-5.10.y"
SRCREV = "73aa709ca10103b61fba3a07471dbb4dcb56db45"
SRC_URI = "git://git.ti.com/git/ti-linux-kernel/ti-linux-kernel.git;protocol=https;branch=${BRANCH}"

do_compile () {
	cd ${S}/tools/pci
	${CC} ${CFLAGS} ${LDFLAGS} -o pcitest pcitest.c
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/tools/pci/pcitest ${D}${bindir}
	install -m 0755 ${S}/tools/pci/pcitest.sh ${D}${bindir}
}
