# NETWORK ARCHITECTURE
## Stage services and connections
The services are complex because the localhost usually will be pointing to the docker or kubernetes container and not to the host machine.

```mermaid
  graph TD;
      FRONTEND:49998-->KUBE/neocom/nif-svc:8000;
      KUBE/neocom/nif-svc:8000-->neocom/nif-stage:5203;
      neocom/nif-stage:5203-->host.minikube.internal:5233;
      host.minikube.internal:5233-->BACKEND:5233;
```
