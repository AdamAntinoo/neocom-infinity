# KUBERNETES ACTIONS
## Kubernetes
* Kubernetes comes preinstalled with Docker Desktop so only we should start/restart it to have a clen Kubernetes cluster.

## Helm Repos
helm repo list
* k8s-dashboard       	https://kubernetes.github.io/dashboard            
* prometheus-community	https://prometheus-community.github.io/helm-charts
* grafana             	https://grafana.github.io/helm-charts             
* kubernetes-dashboard	https://kubernetes.github.io/dashboard/           


## Kubernetes Dashboard
### Add kubernetes-dashboard repository
helm repo add kubernetes-dashboard https://kubernetes.github.io/dashboard/
### Deploy a Helm Release named "kubernetes-dashboard" using the kubernetes-dashboard chart
helm upgrade --install kubernetes-dashboard kubernetes-dashboard/kubernetes-dashboard --create-namespace --namespace kubernetes-dashboard

* Activate the port<br>
kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443
* Create the authorization elements<br>
kubectl apply -f service-account.yaml
kubectl apply -f cluster-role-binding.yaml 
kubectl apply -f bearer-token-secret.yaml
kubectl -n kubernetes-dashboard create token admin-user

* After Secret is created, we can execute the following command to get the token which saved in the Secret:<br>
kubectl get secret admin-user -n kubernetes-dashboard -o jsonpath={".data.token"} | base64 -d

# MiniIO
curl https://raw.githubusercontent.com/minio/docs/master/source/extra/examples/minio-dev.yaml -O
kubectl apply -f minio-dev.yaml


kubectl apply -k github.com/minio/operator
## Grafana Loki
adamantinoogit.grafana.net

+ PDC signing token<br>
glc_eyJvIjoiMTE2MDg0MSIsIm4iOiJwZGMtYWRhbWFudGlub29naXQtZGVmYXVsdC1wZGMtN2UzZmIxIiwiayI6InczNFBZSjNoSzJPSzQzMXI5dndpSGo0NiIsIm0iOnsiciI6InByb2QtZXUtd2VzdC0yIn19

kubectl create secret generic grafana-pdc-agent \
 --from-literal="token=glc_eyJvIjoiMTE2MDg0MSIsIm4iOiJwZGMtYWRhbWFudGlub29naXQtZGVmYXVsdC1wZGMtN2UzZmIxIiwiayI6InczNFBZSjNoSzJPSzQzMXI5dndpSGo0NiIsIm0iOnsiciI6InByb2QtZXUtd2VzdC0yIn19" \
 --from-literal="hosted-grafana-id=970686" \
 --from-literal="cluster=prod-eu-west-2"

 kubectl apply -f https://raw.githubusercontent.com/grafana/pdc-agent/main/production/kubernetes/pdc-agent-deployment.yaml


## Grafana

## Prometheus

## Grafana Cloud
cluster: docker-desktop
namespace: grafana-cloud
https://adamantinoogit.grafana.net/a/grafana-k8s-app/configuration/cluster-config?from=now-1h&to=now&var-datasource=grafanacloud-adamantinoogit-prom&var-loki=grafanacloud-adamantinoogit-logs

grafana-cloud-access-policy-token
glc_eyJvIjoiMTE2MDg0MSIsIm4iOiJzdGFjay05NzA2ODYtaW50ZWdyYXRpb24tZ3JhZmFuYS1jbG91ZC1hY2Nlc3MtcG9saWN5LXRva2VuLWdyYWZhbmEtY2xvdWQtYWNjZXNzLXBvbGljeS10b2tlbiIsImsiOiIxNTFPd0JqVTJJUERPNjc3TjNaMEI2dEMiLCJtIjp7InIiOiJwcm9kLWV1LXdlc3QtMiJ9fQ==

        - mountPath: /host/root
          mountPropagation: HostToContainer
          name: root
          readOnly: true

