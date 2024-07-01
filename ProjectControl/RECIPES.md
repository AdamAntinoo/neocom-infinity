# PROCESSES
## Start teamcity by first time
docker run --name teamcity-server-instance  \
    -v ~/.teamcity/data:/data/teamcity_server/datadir \
    -v ~/.teamcity/logs:/opt/teamcity/logs  \
    -p 8111:8111 \
    jetbrains/teamcity-server

# Generate Node sources from OpenApi specification
openapi-generator-cli generate -g typescript-nestjs -i esi.openapi.json

# Generate a config map from file
kubectl create configmap -n neocom-staging neocom-poc-environment --from-file=environment=.env.development
configmap/neocom-poc-environment created

# KUBERNETES
## Start Kubernetes Dashboard
kubectl -n kubernetes-dashboard create token admin-user
kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443

# FRONTEND
## Generate a new render component
ng generate component --export --selector v1-<render-name>-render --style scss industry/renders/v1-<render-name>-render
