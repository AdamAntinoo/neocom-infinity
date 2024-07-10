# DEPLOYMENT DESCRIPTION
The deployment process can share many configuration propoerties across the different applications. Because of the use of Kubernetes the environment configuration can be externalizwed to ConfigMaps with the exception of the Frontend appliation build with Angular that should include the configuration at compile time inside the packed code.

The configuration then should define a set of environment variables to be used in two phases. When required they will be used to replac the template values and generate a environment final file that instead being used as replacement is generated on the deployment phase from a set of values.

If the set of values and the result are identical then we can remove the template and just manage the environment files.

## NEOFRONT
The ESI configuration values should be shared with the backends to be able to follow the authorization flow. So the set of values related to the authorization ESI appliction is configured into two files, one for the **deployment** environment and another for the **production** environment.

