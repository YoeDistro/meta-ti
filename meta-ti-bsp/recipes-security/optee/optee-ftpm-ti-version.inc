PV = "4.7.0+git"
OPTEE_TA_GIT_TAG = "${@'branch=master' if d.getVar('PV') == 'git' or d.getVar('PV').endswith('+git') else 'tag=%s;nobranch=1' % d.getVar('PV')}"
SRCREV_optee-ta = "ce33372ab772e879826361a1ca91126260bd9be1"
