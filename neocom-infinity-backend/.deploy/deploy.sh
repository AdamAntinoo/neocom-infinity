# D E P L O Y   K U B E R N E T E S
# Deploy the image to the Kubernetes namespace
#
# - variables required to be on the environment.
# APP_CODE - the reduced application string like 'neonest'.
# ENV - the selector between a deployment and a production release
# NAMESPACE - the destination Kubernetes namespace.
# PORT - the standard service port of the application under the declared service. Ir is static for an application.
# EXPOSE_PORT - the port number exposed to the localhost. It should change depending on the ENV.
# VERSION - the image version that is assigned to the pod.
# MODULE_NAME - the infinity module name. It is the long directory name of the software module.
# IMAGE_TAG - the Dicker repository image tag name. Usually the module+version.

# - environment definition to be mapped on kubernetes maps
# PORT - already declared.
# NEOCOM_DATABASE_URL - secret to be mapped because contains a password.
# SPRING_DATASOURCE_PASSWORD - to be declared as a secret because contains a password.
# REDIS_URL - location of the environment Redis database. There is currently a singel one for deployment and production.
# PROFILE - to be defined because I belive this shjoudl be deprecated. Matched the ENV.
# PROPERTIES_DIRECTORY - the place where to find the configuration properties. In nw releases should be a config map directory.

# - set environment values
export APP_CODE=%APP_CODE%
export ENV=%env.ENV%
export NAMESPACE="neocom-$ENV"
export PORT=%PORT%
export EXPOSE_PORT=%env.EXPOSE_PORT%
export VERSION=%env.VERSION%
export MODULE_NAME=%PROJECT_NAME%
export IMAGE_TAG=$%env.IMAGE_NAME%:%env.VERSION%

export HOST_IP="host.docker.internal"
export SPRING_DATASOURCE_URL="jdbc:postgresql://${HOST_IP}:5240/neocom"
export SPRING_DATASOURCE_USERNAME=%NEOCOM_USERNAME%
export SPRING_DATASOURCE_PASSWORD=%NEOCOM_PASSWORD%
export NEOCOM_DATABASE_URL="$SPRING_DATASOURCE_URL?user=$SPRING_DATASOURCE_USERNAME&password=$SPRING_DATASOURCE_PASSWORD"
export REDIS_URL="redis://${HOST_IP}:5250"
export PROFILE=$ENV
export PROPERTIES_DIRECTORY="/resources/main/properties-$ENV"

export DEPLOY_DIR=%teamcity.build.checkoutDir%/%env.PROJECT_NAME%/.deploy

cat $DEPLOY_DIR/deployment.template.yaml | envsubst > $DEPLOY_DIR/deployment.yaml
kubectl apply -f $DEPLOY_DIR/deployment.yaml
