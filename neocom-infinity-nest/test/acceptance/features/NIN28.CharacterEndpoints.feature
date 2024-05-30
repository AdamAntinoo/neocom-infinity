@NIN28 @Assets @Endpoint
Feature: [NIN28] NIN endpoint to retrieve the list of character assets.

	[STORY-DOMAIN] Create the new data strucures to define assets.
	[STORY-NIN] Create the adapter to retrieve the list of character assets.
	[STORY-NIN] Update the adapter definitions to use the new openapi definitions.

	Background: Authentication preparation
		Given a environment prepared for capsuleer 93813310
		Given a environment prepared with predefined token

	@NIN28 @NIN28.01
	Scenario: [NIN28.01] Get a list of assets when requesting them on the endpoint.
		When the endpoint 'esi/Assets' is activated
		Then the response is "200 OK"
		And the response body is a list of assets
		And the response body contains 14 asset

	@NIN28 @NIN28.02
	Scenario: [NIN28.02] Get a list of the blueprints belonging to the selected character.
		When the endpoint 'esi/Blueprints' is activated
		Then the response is "200 OK"
		And the response body is a list of blueprints
		And the response body contains 12 blueprint

	@NIN28 @NIN28.03
	Scenario: [NIN28.03] From the list of blueprints validate the contents for a copy blueprint.
		When the endpoint 'esi/Blueprints' is activated
		Then the blueprint at position 3 has next contents
			| jsonClass    | id            | materialEfficiency | timeEfficiency | runs | original | typeLink                            | storageLocation.locationType | storageLocation.locationLink            |
			| BlueprintDto | 1005458732399 | 10                 | 20             | 100  | false    | /api/v3/neocom/universe/types/31395 | Hangar                       | /esi/v1/universe/spacelocation/60006907 |

	@NIN28 @NIN28.04
	Scenario: [NIN28.04] From the list of blueprints validate the contents for a original blueprint.
		When the endpoint 'esi/Blueprints' is activated
		Then the blueprint at position 2 has next contents
			| jsonClass    | id            | materialEfficiency | timeEfficiency | runs | original | typeLink                            | storageLocation.locationType | storageLocation.locationLink            |
			| BlueprintDto | 1005458706867 | 10                 | 20             | -1   | true     | /api/v3/neocom/universe/types/31118 | Hangar                       | /esi/v1/universe/spacelocation/60006907 |
