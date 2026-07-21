SUMMARY = "Gst-In Band Metadata"
DESCRIPTION = "GStreamer product using GObject Introspection and patched GStreamer RTMP/FLV metadata support"
SECTION = "multimedia"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = ".*"

SRCBRANCH ?= "master"
SRCREV = "${AUTOREV}"

SRC_URI = "git://git@gitlab.ridgerun.com/ridgerun/orders/${RR_CUSTOMER_GITLAB_ORDER_DIR}/gst-metadata.git;protocol=ssh;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= ""
PACKAGECONFIG[developer] = "--enable-developer,--disable-developer"

inherit autotools pkgconfig gettext

DEPENDS = " \
    libtool \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad \
"

RDEPENDS:${PN} += " \
    libtool \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad \
"

FILES:${PN} += " \
    ${libdir}/gstreamer-1.0/*.so \
    ${bindir}/* \
    ${datadir}/${BPN} \
"

FILES:${PN}-dev += " \
    ${includedir} \
    ${libdir}/pkgconfig \
"

