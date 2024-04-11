@NIN01 @MiningOperation @Endpoint
Feature: [NIN01] Validate the api defined for the Mining Operations endpoint.

  [STORY] The ESI GET request gives a result with the current complete list as obtained from the Esi service.
  [STORY] The backend should get the ESI data and aggregate it by system and date.
  [STORY] For each system-date pair we generate a single Mining Operation that will contain the list of resources mined for that combination.

  Background: Authentication preparation
    Given a environment prepared for capsuleer 93813310
    Given a environment prepared with predefined token

  @NIN01  @NIN01.01
  Scenario: [NIN01.01] Call the ESI service and validate we get a valid response with some items
    When the endpoint 'esi/miningoperations' is activated
    Then the esi response has 6 items

  @NIN01 @NIN01.02
  Scenario: [NIN01.02] Aggregate the records obtained from ESI by system and then by date
    When the endpoint 'esi/miningoperations' is activated
    And the list of items is processed we get 3 miningoperations

  @NIN01 @NIN01.03
  Scenario: [NIN01.03] Validate that one of the resources returned has the correct contents.
    When the endpoint 'capsuleer/miningoperations' is activated
    And the Mining Operation record at position 1 has next contents
      | jsonClass       | date       | quantity | solarSystem | typeId |
      | MiningOperation | 2024-02-23 | 210      |             |        |
