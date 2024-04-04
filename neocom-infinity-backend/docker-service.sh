#!/bin/bash
# - PARAMETERS & CONSTANTS
COMMAND=$1
ENVIRONMENT=$2
if [ -z "$ENVIRONMENT" ]; then
  ENVIRONMENT="acceptance"
fi
figlet "Docker Service ${COMMAND}"
echo
echo "Using environment: ${ENVIRONMENT}"
APPLICATION_JAR_NAME="neocom-infinity-backend-container"
WORKING_DIRECTORY="$(dirname "$0")"
DOCKER_DIRECTORY="${WORKING_DIRECTORY}/src/main/resources/docker"
DOCKER_COMPOSER_COMMAND="docker-compose --file src/test/scripts/neocom-docker-${ENVIRONMENT}/docker-compose.yml"

# - G E N E R A T E   C O N T A I N E R
generateContainer() {
  cd "${WORKING_DIRECTORY}" || exit 1
  # Cleanup
  rm -vrf "${DOCKER_DIRECTORY}"/*.jar
  rm -vrf "${DOCKER_DIRECTORY}"/properties*
  ./gradlew bootJar || exit 1
  cp ./build/libs/*.jar "$DOCKER_DIRECTORY"
  cp ./build/resources/main/app-banner.txt "$DOCKER_DIRECTORY"
  cp ./build/resources/main/app-banner.txt ./build/libs/app-banner.txt
  cp ./src/main/resources/sde.db "${DOCKER_DIRECTORY}"
  # Copy all properties sets into the container. Configuration will select the right set.
  cp -r ./src/main/resources/properties* "${DOCKER_DIRECTORY}"
  cd "$DOCKER_DIRECTORY" || exit 1
  mv -v neocom-infinity-backend*.jar ${APPLICATION_JAR_NAME}".jar"
  echo "${DOCKER_DIRECTORY}/Dockerfile"
  docker build -t neocom/neocom-infinity.backend .
}
# - S T A R T / S T O P
start() {
  cd "${WORKING_DIRECTORY}" || exit 1
  RUN_COMMAND="${DOCKER_COMPOSER_COMMAND}"
  $RUN_COMMAND up &
}
stop() {
  cd "${WORKING_DIRECTORY}" || exit 1
  RUN_COMMAND="${DOCKER_COMPOSER_COMMAND}"
  $RUN_COMMAND down
}
recycle() {
  # - STOP
  cd "${WORKING_DIRECTORY}" || exit 1
  RUN_COMMAND="${DOCKER_COMPOSER_COMMAND}"
  $RUN_COMMAND down
  generateContainer
  cd "${WORKING_DIRECTORY}" || exit 1
  RUN_COMMAND="${DOCKER_COMPOSER_COMMAND}"
  $RUN_COMMAND up &
}

case $COMMAND in
'generate')
  generateContainer
  ;;
'start')
  start
  ;;
'stop')
  stop
  ;;
'recycle')
  recycle
  ;;
*)
  echo "Usage: $0 { generate | start | stop | recycle }"
  echo
  exit 1
  ;;
esac
exit 0
