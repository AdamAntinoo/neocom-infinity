# C R E A T E    I M A G E
# Create a docker image for the NestJS backend
#
# - get input mandatory parameters
export ENV=$1
export PORT=$2
# - define variables for deployment
PROJECT_ROOT=../..
PROJECT_NAME=neocom-infinity-backend
export PROJECT_CODE=nib
export IMAGE_NAME=$PROJECT_NAME
WORKING_DIR=$PROJECT_ROOT/$PROJECT_NAME

# - get version codes
export SEMVER=`gitversion /showvariable MajorMinorPatch`
VMETA=`gitversion /showvariable CommitsSinceVersionSource`
export VERSION=$SEMVER-$VMETA
export TAGVERSION=$SEMVER-$VMETA
export LOGIN_LINK="http://localhost:32000/app/loginValidation"
BANNER_LOCATION=$WORKING_DIR/app-banner.txt
echo 'BANNER_LOCATION->'$BANNER_LOCATION

# - identification
echo "Environment->$ENV"
echo "Port->$PORT"
echo "Version->$VERSION"

# - generate banner
echo '>>> Creating banner'
figlet NeoBack `gitversion /showvariable MajorMinorPatch`-`gitversion /showvariable CommitsSinceVersionSource` > app-banner.txt
cp app-banner.txt $BANNER_LOCATION
cat app-banner.txt

# - build the image
echo '>>> Creating image'
cat dockerfile.backend.template | envsubst > Dockerfile
docker build -f ./Dockerfile -t $IMAGE_NAME $WORKING_DIR

# - tag the image
echo ">>> Tagging image->adamantinoo/$IMAGE_NAME:$VERSION"
docker tag $IMAGE_NAME adamantinoo/$IMAGE_NAME:$VERSION
docker tag $IMAGE_NAME $IMAGE_NAME:$VERSION
docker push adamantinoo/$IMAGE_NAME:$VERSION

# - update the kubernetes elements
cat deployment-frontend.template.yaml | envsubst > deployment.yaml
kubectl apply -f deployment.yaml
