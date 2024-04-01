# NEOCOM Nest Backend Requirements and Notes
## 1. Mining Operations. SSE solution
At this date Esi provides a new endpoint for mining operations. That endpoint provides this data
````
"date": "2024-02-23",
"quantity": 210,
"solar_system_id": 30003541,
"type_id": 17453
````
that is kept on the list for the next 30 days. Then all the logic to calculate deltas and all asset management *is not required*.

The new Mining service should do the next actions:
* Allow the frontend to open a session for Server Side Events when it needs to see mining data.
* Over that SSE session the server is going to send the mining operation from the Esi data service on a 10min schedule (600 seconds is the Esi wait time for this endpoint).
* When the mining operation frontend page is closed then the SSE should also be closed to the backend will stop fetching mining data.

The data from the Esi endpoint should be transformed to HAL data so the frontend can dinamically fetch the dependencies with no need to do all that management at the server side. For mining operations this is not so important since the number of locations and item types is fairly reduced even for miner characters.

Data should be converted to NI Extended JSON Serialization format that includes the class identifier for easy reconstruction and the frontend side.
### 1.1 Mining Operations endpoints
I lack OpenApi definitions for the endpints since this is s PROJECT task that is not planned at this moment. The base definition is:

**/nin/v1/character/{characterId}/miningoperations**

It should return an array of MiningOperation entries in the format:
````
jsonClass: "miningoperation"
id: "b433bdf3-2e0e-4f9a-a5fb-93b6a0d44752"
date: "YYYY-MM-DD"
quantity: 1234
solarSystem: "<hal link>"
typeId: "<hal link>"
````
HAL Links are provided by the server in the form accepted by the frontend. There are two possible solutions for this. Set it to a URL so the fornt end can generate the HAL instance from it and the jsonClass.
Or a second implentation where the server generates a new NIE json with the frontend required field to instantiate a HAL link. This last options is still under desing and will need a new implementation of the frontend to be accepted.

## 1.2 HAL Links
A HAL link should have an URL and a class to allow the instance factory to access Esi Data Source directly (for solar systems or for items) and some information to know how to map that Esi data into a Typescrypt class on the frontend


## X. Mining Sessions
Mining sessions are based on the detection of Ore assets change during time. During a mining operation the number of Ore items will be increased
and the system can detect that this increase has started and when has stopped to identify a mining operation.
This feature is based on a new set of services.
* MiningUpdateJob
* MiningOperationStarter
* MiningOperationCloser
* DeltaCalculator
* ESIAssetsAdapter

***This is obsolete right now since there is a new Esi endpoint that provides this information on a date/system/ore basis.***

## 2. Mining Session Data Flow
The flow starts with a UX request to start detecting a mining session. The backend service cannot be monitoring all characters all round 24h time for the assets since this is a quite expensive operation. It is most useful to start the signaling on the UX and from it the backend can take care of periodically get updates of the asset stacks to get the mining accounting done

The flow then depicts the next diagram.
Figure 2.1
* The action starts on the UX the the pilot opens the mining operation monitoring page.
* That monitoring page calls a signaling endpoint that will start a **StartMiningOperation** Use Case.
* The use case will create a new **MiningOperation** than when initialized will have the initial set of ore assets found at the start time.
* The use case will start a scheduled job to periodically get the new list of Ore assets. This job will reach the **ESIAssetsAdapter** to get the list of assets for the selected pilot. With the **MiningOperationStarter** will create the list that is injected into the MiningOperation to calculate the delta from the start set of assets.
* The ESIAssetsAdapter will use the **ESISecuredDataServiceAdapter** to reach the ESI data provider and get the current capsuleer list of assets.
* The list of assets will be filtered and ordered to be used by the **DeltaCalculator** to generate the difference of ore assets from the start mining point to this new asset extraction.
* The MiningOperation will use this new delta as the contents of the operation to be shown on the UX.
* The aggregated data will be available to the UX. There are two solutions to update that UX, or do a periodic get to refresh the **MininsSessionData** available on the frontend, or use push to push the new data to the frontend when available.

### 2.1 Ore Asset filtering
To filter out only ore assets we need additional data to the one provided by the asset list obtained from the ESI service. The ESI asset only has the *type_id* as a type selector. This type is a new structure on the ESI Universe that will provide all the additional type information like the **group_id** and the **name**.

The group_id is a key element to identify ore types since it points to the **category_id** number 25 that represent all the elements that originate at Asteroids.

Since we have not that information available beforehand and collecting it will require many ESI calls we can create a list by hand of all the types that fit on the category 25. This list usually will not change but in the event there are more ore assets we can upgrade it and this will solve the problem of filtering.

### 2.1 HttpSecureServiceAdapter
This is the single element tied to the NestJs libraries dependencies. It will wrap calls with the required headers and elements to make call to the Esi provider using the Axis framework http library.

### 2.1 ESISecuredDataServiceAdapter
ESI data provider is a set of swagger endpoint provided by CCP to get access to Eve Online capsuller data from inside the game. Most of those endpoints are secured and require a token oauth authentication prior to get access to the capsuleer data.
On feature development this data will come from a mocked ESI service that will mimic the functionality we require for the MiningOperation.

This Infrastructure service relays on the Framework layer to make outbound http calls. To allow easy mocking and testing this element cannot depend on the NesJs Framework layer. It will make use of the **HttpSecureServiceAdapter** to make calls to the real framework layer. This way the elements are completely isolated from the http service to be used on the whole application.

### Delta Calculator
The delta calculator is created to be used on mining operations. This means that has some limitations. The main one is that is should only
be applied to Ore items. All other items should be removed from the time line comparison. The scond and also important is that there should
be a timing data recovery process that will be used to detect the mining operation itself and to calculate the volume of items
created during the operation.
There is also a third requirement and it is that negative or 0 asset stocks should be removed from the delta calculation
since the mining operation is about positive actions. Negative delta stacks means that the user has destroyed or converted
some stacks into minerals and are not to be taken on account for delta calculations.

### Delta Calculator Limitations
A key limitation that should be researched is the case that the user packs a set of resourcces innto a new unique resource with
the sum of the quantities. This poses a problem on the assets calculations since negative elements are being removed from the
calculations. The destroyed stacks will not be accounted on the mining operation while the new merge stack will so the calculation
will be wrong since part of the new stack was added before when created.

### Delta Calculator Alternatives
Instead using delta calculations for the final result the delta are used for intermediate
result during the operation. When the operation completed a full Ore asset compariso from the start set of assets to the last set of assets is done to do a real net comprison of the
asset accounting. This solution only leaves the limitation of Ore asset destruction
by conversion to Minerals but solves the mergin and consolidation os stacks.

### Assets Adapter
The initial version will get the resources from the mocked service. Because thre is a timing load of resources to generate deltas we need that the
mock service be able to respond with different response contents each time it is called in a closed sequence.
The closed sequence should end with an exception to signal the termination of the source and probably to allow
the server to restart the synchronization.

This is a research task with wiremock or apisimulator.
