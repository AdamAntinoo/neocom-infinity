# D E P L O Y   K U B E R N E T E S
# Deploy the image to the Kubernetes namespace
#
# - read parameters to configure the image creation
# $1 - the environment to be used for the image. By default it gets the NODE_ENV environment variable
export ENVIRONMENT=$1
if [ -z "$NODE_ENV" ]; then
  export NODE_ENV=staging
fi
if [ -z "$ENVIRONMENT" ]; then
  export ENVIRONMENT=$NODE_ENV
fi
export ENV=$ENVIRONMENT

# - define variables for deployment
export NAMESPACE="neocom-$ENVIRONMENT"
export APP_CODE="neospring"
export MODULE_NAME="neocom-infinity-backend"
export PORT=9500

export IMAGE_NAME=$MODULE_NAME
export VERSION=`gitversion /showvariable AssemblySemFileVer`
export IMAGE_TAG=$IMAGE_NAME:$VERSION

echo "##teamcity[setParameter name='_NEOCOM.NAMESPACE' value='$NAMESPACE']"
echo "##teamcity[setParameter name='_NEOCOM.APP_CODE' value='$APP_CODE']"
echo "##teamcity[setParameter name='_NEOCOM.MODULE_NAME' value='$MODULE_NAME']"
echo "##teamcity[setParameter name='_NEOCOM.PORT' value='$PORT']"
echo "##teamcity[setParameter name='_NEOCOM.IMAGE_NAME' value='$IMAGE_NAME']"
echo "##teamcity[setParameter name='_NEOCOM.VERSION' value='$VERSION']"
echo "##teamcity[setParameter name='_NEOCOM.IMAGE_TAG' value='$IMAGE_TAG']"

# - generate the elements to upload to kubernetes
# - define variables for deployment
LAUNCH_LOCATION="$(dirname "$0")"
PROJECT_ROOT=$LAUNCH_LOCATION
echo "LAUNCH_LOCATION->$LAUNCH_LOCATION"
# PROJECT_NAME=neocom-infinity-nest
# PROJECT_ROOT=%teamcity.build.checkoutDir%/$PROJECT_NAME
# cd $PROJECT_ROOT
# WORKING_DIR=$PROJECT_ROOT
DEPLOY_DIR=.deploy
echo "DEPLOY_DIR->$DEPLOY_DIR"

cat $DEPLOY_DIR/deployment.template.yaml | envsubst > $DEPLOY_DIR/deployment.yaml
kubectl apply -f $DEPLOY_DIR/deployment.yaml
