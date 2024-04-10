cat deployment-frontend.template.yaml | envsubst > deployment.yaml
kubectl apply -f deployment.yaml
