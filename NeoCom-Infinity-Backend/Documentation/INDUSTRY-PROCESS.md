# INDUSTRY PROCESS
The endpoints for Industry processing should be able to generate different build
scenarios for a Fitting to search for the best or more profitable set of actions.

Each module can be *buy, built or moved*. Build actions can be complex because would
require blueprints and blueprint copy and research. A blueprint can be obtained
by *buying* it or by *research*. Blueprint copies are expected to be available from a
service will identify used blueprints and control the stock level.

## Fitting Transformation. Step 01. Buy components.
The start point is a list of Fittings. The first problem to be approached is that
Corporation fittings are not available from the ESI endpoints. The authenticated
endpoint exports the fittings available to the Pilot. Starting with this list we 
then can select a fit from the list.

* **STEP 01** - Select the list of Fittings available to a Pilot. Entering the **Pilot
unique identifier** we get the list of Fittings.
* **STEP 02** - Selecting a Fitting adds the **fit unique identifier** to the list of 
process parameters.


The fittings cannot be accessed by an identifier from the ESI endpoints. The only access if through 
the Pilot fittings list. So with that set of data we get a Fitting core data, a hull
type identifier and the list of modules and contents to that fit.

So the next step is the list of components of that Fitting. The first iteration then
is the base solution of buy all required components.

* **STEP 03** - For each one of the Fitting components search on the market data the 
market price to buy the required number of copies. This requires then the *number of copies*.

Market data has different sources. There is the ESI endpoint *markets/prices* that will provide
a first tentative price value. This value for example for the Orca hull is 1,129,708,053.54 as the
average price and 1.29 B ISK on another market data source. So direct market average prices
cannot be trusted. Another official ESI source are prices by region. This gives a detailed
list of the open transactions that allow to get a real price and real location.
Locations can be traced and the distance calculation will allow to know the number of
hops away from a predefined location.

* **STEP 04** - To get the market price for an item there should be additional data
to search on a location or influence area. So the process needs the input for a
*industry location* and the market operation *region identifier*.

The result will be then a sum of market values that will account for the total
elements required for this fit. Thw time value for this fit is then zero because there
are no industry jobs implied on this construction.

