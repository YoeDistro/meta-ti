PV = "4.10.0+git"
OPTEE_TA_GIT_TAG = "${@'branch=master' if d.getVar('PV') == 'git' or d.getVar('PV').endswith('+git') else 'tag=%s;nobranch=1' % d.getVar('PV')}"
SRCREV_optee-ta = "e3b1e426a605e0537a3c3c9c24228eb22e354b80"
