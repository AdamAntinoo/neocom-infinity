# NEOCOM Nest Backend Requirements and Notes
## 1. Mining Sessions
Mining sessions are based on the detection of Ore assets change during time. During a mining operation the number of Ore items will be increased
and the system can detect that this increase has started and when has stopped to identify a mining operation.
This feature is based on a new service named DeltaCalculator.

## 2. Mining Session Data Flow
The flow start with a UX request to start detecting a mining session. The baclend service cannot be monitoring all characters all round 24h time for the assets since this is a quite expensive operation. It is most useful to start the signaling on the UX and from it the backend can tae care of periodically get updates of the asset stacks to get the mining accounting done

The flow then depicts the next diagram.
Figure 2.1
* The action starts on the UX the the pilot opens the mining operation monitoring page.
* That monitoring page calls a signaling endpoint that will start a **MiningOperation** Service Instance.
* That MiningOperation will reach the **AssetsAdapter** to get the list of assets for the selected pilot.
* This adapter will use the **ESISecuredDataServiceAdapter** to reach the ESI data provider and get the current capsuleer list of assets.
* The list of assets will be filtered and ordered to be used for **DeltaCalculations**.
* The aggregated data will be available to the UX. There are two solutions to update that UX, or do a periodic get to refresh the **MininsSessionData** available on the frontend, or use push to push the new data to the frontend when available.

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

