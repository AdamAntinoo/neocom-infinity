# Start teamcity by first time
docker run --name teamcity-server-instance  \
    -v ~/.teamcity/data:/data/teamcity_server/datadir \
    -v ~/.teamcity/logs:/opt/teamcity/logs  \
    -p 8111:8111 \
    jetbrains/teamcity-server

# Docker / Kubernetes / Minikube
## Get the URL where to access a service
minikube service -n neocom nif-svc --url

# Generate Node sources from OpenApi specification
openapi-generator-cli generate -g typescript-nestjs -i esi.openapi.json

# Generate a config map from file
kubectl create configmap -n neocom-staging neocom-poc-environment --from-file=environment=.env.development
configmap/neocom-poc-environment created

