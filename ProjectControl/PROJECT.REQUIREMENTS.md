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
* [STORY-NIF] Replace current page with blueprints with a new backend request and then classify them by Category.
* [STORY-NIF] Create a Render for the different categories.
* [STORY-NIF] Update the render for Blueprints.



*[EPIC-0.29] PROJECT update the published api into a web service and update the schemas for the DTOs.*
* [STORY-PROJECT] Create the new web service to publish the api.
* [STORY-PROJECT] Update the schemas for the DTOs.
* [STORY-PROJECT] Upgrade Java version to the latest version. This affects the project and TeamCity.
* [STORY-NIF] Update to the latest version of Angular.
* [STORY-NIN] Update dependencies to use the latest versions.



[PENDING]
* [STORY-NIN] Update the adapter definitions to use the new openapi definitions.
