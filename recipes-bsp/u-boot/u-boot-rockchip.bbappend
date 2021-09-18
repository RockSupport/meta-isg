UBOOT_KCONFIG_SUPPORT = "1"
inherit resin-u-boot

FILESEXTRAPATHS_append := ":${THISDIR}/files"

# rework meta-resin patch whose context is different now in u-boot v2019.4
SRC_URI_remove = "file://resin-specific-env-integration-kconfig.patch"

SRC_URI_append_isg-503 = " \
    file://0000-rocktech-isg-503-spl.patch \
	file://0001-rocktech-isg-503-dts.patch \
	file://0003-rocktech-isg-503-px30.patch \
	file://0004-rocktech-isg-503-ota.patch \
	file://0005-rocktech-isg-503-gmac.patch \
"

SRCREV_isg-503  = "v2019.10"


do_compile_append_isg-503() {
    tools/mkimage -n px30 -T rksd -d ${DEPLOY_DIR_IMAGE}/rkbin/px30_ddr_333MHz_v1.10.bin idbloader.bin
    cat ${DEPLOY_DIR_IMAGE}/rkbin/px30_miniloader_v1.15.bin >> idbloader.bin
    ${DEPLOY_DIR_IMAGE}/rkbin/tools/loaderimage --pack --uboot ./u-boot-dtb.bin uboot.img 0x200000
}
