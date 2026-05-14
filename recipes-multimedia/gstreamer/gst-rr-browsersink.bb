SUMMARY = "gst-rr-browsersink"
DESCRIPTION = "C/C++ multimedia product using OpenCV, GStreamer, Qt5 Multimedia, and JsonCpp"
SECTION = "multimedia"

LICENSE = "CLOSED"

SRCBRANCH ?= "main"
SRCREV = "${AUTOREV}"
SRC_URI = "git://git@gitlab.ridgerun.com/ridgerun/orders/${RR_CUSTOMER_GITLAB_ORDER_DIR}/gst-rr-browser-sink.git;protocol=ssh;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

inherit meson pkgconfig

DEPENDS = " \
    meson-native \
    glib-2.0 \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    libsoup-3.0 \
    libnice \
    openssl \
"

PACKAGECONFIG ??= ""

# If your project uses different Meson option names, change these.
EXTRA_OEMESON += " \
    --prefix=${prefix} \
"

RDEPENDS:${PN} += " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-ugly \
    gstreamer1.0-libav \
    gstreamer1.0-plugins-base-app \
    libnice \
    openssl \
"

FILES:${PN} += " \
    ${bindir}/stunbdc \
    ${bindir}/stund \
    ${libdir}/libnice.so.* \
    ${libdir}/gstreamer-1.0/libgstnice.so \
    ${libdir}/gstreamer-1.0/libgstrrbrowsersink.so \
    ${libdir}/girepository-1.0/Nice-0.1.typelib \
"
FILES:${PN}-dev += " \
    ${includedir}/nice \
    ${includedir}/stun \
    ${libdir}/libnice.so \
    ${libdir}/pkgconfig/nice.pc \
    ${datadir}/gir-1.0/Nice-0.1.gir \
"
