@NIN29 @Assets @Endpoint
Feature: [EPIC-0.29] NIF Page review and change to show the list of blueprints.

		* [STORY-NIN] Move the endpoint to retrieve Extended Blueprints from NIB. Leave the creation process at NIB.

	Background: Authentication preparation
		Given a environment prepared for capsuleer 93813310
		Given a environment prepared with predefined token

	@NIN29 @NIN29.01
	Scenario: [NIN29.01] Display the list of Extended Blueprints on the left panel.
		When the endpoint 'industry/ProcessedBlueprints' is activated
		Then the response is "200 OK"
		And the response body is a list of Extended Blueprints
		And the response body contains 3 items
