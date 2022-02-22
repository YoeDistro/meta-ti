# This file defines function used for unpacking the .bin file downloaded over
# the http and display EULA.
#  BINFILE - name of the install jammer .bin file
#  TARFILE - name of the tar file inside the install jammer
#  TI_BIN_UNPK_CMDS   - contains list of commands separated with colon to be 
#                       passed while unpacking the bin file. The keyword 
#                       workdir expands to WORKDIR and commands are appendded
#                       with '\n'. Eg. TI_BIN_UNPK_CMDS="Y:Y: qY:workdir"
#  TI_BIN_UNPK_WDEXT  - This variable extends workdir path, if user wants to put
#                       the output in some internal directory

require recipes-ti/includes/ti-unpack.inc

TI_BIN_UNPK_ARGS = "--mode console"

