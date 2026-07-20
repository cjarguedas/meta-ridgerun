SUMMARY = "libmisb"
DESCRIPTION = "MISB metadata library with converter tool and Python support"
SECTION = "multimedia"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = ".*"
SRCBRANCH ?= "main"
SRCREV = "${AUTOREV}"

SRC_URI = "git://git@gitlab.ridgerun.com/ridgerun/orders/${RR_CUSTOMER_GITLAB_ORDER_DIR}/libmisb.git;protocol=ssh;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

inherit meson pkgconfig python3native

DEPENDS = " \
    jansson \
    cpputest \
    python3 \
    python3-pybind11-native \
    cmake-native \
    doxygen-native \
"

EXTRA_OEMESON += " \
    -Dexamples=enabled \
    -Dtests=disabled \
    -Ddocs=disabled \
    -Deval=disabled \
    -Dpython=enabled \
    -Ddeveloper-mode=false \
"

RDEPENDS:${PN} += " \
    jansson \
    python3-core \
"

# If upstream installs into Debian multiarch libdir, normalize it to Yocto's ${libdir}.
do_install:append() {
    if [ -d ${D}${prefix}/lib/aarch64-linux-gnu ]; then
        install -d ${D}${libdir}
        cp -a ${D}${prefix}/lib/aarch64-linux-gnu/* ${D}${libdir}/
        rm -rf ${D}${prefix}/lib/aarch64-linux-gnu
    fi

    # Install example source/content onto the target.
    # Adjust "examples" to the exact directory name if different.
    if [ -d ${S}/examples ]; then
        install -d ${D}${datadir}/${BPN}/examples
        cp -r ${S}/examples/* ${D}${datadir}/${BPN}/examples/
    fi
}

FILES:${PN} += " \
    ${bindir}/misb-converter \
    ${bindir}/misb_ST0601_sample.json \
    ${libdir}/libformatter.so.0 \
    ${libdir}/libformatter.so.0.1.0 \
    ${libdir}/libmisb-0.0.so.0 \
    ${libdir}/libmisb-0.0.so.0.1.0 \
    ${datadir}/${BPN}/examples \
     ${PYTHON_SITEPACKAGES_DIR}/libmisb*.so \
"

FILES:${PN}-dev += " \
    ${includedir}/libmisb-0.0 \
    ${libdir}/libformatter.so \
    ${libdir}/libmisb-0.0.so \
    ${libdir}/pkgconfig/misb-0.0.pc \
"
