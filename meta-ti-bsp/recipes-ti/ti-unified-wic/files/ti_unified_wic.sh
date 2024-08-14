#!/usr/bin/env bash

glRealBin=$(cd "$(dirname "$0")"; pwd)
glScript=$(basename "$0")

glDeployDir=""
glImage=""
glVariant=""
glMultiConfig=""
glOutput=""

glListOptions="false"
glListImages="false"
glListVariants="false"
glListMultiConfigs="false"
glCleanWorkDir="false"

glConfigFN="ti_unified_wic.cfg"

glRunDir=$(pwd)
glWorkDir="${glRunDir}/.ti_unified_wic.workdir"

glOeCoreRepoDN="oe-core-repo"
glOeCoreRepoFPDN="${glWorkDir}/${glOeCoreRepoDN}"

glBmaptoolRepoDN="bmaptool-repo"
glBmaptoolRepoFPDN="${glWorkDir}/${glBmaptoolRepoDN}"


read_config()
{
    local loConfigFPFN="${glDeployDir}/${glConfigFN}"

    glConfigMachine=""
    glConfigVariants=""
    glConfigMultiConfigs=""
    glConfigBootloaders=""

    local loSection=""

    while CFG_IN= read -r lpLine
    do
        case "$lpLine" in
            \[machine\])      loSection="machine" ;;
            \[variants\])     loSection="variants" ;;
            \[multiconfigs\]) loSection="multiconfigs" ;;
            \[bootloaders\])  loSection="bootloaders" ;;
            \#*)  ;;
            "")  ;;
            *) [ "$loSection" == "machine" ]      && glConfigMachine="${lpLine}";
               [ "$loSection" == "variants" ]     && glConfigVariants="${glConfigVariants} ${lpLine}";
               [ "$loSection" == "multiconfigs" ] && glConfigMultiConfigs="${glConfigMultiConfigs} ${lpLine}";
               [ "$loSection" == "bootloaders" ]  && glConfigBootloaders="${glConfigBootloaders} ${lpLine}";
                ;;
        esac
    done <$loConfigFPFN

    glConfigImages=""
    glConfigWics=""

    for lpWic in `ls ${glDeployDir}/*.wic.xz`; do
        if [ ! -L "${lpWic}" ]; then
            local loWic=$(basename $lpWic);
            local loImage=`echo ${loWic} | awk -F "-${glConfigMachine}" '{printf($1)}'`
            glConfigImages="${glConfigImages} ${loImage}"
            glConfigWics="${glConfigWics} ${loImage}:${loWic}"
        fi
    done
}

list_options()
{
    echo "Images:"
    for lpImage in $glConfigImages; do
        echo "  ${lpImage}"
    done

    echo ""

    echo "U-Boot Variants:"
    for lpVariant in $glConfigVariants; do
        echo "  ${lpVariant}"
    done

    echo ""

    echo "U-Boot Multi-Configs:"
    for lpMultiConfig in $glConfigMultiConfigs; do
        echo "  ${lpMultiConfig}"
    done
}

list_images()
{
    echo $glConfigImages
}

list_variants()
{
    echo $glConfigVariants
}

list_multiconfigs()
{
    echo $glConfigMultiConfigs
}

clean_work_dir() {
    rm -rf ${glWorkDir}
}

copy_and_update_wic()
{
    local loWicImageFN=""
    for lpWic in $glConfigWics; do
        local loImage=`echo ${lpWic} | awk -F : '{printf($1)}'`
        if [ "${loImage}" == "${glImage}" ]; then
            loWicImageFN=`echo ${lpWic} | awk -F : '{printf($2)}'`
            break
        fi
    done

    if [ "${loWicImageFN}" == "" ]; then
        echo "ERROR: Invalid image \"$glImage\"."
        exit 1
    fi

    local loSourceWic="${glDeployDir}/${loWicImageFN}"

    local loTempWic="${glWorkDir}/temp.wic"
    local loTempWicXz="${loTempWic}.xz"

    echo -n "Copying and uncompressing source wic file..."
    cp ${loSourceWic} ${loTempWicXz}
    xz --decompress -f -k --memlimit=50% --threads=$(nproc) ${loTempWicXz}
    echo "done"

    #${glOeCoreRepoFPDN}/scripts/wic ls ${loTempWic}:1

    echo "Installing new bootloader files..."
    for lpFile in $glConfigBootloaders; do
        if [ "${glVariant}" != "" ]; then
            echo "    ${lpFile}-${glVariant} -> ${lpFile}"
            ${glOeCoreRepoFPDN}/scripts/wic rm ${loTempWic}:1/${lpFile}
            ${glOeCoreRepoFPDN}/scripts/wic cp ${glDeployDir}/${lpFile}-${glVariant} ${loTempWic}:1/${lpFile}
        fi

        if [ "${glMultiConfig}" != "" ]; then
            if [[ "${lpFile}" =~ ^(.+)-${glMultiConfig}(.+) ]]; then
                local loFront=${BASH_REMATCH[1]}
                local loBack=${BASH_REMATCH[2]}

                local loBootloader="${loFront}${loBack}"

                if [ "${glVariant}" != "" ]; then
                    echo "    ${lpFile}-${glVariant} -> ${loBootloader}"
                    ${glOeCoreRepoFPDN}/scripts/wic rm ${loTempWic}:1/${loBootloader}
                    ${glOeCoreRepoFPDN}/scripts/wic cp ${glDeployDir}/${lpFile}-${glVariant} ${loTempWic}:1/${loBootloader}
                else
                    echo "    ${lpFile} -> ${loBootloader}"
                    ${glOeCoreRepoFPDN}/scripts/wic rm ${loTempWic}:1/${loBootloader}
                    ${glOeCoreRepoFPDN}/scripts/wic cp ${glDeployDir}/${lpFile} ${loTempWic}:1/${loBootloader}
                fi
            fi
        fi
    done
    echo "done"

    #${glOeCoreRepoFPDN}/scripts/wic ls ${loTempWic}:1

    echo -n "Generating bmap file..."
    cd ${glBmaptoolRepoFPDN}/src
    python3 -m bmaptool create ${loTempWic} -o ${glOutput}.bmap
    echo "done"

    cd ${glRunDir}

    echo -n "Compressing target wic file..."
    xz -f -k -c -6 --memlimit=50% --threads=$(nproc) --check=crc32 ${loTempWic} > ${glOutput}
    echo "done"
    
    rm -f ${loTempWic} ${loTempWicXz}
}

function git_clone()
{
    local arRepoUrl=$1
    local arRepoDir=$2

    cd ${glWorkDir}

    if [ ! -d $arRepoDir ]; then
        echo ""
        echo "Cloning $arRepoDir..."
        echo ""

        git clone ${arRepoUrl} ${arRepoDir}
    fi

    cd ${glRunDir}
}

usage()
{
    echo "Usage:" >&2
    echo "" >&2
    echo "$glScript" >&2
    echo "" >&2
    echo "  --image <image>         Image to use as the base for the new wic image." >&2
    echo "  --output <path>         Path to where the new wic should be placed." >&2
    echo "" >&2
    echo "  [--deploy-dir <path>]   Path to the directory that contains the wic images," >&2
    echo "                          bootloaders, config, etc...  If you do not specify" >&2
    echo "                          this option, it will be set to the location where" >&2
    echo "                          the \"${glConfigFN}\" config file is found." >&2
    echo "  [--variant <variant>]   U-Boot variant to install." >&2
    echo "  [--multiconfig <cfg>]   U-Boot multiconfig to install." >&2
    echo "" >&2
    echo "  [--list-options]        Print a report of all available options for image" >&2
    echo "                          variant, and multiconfig from the config file." >&2
    echo "  [--list-images]         Print the available images in the deploy-dir." >&2
    echo "  [--list-variants]       Print the available U-Boot variants from the config" >&2
    echo "                          file." >&2
    echo "  [--list-multiconfigs]   Print the available U-Boot multiconfigs from the" >&2
    echo "                          config file." >&2
    echo "  [--clean-workdir]       Remove the workdir and clean up any left over files" >&2
    echo "                          from running the command.  This option should only" >&2
    echo "                          be called once all of the calls to the script are" >&2
    echo "                          complete as this removes the git repos for the wic." >&2
    echo "                          and bmaptool programs." >&2
    echo "  [--help]                Show this screen]" >&2
}


###############################################################################
# Process the command line arguments and set global variables for each one.
###############################################################################
glOptSpec="h-:"
while getopts "$glOptSpec" lpArg; do
    case "${lpArg}" in
        -)
            case "${OPTARG}" in
                deploy-dir)     loVal="${!OPTIND}"; OPTIND=$(( $OPTIND + 1 ));  glDeployDir=$loVal;;
                deploy-dir=*)   loVal=${OPTARG#*=}; loOpt=${OPTARG%=$loVal};    glDeployDir=$loVal;;
                image)          loVal="${!OPTIND}"; OPTIND=$(( $OPTIND + 1 ));  glImage=$loVal;;
                image=*)        loVal=${OPTARG#*=}; loOpt=${OPTARG%=$loVal};    glImage=$loVal;;
                variant)        loVal="${!OPTIND}"; OPTIND=$(( $OPTIND + 1 ));  glVariant=$loVal;;
                variant=*)      loVal=${OPTARG#*=}; loOpt=${OPTARG%=$loVal};    glVariant=$loVal;;
                multiconfig)    loVal="${!OPTIND}"; OPTIND=$(( $OPTIND + 1 ));  glMultiConfig=$loVal;;
                multiconfig=*)  loVal=${OPTARG#*=}; loOpt=${OPTARG%=$loVal};    glMultiConfig=$loVal;;
                output)         loVal="${!OPTIND}"; OPTIND=$(( $OPTIND + 1 ));  glOutput=$loVal;;
                ouptut=*)       loVal=${OPTARG#*=}; loOpt=${OPTARG%=$loVal};    glOutput=$loVal;;

                list-options)                                                   glListOptions="true";;
                list-images)                                                    glListImages="true";;
                list-variants)                                                  glListVariants="true";;
                list-multiconfigs)                                              glListMultiConfigs="true";;
                clean-workdir)                                                  glCleanWorkDir="true";;

                help)                                                           usage; exit 0;;
                *)
                    if [ "$OPTERR" = 1 ] && [ "${glOptSpec:0:1}" != ":" ]; then
                        usage
                        echo "" >&2
                        echo "Unknown option --${OPTARG}" >&2
                        echo "" >&2
                        exit 0
                    fi
                    exit 1;
                    ;;
            esac;;
        h)  usage; exit 0;;
    esac
done

glFailedArgCheck=0

echo "$glConfigFN"
if [ "${glDeployDir}" == "" ]; then
    if [ -f $glConfigFN ]; then
        glDeployDir="${glRunDir}"
    fi
fi

if [ "${glDeployDir}" == "" ]; then
    if [ -f "${glRealBin}/${glConfigFN}" ]; then
        glDeployDir="${glRealBin}"
    fi
fi

if [ "${glDeployDir}" == "" ]; then
    echo "ERROR: You must specify --deploy-dir <dir> where all of the deployed files reside."
    glFailedArgCheck=1
fi

read_config

if [ "${glListImages}" == "true" ]; then
    list_images
    exit
fi

if [ "${glListVariants}" == "true" ]; then
    list_variants
    exit
fi

if [ "${glListMultiConfigs}" == "true" ]; then
    list_multiconfigs
    exit
fi

if [ "${glCleanWorkDir}" == "true" ]; then
    clean_work_dir
    exit
fi

echo ""
echo "${glScript}"
echo "-----------------"
echo ""

if [ "${glListOptions}" == "true" ]; then
    list_options
    exit
fi

if [ "${glImage}" == "" ]; then
    echo "ERROR: You must specify --image <image> to choose which image wic file to pull from."
    glFailedArgCheck=1
fi

if [[ "${glVariant}" == "" && "${glMultiConfig}" == "" ]]; then
    echo "ERROR: You must specify --variation <variation> and/or --multiconfig <multiconfig> to choose which set of bootloaders to configure on the target wic image."
    glFailedArgCheck=1
fi

if [ "${glOutput}" == "" ]; then
    echo "ERROR: You must specify --output <file> to point to the new wic file you want to create with the applied variation."
    glFailedArgCheck=1
fi

glOutput=$(realpath ${glOutput})

if [ "${glFailedArgCheck}" == "1" ]; then
    echo ""
    echo "Aborting"
    exit 1
fi

echo "WORK-DIR:     ${glWorkDir}"
echo "DPELOY-DIR:   ${glDeployDir}"
echo "IMAGE:        ${glImage}"
if [ "${glVariant}" != "" ]; then
    echo "VARIANT:      ${glVariant}"
fi
if [ "${glMultiConfig}" != "" ]; then
    echo "MULTI-CONFIG: ${glMultiConfig}"
fi
echo "OUTPUT:       ${glOutput}"

mkdir -p ${glWorkDir}

git_clone https://git.openembedded.org/openembedded-core ${glOeCoreRepoDN}
git_clone https://github.com/yoctoproject/bmaptool.git ${glBmaptoolRepoDN}

echo ""

copy_and_update_wic

