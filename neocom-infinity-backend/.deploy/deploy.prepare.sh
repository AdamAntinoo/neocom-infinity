# D E P L O Y - P R E P A R E
# Generate deployment files and prepare for packing and deployment.
#
# - generate version and banner
echo '>>> Identifying version'
export SEMVERSION=`gitversion /showvariable MajorMinorPatch`
export COMMIT_COUNT=`gitversion /showvariable CommitsSinceVersionSource`
export VERSION=`gitversion /showvariable AssemblySemFileVer`
echo 'SEMVERSION->'$SEMVERSION
echo 'VERSION->'$VERSION
echo "##teamcity[setParameter name='env.SEMVERSION' value='$SEMVERSION']"
echo "##teamcity[setParameter name='env.VERSION' value='$VERSION']"
echo "<<<<<"

# - update compilation version
echo "version=$SEMVERSION" > $WORKING_DIR/gradle.properties

# - generate banner
echo '>>> Creating banner'
export WORKING_DIR=`pwd`
echo 'WORKING_DIR->'$WORKING_DIR
export BANNER_LOCATION=$WORKING_DIR/src/main/resources/app-banner.txt
echo 'BANNER_LOCATION->'$BANNER_LOCATION
figlet $PROJECT_CODE $VERSION > $BANNER_LOCATION
cat $BANNER_LOCATION
figlet $ENVIRONMENT
echo "<<<<<"
