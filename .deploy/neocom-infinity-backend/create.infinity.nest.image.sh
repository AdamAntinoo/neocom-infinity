# C R E A T E    I M A G E
# Create a docker image for the NestJS backend
#
# - define variables for deployment
PROJECT_ROOT=../..
PROJECT_NAME=neocom-infinity-nest
export IMAGE_NAME=neocom-infinity-nest
WORKING_DIR=$PROJECT_ROOT/$PROJECT_NAME
export ENV=$1
export PORT=3000

# - get version codes
export SEMVER=`gitversion /showvariable MajorMinorPatch`
VMETA=`gitversion /showvariable CommitsSinceVersionSource`
export VERSION=$SEMVER-$VMETA
BANNER_LOCATION=$WORKING_DIR/app-banner.txt
echo 'BANNER_LOCATION->'$BANNER_LOCATION

# - identification
echo "Environment->$1"
echo "Version->$VERSION"

# - generate banner
echo '>>> Creating banner'
figlet NeoNest `gitversion /showvariable MajorMinorPatch`-`gitversion /showvariable CommitsSinceVersionSource` > app-banner.txt
cp app-banner.txt $BANNER_LOCATION
cat app-banner.txt

# - build the image
echo '>>> Creating image'
cat dockerfile.nest | envsubst > Dockerfile
docker build --build-arg="$ENV" --build-arg="$PORT" -f ./Dockerfile -t $IMAGE_NAME $WORKING_DIR

# - tag the image
echo ">>> Tagging image->adamantinoo/$IMAGE_NAME:$VERSION"
docker tag $IMAGE_NAME adamantinoo/$IMAGE_NAME:$VERSION
docker tag $IMAGE_NAME $IMAGE_NAME:$VERSION
docker push adamantinoo/$IMAGE_NAME:$VERSION

# - update the kubernetes elements
cat deployment-nest.template.yaml | envsubst > deployment-nest.yaml
kubectl apply -f deployment-nest.yaml
