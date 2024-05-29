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
		# Given a request for Character with id 93813310
		When the endpoint 'esi/Assets' is activated
		Then the response is "200 OK"
		And the response body is a list of assets
		And the response body contains 14 asset

	@NIN28 @NIN28.02
	Scenario: [NIN28.02] Get a list of the blueprints belonging to the selected character.
		When the endpoint 'esi/Blueprints' is activated
		Then the response is "200 OK"
		And the response body is a list of blueprints
		And the response body contains 14 blueprint
