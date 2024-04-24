# G E N E R A T E   D O C K E R   I M A G E
# Create a docker image for the NestJS backend
#
# - read parameters to configure the image creation
# $1 - the environment to be used for the image. By default it gets the NODE_ENV environment variable
ENVIRONMENT=$1
if [ -z "$NODE_ENV" ]; then
  export NODE_ENV=staging
fi
if [ -z "$ENVIRONMENT" ]; then
  export ENVIRONMENT=$NODE_ENV
fi
# - define variables for deployment
LAUNCH_LOCATION="$(dirname "$0")"
PROJECT_ROOT=$LAUNCH_LOCATION
PROJECT_NAME=neocom-infinity-nest
WORKING_DIR=$PROJECT_ROOT
DEPLOY_DIR=$WORKING_DIR/.deploy
SOURCES_DIR=$PROJECT_ROOT/src
export IMAGE_NAME=$PROJECT_NAME
export ENV=$ENVIRONMENT
export PORT=3000 # Port number is hardcoded to the default port number of the service. Mapping is done at the Docker level

# - generate banner
echo '>>> Creating banner'
export BANNER_LOCATION=$WORKING_DIR/app-banner.txt
echo 'BANNER_LOCATION->'$BANNER_LOCATION
figlet NeoNest `gitversion /showvariable AssemblySemFileVer` > app-banner.txt
cat app-banner.txt
figlet $ENV

echo "WORKING_DIR->$WORKING_DIR"
echo 'IMAGE_NAME->'$IMAGE_NAME
echo 'ENV->'$ENV
echo 'PORT->'$PORT
echo

# - get version codes
echo '>>> Identifying version'
export SEMVER=`gitversion /showvariable MajorMinorPatch`
export VMETA=`gitversion /showvariable CommitsSinceVersionSource`
export SEMVERSION=$SEMVER-$VMETA
export VERSION=`gitversion /showvariable AssemblySemFileVer`
echo 'SEMVERSION->'$SEMVERSION
echo 'VERSION->'$VERSION

# - image identification and tags
export TAG=$IMAGE_NAME:$VERSION
echo "Environment->$ENV"
echo "Version->$VERSION"
echo "Tags->$TAG"

# - build the image
echo '>>> Building image'
cat $DEPLOY_DIR/dockerfile.template | envsubst > $DEPLOY_DIR/Dockerfile
docker build -f $DEPLOY_DIR/Dockerfile -t $IMAGE_NAME $WORKING_DIR

# - tag the image
# echo ">>> Tagging image->adamantinoo/$IMAGE_NAME:$VERSION"
# docker tag $IMAGE_NAME adamantinoo/$IMAGE_NAME:$VERSION
# docker tag $IMAGE_NAME TAG
# docker push adamantinoo/$IMAGE_NAME:$VERSION

# # - update the kubernetes elements
# cat deployment-nest.template.yaml | envsubst > deployment.yaml
# kubectl apply -f deployment.yaml
