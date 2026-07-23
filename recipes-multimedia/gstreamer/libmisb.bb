DESCRIPTION = "MISB metadata library with converter tool and Python support"
HOMEPAGE = "https://developer.ridgerun.com/wiki/index.php/LibMISB"
SECTION = "multimedia"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = ".*"
SRCBRANCH ?= "main"
SRCREV = "${AUTOREV}"

SRC_URI = "git://git@gitlab.ridgerun.com/ridgerun/orders/${RR_CUSTOMER_GITLAB_ORDER_DIR}/libmisb.git;protocol=ssh;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

inherit meson pkgconfig python3native rr_proprietary

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
        cp -ra ${S}/examples/. ${D}${datadir}/${BPN}/examples/
    fi
}

FILES:${PN} += " \
    ${bindir} \
    ${libdir}/lib*.so* \
    ${datadir}/${BPN}/examples \
    ${PYTHON_SITEPACKAGES_DIR}/lib*.so \
"

FILES:${PN}-dev += " \
    ${includedir}/libmisb-0.0 \
    ${libdir}/lib*.so* \
    ${libdir}/pkgconfig/misb-0.0.pc \
"
