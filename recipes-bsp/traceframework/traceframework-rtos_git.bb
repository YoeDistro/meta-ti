inherit ti-pdk

require traceframework.inc

PR = "${INC_PR}.1"

DEPENDS_append = " uia \
"

XDCPATH_append = ";${UIA_INSTALL_DIR}/packages"
