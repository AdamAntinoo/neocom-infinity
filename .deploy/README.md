# NEOCOM Deployment Process
To create a new deployment for advanced testing or for Stage we should use the local Kubernetes cluster.

Deployment process will be commanded by the commit tasks to the code repository and will be handled by TeamCity CI/CD.

Because TeamCity relays on a full application code the **master** branch should be set as close to possible to the development stage. This means moving the master to the current state of the art of the project and leaving testing and acceptance testing for a later case when the system is stable again.

The introduction of NEST has bumbep forward all this code base and probably there are commits that should be recovered from previous developments into a new testbed platform to integrate most of the previous and now disabled features.

Java backend is deprecated and new developments should focus on the new and easier to maintain NEST backend service that should be deployed asap.

Heroku and CircleCi jointly with githib actions and bitbucket pipelines are also deprecated and being moved to local with TeamCity as the new CI/CD deployment framework.

## Docker images
Code ready to be deployed should be packed into docker images. That images will be promoted and handled by the local Kubernetes platform and keep all environments ready for operation.

Before any image creation we should point to the source context and create the application from it. Apply space reduction rules to include inside the image just the required files.

What is going to be stored on the image is the compiled code from a fresh npm install and the selected environment build process. Images will not depend on the previous build contents nor need any previous build.

### NEST backend
````bash
docker build -t neocom-infinity-nest .
````
