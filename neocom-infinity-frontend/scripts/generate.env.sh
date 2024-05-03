. .env.$NODE_ENV
printenv | sort
cat ./src/environments/environment.template.ts | envsubst > ./src/environments/environment.ts
