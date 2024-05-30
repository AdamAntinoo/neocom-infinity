# RECIPES
## Generate Nest Resources
./node_modules/@nestjs/cli/bin/nest.js g resource mincontroller

## Generate a docker image
docker build -t neocom-infinity-nest .
docker tag neocom-infinity-nest adamantinoo/neocom-infinity-nest:0.21.0
docker push adamantinoo/neocom-infinity-nest:0.21.0

kubectl create ns neocom
kubectl apply -f deployment.yml

kubectl port-forward svc/nin-svc 8000:8000

## Kubectl commands
kubectl config set-context --current --namespace neocom

    "[typescript]": {
        "editor.defaultFormatter": "esbenp.prettier-vscode",
        "editor.tabSize": 4
    },

# VSCode Settings
    "files.associations": {
        "*.step.ts": "steps",
        "*.ts": "typescript"
    },
    "[steps]": {
        "editor.defaultFormatter": "vscode.typescript-language-features"
    },
    "[typescript]": {
        "editor.defaultFormatter": "vscode.typescript-language-features"
    },
