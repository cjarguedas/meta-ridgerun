SUMMARY = "Meson build system"
DESCRIPTION = "A high-performance build system"
HOMEPAGE = "https://mesonbuild.com"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://github.com/mesonbuild/meson/releases/download/${PV}/meson-${PV}.tar.gz"
SRC_URI[sha256sum] = "999b65f21c03541cf11365489c1fad22e2418bb0c3d50ca61139f2eec09d5496"

UPSTREAM_CHECK_URI = "https://github.com/mesonbuild/meson/releases"

S = "${WORKDIR}/meson-${PV}"

inherit setuptools3 python3native

RDEPENDS:${PN} = " \
    python3-core \
    python3-modules \
    ninja \
"

BBCLASSEXTEND = "native nativesdk"
