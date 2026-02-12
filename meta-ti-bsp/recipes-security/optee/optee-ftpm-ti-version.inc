PV = "4.9.0+git"
OPTEE_TA_GIT_TAG = "${@'branch=master' if d.getVar('PV') == 'git' or d.getVar('PV').endswith('+git') else 'tag=%s;nobranch=1' % d.getVar('PV')}"
SRCREV_optee-ta = "a09269b15de635e1816fe832e26adfbfb44c5455"
