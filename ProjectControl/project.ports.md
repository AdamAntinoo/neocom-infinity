# NEOCOM Application Port Mapping - Project Code 52
## Port Nomenclature
The project will provide all ports on the range 5200 to 5299. Digits for port assignment follow next rules.
- The first two digits are always 5 and 2.
- The third digit is for the functionality group. Like server, mocks and other services. Goes from 0 (main service) to 9.
- The fourth and last digit is for the environment.
    * Value **0** is for *PRODUCTION* environment
    * Value **1** is for development and local runs not deployed. 
    * Value **2** for Acceptance testing.
    * Value **3** for Staging and local deployments.

## Development
* **5201** - Development Angular Frontend application. Uses mocks but no access to Internet.
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
- **5203 - Frontend Angular Server**
- **5213 - Backend Nest Server**

#### 5282 - [INTEGRATION] Backend Integration stand-alone Postgres DB
#### 5242 - [INTEGRATION] Backend SB application server

## Production
