# Start teamcity by first time
docker run --name teamcity-server-instance  \
    -v ~/.teamcity/data:/data/teamcity_server/datadir \
    -v ~/.teamcity/logs:/opt/teamcity/logs  \
    -p 8111:8111 \
    jetbrains/teamcity-server

# Docker / Kebernetes / Minikube
## Get the URL where to access a service
minikube service -n neocom nif-svc --url
