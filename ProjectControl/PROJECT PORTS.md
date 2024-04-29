# NEOCOM Application Port Mapping - Project Code 52
## Port Nomenclature
The project will provide all ports on the range 5200 to 5299. Digits for port assignment follow next rules.
- The first two digits are always **5** and **2**.
- The third digit is for the functionality group. Like server, mocks and other services. Goes from 0 (main service) to 9. Services may depend on the environment like the mocks thare are not present on staging or production.
- The fourth and last digit is for the environment.
    * Value **0** is for ***PRODUCTION*** environment
    * Value **1** is for ***STAGING*** and user testing before production deployment. 
    * Value **2** for ***Integration*** testing agaings other operative modules.
    * Value **3** for ***Acceptance*** testing. Uses mocks for acceptance tests.
    * Value **4** for ***Unit Testing*** and other local tests.
    * Value **5** is for ***DEVELOPMENT*** runs and manual tests.

## Development
* **5205** - Development Angular Frontend application. Uses mocks but no access to Internet.
* **5215** - Public ESI endpoints ApiSimulator.
* **5225** - Backend /api/* endpoints.
* **5235** - WireMock /api/v3 endpoints.
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
- **5230 - Backend SpringBoot Server**
- **5240 - Backend Postgres Repository**
- **5250 - Backend Redis Cache**
