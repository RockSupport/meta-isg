require recipes-kernel/linux/linux-yocto.inc
SUMMARY = "isg-503 kernel"
DESCRIPTION = "Rocktech isg-503 kernel"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/4.4.194:"

LINUX_VERSION = "4.4.194"

SRC_URI = " \ 
	git://github.com/rockchip-linux/kernel.git;branch=develop-4.4 \
	file://0002-recktech-dts-config.patch \
	file://0002-recktech-dts-uart5_fix.patch \
	file://0003-recktech-add-mcp9800.patch \
	file://0004-recktech-add-rtc-rx8010.patch \
	file://0005-recktech-fix-wifierror.patch \
	file://0004-recktech-modify_ttyName.patch \
	"

SRCREV = "eed2c2aa9f3899876861c7500ec5b2e7550b819d"

PV = "${LINUX_VERSION}+git${SRCPV}"

S = "${WORKDIR}/git"

KCONFIG_MODE="--alldefconfig"

COMPATIBLE_MACHINE = "(isg-503)"

SRC_URI += " \
        file://defconfig \
		"
		
inherit python3-dir

DEPENDS += "openssl-native lz4-native ${PYTHON_PN}-native"

inherit auto-patch


# Make sure we use /usr/bin/env ${PYTHON_PN} for scripts
do_patch_append() {
	for s in `grep -rIl python ${S}/scripts`; do
		sed -i -e '1s|^#!.*python[23]*|#!/usr/bin/env ${PYTHON_PN}|' $s
	done
}		
deltask kernel_configme
# Comment the below line to mount file system from eMMC
# SRC_URI += "file://0008-Modified-Boot-Arguments-to-mount-fileystem-from-SD-c.patch")"


