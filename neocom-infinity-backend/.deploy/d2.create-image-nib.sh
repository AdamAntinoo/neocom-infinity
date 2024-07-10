# G E N E R A T E   D O C K E R   I M A G E
# Create a docker image for the NestJS backend
#
export PROJECT_ROOT=%teamcity.build.checkoutDir%/%PROJECT_NAME%
cd $PROJECT_ROOT

# - generate banner
echo '>>> Creating banner'
echo 'WORKING_DIR->'$WORKING_DIR
export BANNER_LOCATION=$WORKING_DIR/.app-banner.txt
echo 'BANNER_LOCATION->'$BANNER_LOCATION
figlet %PROJECT_CODE% $VERSION > $BANNER_LOCATION
cat $BANNER_LOCATION
figlet $ENV
echo "<<<<<"

# - build the image
echo '>>> Building image'
export DEPLOY_DIR=$WORKING_DIR/.deploy
cat $DEPLOY_DIR/dockerfile.template | envsubst > $DEPLOY_DIR/Dockerfile
docker build -f $DEPLOY_DIR/Dockerfile -t $IMAGE_NAME $WORKING_DIR
echo "<<<<<"










# G E N E R A T E   D O C K E R   I M A G E
# Create a docker image for the SpringBoot backend
#
export PROJECT_ROOT=%teamcity.build.checkoutDir%/%PROJECT_NAME%
cd $PROJECT_ROOT
# - image identification and tags
export TAG=$IMAGE_NAME:$VERSION
echo "TAG->$TAG"
echo "##teamcity[setParameter name='env.TAG' value='$TAG']"

# - G¡generate banner
echo '>>> Creating banner'
echo 'WORKING_DIR->'$WORKING_DIR
export BANNER_LOCATION=$WORKING_DIR/src/main/resources
echo 'BANNER_LOCATION->'$BANNER_LOCATION
figlet NeoSpring $VERSION > $BANNER_LOCATION/app-banner.txt
cat $BANNER_LOCATION/app-banner.txt
figlet $ENV
echo "<<<<<"

# - export environment properties
export PORT=%PORT%
export HOST_IP="host.docker.internal"
export REDIS_CACHE_URL="redis://${HOST_IP}:5250"
export SPRING_DATASOURCE_URL="jdbc:postgresql://${HOST_IP}:5240/neocom"
export SPRING_DATASOURCE_USERNAME='adamantinoo'
export SPRING_DATASOURCE_PASSWORD='z.iliada.2020'
export NEOCOM_DATABASE_URL="$SPRING_DATASOURCE_URL?user=$SPRING_DATASOURCE_USERNAME&password=$SPRING_DATASOURCE_PASSWORD"
echo "##teamcity[setParameter name='env.HOST_IP' value='$HOST_IP']"
echo "##teamcity[setParameter name='env.REDIS_CACHE_URL' value='$REDIS_CACHE_URL']"
echo "##teamcity[setParameter name='env.SPRING_DATASOURCE_URL' value='$SPRING_DATASOURCE_URL']"
echo "##teamcity[setParameter name='env.SPRING_DATASOURCE_USERNAME' value='$SPRING_DATASOURCE_USERNAME']"
echo "##teamcity[setParameter name='env.NEOCOM_DATABASE_URL' value='$NEOCOM_DATABASE_URL']"

# - build the jar
echo '>>> Building image'
export JAR_VERSION=$SEMVERSION
echo "JAR_VERSION->$JAR_VERSION"
echo "##teamcity[setParameter name='env.JAR_VERSION' value='$JAR_VERSION']"
export JAVA_HOME=%env.JAVA_HOME%
# rm -rf $PROJECT_ROOT/build
pwd
./gradlew -Pversion=$JAR_VERSION clean bootJar || exit 1
cp $PROJECT_ROOT/build/libs/%PROJECT_NAME%-$JAR_VERSION.jar $PROJECT_ROOT/build/libs/neocom-backend.jar || exit 1

# - build the image
export DEPLOY_DIR=$WORKING_DIR/.deploy
cat $DEPLOY_DIR/dockerfile.template | envsubst > $DEPLOY_DIR/Dockerfile
docker build -f $DEPLOY_DIR/Dockerfile -t $IMAGE_NAME $WORKING_DIR
