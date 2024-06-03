# NEOCOM PROJECT REQUIREMENTS
**[EPIC-0.21] Add Mining Operations page and functionality to see what is the input from mining.**
* **[STORY-NIN]** The backend should get the ESI data and aggregate it by system and date. For each system-date pair we generate a single Mining Operation that will contain the list of resources mined for that combination.
* **[STORY-NIN]** The backend should aggregate economic information to each of the ores on the mining operation. The economic data is the estimated value for the ore at the Amarr hub that is the predefined trading hub.
* [STORY-NIF] Volumetrics can be calculated at the frontend. For each ore the type information provides volume information that can be aggregated by ore and by system-date pair.
* **[STORY-NIF]** Date is on the format YYYY-MM-DD.
* [STORY-NIF] System information should provide the Region->Constellation->System. The hover over each of the localtion path names should provide the location unique identifier.
* [STORY-NIF] Ore box contents are still under definition. The expected content is the small icon for the ore, the ore name, the stack quantity the estimated economic value and the volume it occupies.
* [STORY-NIF] system-date panel should report information for the date, the system location path and the selected trade hub where the economic data estimation is calculated. It should also show the global volume and the global economic estimated value.

*[EPIC-0.27] New Authorization token refresh flow.*
* [STORY-OPENAPI] Create a new OpenApi project to publish the unified backend api.
* [STORY-OPENAPI] Move to this project the already available definitions for the backend api. Group the endpoints by functionality groups.
* [STORY-NIB] Create new refresh endpoint to refreh the ESI authoriation token.

*[EPIC-0.28] NIN endpoint to retrieve the list of character blueprints.*
* ***[STORY-DOMAIN]*** Create the new data structures to define assets.
* ***[STORY-NIN]*** Create the adapter to retrieve the list of character assets.
* [STRY-NIN] Create a new endpoint to retrieve the character blueprints.




*[EPIC-0.29] PROJECT update the published api into a web service and update the schemas for the DTOs.*
* [STORY-PROJECT] Create the new web service to publish the api.
* [STORY-PROJECT] Update the schemas for the DTOs.
* [STORY-PROJECT] Upgrade Java version to the latest version. This affects the project and TeamCity.
* [STORY-NIF] Update to the latest version of Angular.
* [STORY-NIN] Update dependencies to use the latest versions.



[PENDING]
* [STORY-NIN] Update the adapter definitions to use the new openapi definitions.


bluepri

[EPIC-0.30]
* [STORY.NIB] Replace the old MarketData structures that are currently unusable by the new Fuzzwork MarketData structures.
* [STORY-NIB] Create a MarketData adapter that should be able to cache the MarketData.
* [STORY-NIB] Implements the MarketData adapter to retrieve the data from Fuzzworks.
