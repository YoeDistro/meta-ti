PV = "4.11.0+git"
OPTEE_TA_GIT_TAG = "${@'branch=master' if d.getVar('PV') == 'git' or d.getVar('PV').endswith('+git') else 'tag=%s;nobranch=1' % d.getVar('PV')}"
SRCREV_optee-ta = "f09f4cdad21028289d4e8fc573893b75f7228dc7"
