# NEOCOM PROJECT REQUIREMENTS
*[EPIC-0.27] New Authorization token refresh flow.*
* [STORY-OPENAPI] Create a new OpenApi project to publish the unified backend api.
* [STORY-OPENAPI] Move to this project the already available definitions for the backend api. Group the endpoints by functionality groups.
* [STORY-NIB] Create new refresh endpoint to refreh the ESI authoriation token.

*[EPIC-0.28] NIN endpoint to retrieve the list of character blueprints.*
* ***[STORY-DOMAIN]*** Create the new data structures to define assets.
* ***[STORY-NIN]*** Create the adapter to retrieve the list of character assets.
* ***[STRY-NIN]*** Create a new endpoint to retrieve the character blueprints.

[EPIC-0.29] NIF Page review and change to show the list of blueprints.
* ***[STORY-NIF]*** Review the actual code to see if there is a page to display the character blueprints.
* ***[STORY-NIF]*** Replace current page with blueprints with a new backend request and then classify them by Category.
* ***[STORY-NIF]*** Create a Render for the different categories.
* ***[STORY-NIF]*** Add Category expansion/collapse.
* ***[STORY-NIB]*** Review the access to Market Data and see if we can use the same endpoint to retrieve the data.
* [STORY-PROJECT] Make EsiType compatible between the NIB and NIF implementations.
* [STORY-NIN] Update the adapter definitions to use the new openapi definitions.


* [STORY-NIB] Create a new endpoint to retrieve Global Market Prices to be used as a base value.
* [STORY-NIB] Create a new endpoint to retrieve System specific market orders to be used to calculate a system wide price.
* [STORY-NIB] Create a new endpoint to retrieve Station item prices.



* [STORY-NIF] Update the render for Blueprints.
* [STORY-NIN] Add the type icon url to the generated DTO.
* [STORY-NIF] Stack identical blueprints into a single blueprint item with multiple instances like a stack.
* [STORY-NIF] Add a second panel to the right to show the detailed blueprint data for the selected blueprint.

[EPIC-0.30] Research if it is possible to get backe the Processed Blueprint services.
* [STORY-NIB] Activate the endpoint to retrieve the processed blueprints.
* [STORY-NIB] Generate a mock for the Processed Blueprints output.
* [STORY-NIN] Migrate the Processed Blueprint coce and use a two level service. The first level adapter will ge the data from a REST feeded second level adapter.

[EPIC-0.30] NIF Aggragate the Blueprints by location.
* [STORY-NIF] Create a new panel to show all blueprint locations. Mostly stations and under stations on Containers.


*[EPIC-0.-] PROJECT update the published api into a web service and update the schemas for the DTOs.*
* [STORY-PROJECT] Create the new web service to publish the api.
* [STORY-PROJECT] Update the schemas for the DTOs.
* [STORY-PROJECT] Upgrade Java version to the latest version. This affects the project and TeamCity.
* [STORY-NIF] Update to the latest version of Angular.
* [STORY-NIN] Update dependencies to use the latest versions.
