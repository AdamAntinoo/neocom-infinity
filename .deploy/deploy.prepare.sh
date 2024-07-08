# D E P L O Y - P R E P A R E
# Genrate the common environment variables configuration to be applied to the application deployments.
# Generate the version and banner files to be packed with the deployments or used for the conpilation.
#
# Presetup:
# - The launch directory should be the main project root file where the checkout is completed.
##########
# G E T   E N V I R O N M E N T
# Extract the deployment environment from the branch/tag being processed along with the buid version
#
##########
# - define workspace
export NEOCOM_ROOT_DIRECTORY=.
echo 'NEOCOM_ROOT_DIRECTORY->'$NEOCOM_ROOT_DIRECTORY
echo 'location->'`pwd`
cd $NEOCOM_ROOT_DIRECTORY/.deploy

# - get the environment to be used
 # - set default value to 'deployment'
echo '>>> Identifying Environment'
export ENVIRONMENT='deployment'
export BRANCH=%teamcity.build.branch%
echo "BRANCH->$BRANCH"
if [[ "$BRANCH" =~ ^(.*)main ]];
then 
    export ENVIRONMENT='production'
else 
	export ENVIRONMENT='deployment'
fi
echo 'ENVIRONMENT->'$ENVIRONMENT

# - generate the common environment properties
. ./esi-configuration.$ENVIRONMENT.sh
##########
# P R E - D E P L O Y M E N T
# Run the predeployment scripts for all NeoCom projects.
# Genrate the version and other files required for full deployment.
#
# - run NIB predeployment
export PROJECT_CODE=$NIB_PROJECT_CODE
echo 'PROJECT_CODE->'$PROJECT_CODE
cd ..
cd $NEOCOM_ROOT_DIRECTORY/$NEOCOM_NIB
. ./.deploy/deploy.prepare.sh
