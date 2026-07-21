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
"

PACKAGECONFIG ??= "examples python"

PACKAGECONFIG[examples] = "-Dexamples=enabled,-Dexamples=disabled,cmake-native"
PACKAGECONFIG[tests] = "-Dtests=enabled,-Dtests=disabled,cpputest"
PACKAGECONFIG[docs] = "-Ddocs=enabled,-Ddocs=disabled,doxygen-native"
PACKAGECONFIG[eval] = "-Deval=enabled,-Deval=disabled"
PACKAGECONFIG[python] = \
    "-Dpython=enabled,-Dpython=disabled,python3-pybind11-native python3"

EXTRA_OEMESON += "-Ddeveloper-mode=false"

RDEPENDS:${PN} += " \
    jansson \
    python3-core \
"

# If upstream installs into Debian multiarch libdir, normalize it to Yocto's ${libdir}.
do_install:append() {
    for multiarch_dir in ${D}${prefix}/lib/*-linux-gnu*; do
        if [ -d "$multiarch_dir" ] && [ "$multiarch_dir" != "${D}${libdir}" ]; then
            install -d ${D}${libdir}
            cp -a "$multiarch_dir"/. ${D}${libdir}/
            rm -rf "$multiarch_dir"
        fi
    done

    if [ -d ${S}/examples ]; then
        install -d ${D}${datadir}/${BPN}/examples
        cp -r ${S}/examples/. ${D}${datadir}/${BPN}/examples/
    fi
}

FILES:${PN} += " \
    ${bindir}/misb-converter \
    ${bindir}/misb_ST0601_sample.json \
    ${libdir}/libformatter.so.* \
    ${libdir}/libmisb-0.0.so.* \
    ${datadir}/${BPN}/examples \
    ${PYTHON_SITEPACKAGES_DIR}/libmisb*.so \
"

FILES:${PN}-dev += " \
    ${includedir}/libmisb-0.0 \
    ${libdir}/libformatter.so \
    ${libdir}/libmisb-0.0.so \
    ${libdir}/pkgconfig/misb-0.0.pc \
"
