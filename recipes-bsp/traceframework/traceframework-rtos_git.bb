inherit ti-pdk

require traceframework.inc

PR = "${INC_PR}.2"

XDCPATH_append = ";${UIA_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "Trace Framework"
