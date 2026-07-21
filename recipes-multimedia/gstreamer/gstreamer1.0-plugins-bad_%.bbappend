# Usually needed by WebRTC pipelines
PACKAGECONFIG:append = " dtls srtp sctp webrtc"

FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-bad:"

SRC_URI += " \
	file://0001-Avoid-selecting-PCR-on-metadata-stream.patch;striplevel=3 \
	file://0002-Configure-aggregator-to-avoid-waiting-on-sparse-stre.patch;striplevel=3 \
	file://0003-Support-sync-metadata.patch;striplevel=3 \
	file://0004-Fix-timestamp-issue.patch;striplevel=3 \
	file://0005-Add-eval-mode.patch;striplevel=3 \
	file://0006-Ignore-error-if-processing-KLV.patch;striplevel=3 \
"

