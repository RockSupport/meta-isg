DESCRIPTION = "Rocktech ISG503 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

require u-boot-rockchip.inc

DEPENDS += "bison-native"

SRC_URI = " \
    git://github.com/u-boot/u-boot \
"

S = "${WORKDIR}/git"

do_install_append() {
    install -d ${D}/boot
    install -c -m 0644 ${B}/idbloader.bin ${B}/uboot.img ${D}/boot
}

do_deploy_append() {
    install ${B}/idbloader.bin ${DEPLOYDIR}
    install ${B}/uboot.img ${DEPLOYDIR}
}
