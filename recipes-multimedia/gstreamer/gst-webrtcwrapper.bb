SUMMARY = "GStreamer WebRTCWrapper Plugin"
DESCRIPTION = "GStreamer plugin for WebRTCWrapper "
HOMEPAGE = "https://developer.ridgerun.com/wiki/index.php/GStreamer_WebRTC_Wrapper"
SECTION = "multimedia"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = ".*"

DEPENDS = " \
    glib-2.0 \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-bad \
    libsoup-2.4 \
    json-glib \
    openssl \
    libsrtp \
    libnice \
"

RDEPENDS:${PN} += " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-bad \
    libnice \
    libsrtp \
    json-glib \
    libsoup-2.4 \
    openssl \
"

SRCBRANCH ?= "master"
SRCREV = "${AUTOREV}"
SRC_URI = "git://git@gitlab.ridgerun.com/ridgerun/orders/${RR_CUSTOMER_GITLAB_ORDER_DIR}/gst-webrtc-wrapper.git;protocol=ssh;branch=${SRCBRANCH}"
S = "${WORKDIR}/git"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += " \
    ${bindir}/* \
    ${libdir}/libgstwebrtcwrapper-1.0.so \
    ${libdir}/gstreamer-1.0/*.so \
"

# Development files
FILES:${PN}-dev += " \
    ${includedir} \
    ${libdir}/pkgconfig \
    ${libdir}/gstreamer-1.0/pkgconfig \
    ${libdir}/gstreamer-1.0/pkgconfig/*.pc \
"


PACKAGECONFIG ??= ""
PACKAGECONFIG[doc] = "-Denable-gtk-doc=true,-Denable-gtk-doc=false,gtk-doc-native"
PACKAGECONFIG[examples] = "-Denable-examples=enabled,-Denable-examples=disabled"
PACKAGECONFIG[tests] = "-Denable-tests=enabled,-Denable-tests=disabled"
PACKAGECONFIG[eval] = "-Denable-eval=true,-Denable-eval=false"

# Meson build options
EXTRA_OEMESON = ""

# Inherit necessary classes
inherit meson pkgconfig rr_proprietary

