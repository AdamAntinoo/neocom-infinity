# README #
### Purpose of this repository
* This repository will contain the projects for all the application set of processes to access Eve Online Esi data and to allow users to use a frot end to help on the different game activities.
* The main purpose is the help on the Industry and Planetary areas but other game activities can also be represented on the different application stack.
* There are also project to port the data structures and UI to the Android platform.

# Start the DEV set of applications
## Prepare the environment
* Set the java version to Java 11

````
> java_versions
            Matching Java Virtual Machines (5):
21.0.1 (x86_64) "Azul Systems, Inc." - "Zulu 21.30.15" /Users/adam/Library/Java/JavaVirtualMachines/azul-21.0.1/Contents/Home
19.0.2 (x86_64) "Azul Systems, Inc." - "Zulu 19.32.13" /Users/adam/Library/Java/JavaVirtualMachines/azul-19.0.2/Contents/Home
11.0.22 (x86_64) "Amazon.com Inc." - "Amazon Corretto 11" /Users/adam/Library/Java/JavaVirtualMachines/corretto-11.0.22/Contents/Home
1.8.0_402 (x86_64) "Azul Systems, Inc." - "Zulu 8.76.0.17" /Users/adam/Library/Java/JavaVirtualMachines/azul-1.8.0_402/Contents/Home
1.8.0_382 (x86_64) "Azul Systems, Inc." - "Zulu 8.72.0.17" /Users/adam/Library/Java/JavaVirtualMachines/azul-1.8.0_382/Contents/Home
/Users/adam/Library/Java/JavaVirtualMachines/azul-21.0.1/Contents/Home

> export JAVA_HOME=/Users/adam/Library/Java/JavaVirtualMachines/corretto-11.0.22/Contents/Home
````
* Set the npm version to neocom.infinity
````
> nvm list
v6.17.1
->     v10.16.3
v21.6.2
neocom.infinity -> 10.16.3 (-> v10.16.3)
default -> node (-> v21.6.2)
iojs -> N/A (default)
unstable -> N/A (default)
node -> stable (-> v21.6.2) (default)
stable -> 21.6 (-> v21.6.2) (default)
lts/* -> lts/iron (-> N/A)
lts/argon -> v4.9.1 (-> N/A)
lts/boron -> v6.17.1
lts/carbon -> v8.17.0 (-> N/A)
lts/dubnium -> v10.24.1 (-> N/A)
lts/erbium -> v12.22.12 (-> N/A)
lts/fermium -> v14.21.3 (-> N/A)
lts/gallium -> v16.20.2 (-> N/A)
lts/hydrogen -> v18.19.1 (-> N/A)
lts/iron -> v20.11.1 (-> N/A)

> nvm use neocom.infinity
````
## Generate the SDE database
* Enter the **neocom-datamanagement** project.
* Move to the directory **src/integration/sh**.
* Create the **download** destination with ***mkdir downloads***.
* Move back to the home for the project.
* Create the SDE database with the gradle task ***./gradlew downloadSDEData generateSDEDatabase***.
* Copy the resulting SDE database to the neocom-infinity
````
cp src/integration/resources/sde.db ../<neocom.infinity.backend>/src/main/resources
````

## Start the backend services
* Move to the neocom-infinity application directory and enter the NeoCom-Infinity-Backend
* Generate the docker image with the command ***./docker-service.sh generate***.
* Satrt the backend DEV services with the command ***./docker-service.sh start development***.

## Start the frontend services
* Move to the NeoCom-Infinity-Frontend application.
* Check that all node code is installed with the command ***npm install***.
* Start the development services for the frontend with the command ***npm start docker:start***.
* Start the UX in DEV mode with the command ***npm run serve:dev***

## Start the UX
* Go to the Chrome navigator and navigate to url **http://localhost:5200**.

# Stop DEV services
* Move to neocom-infinity/neocom-infinity-frontend
* Stop docker services with command ***npm run docker:stop***.
* Move to neocom-infinity/neocom-infinity-backend.
* Stop backend services with command ***./docker-service.sh stop development***.

# Application Port Mapping - Project Code 52
## Port Nomenclature
The project will provide all ports on the range 5200 to 5299. Digits for port assignment follow next rules.
- The first two digits are always 5 and 2.
- The third digit is for the functionality group. Like frontend or backend or mock.
- The fouth and last digit is for the functionality up to value 5 (**1-5**). Value **9** is for testig. Value **8** for Acceptance. Value **7** for Staging and valoe **0** is reserved for production.

### Development
#### 5234 - [DEVELOPMENT] Postgresql Common database

#### 5200 - [DEVELOPMENT] Frontend Node server
#### 5210 - [DEVELOPMENT] Frontend ApiSimulator backed server
#### 5290 - [DEVELOPMENT] Frontend ApiSimulator public server
#### 5220 - [DEVELOPMENT] Frontend Backend SB application server for /v1/universe
#### 5230 - [DEVELOPMENT] Frontend Postgres DB
#### 5240 - [DEVELOPMENT] Backend SB application server
#### 5250 - [DEVELOPMENT] Backend ApiSimulator Eve Online authentication server
#### 5260 - [DEVELOPMENT] Backend ApiSimulator ESI Universe Data Server
#### 5270 - [DEVELOPMENT] Backend ApiSimulator ESI Data Server
#### 5271 - [DEVELOPMENT] Backend Nest Wiremock ESI Data Server
#### 5280 - [DEVELOPMENT] Backend Postgres DB

## Acceptance

#### 5241 - [ACCEPTANCE] Backend SB application server
#### 5251 - [ACCEPTANCE] Backend ApiSimulator Eve Online authentication server
#### 5261 - [ACCEPTANCE] Backend ApiSimulator ESI Universe Data Server
#### 5270 - [ACCEPTANCE] Backend ApiSimulator ESI Data Server
#### 5281 - [ACCEPTANCE] Backend Postgres DB
#### 5291 - [ACCEPTANCE] Backend Redis DB service

## Stage
- **5257 - Backend Nest Server**

- **5202 - Frontend Node server**
#### 5282 - [INTEGRATION] Backend Integration stand-alone Postgres DB
#### 5242 - [INTEGRATION] Backend SB application server

## Production
