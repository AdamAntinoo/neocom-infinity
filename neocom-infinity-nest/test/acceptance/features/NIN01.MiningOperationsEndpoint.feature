@NIN01 @MiningOperation @Endpoint
Feature: [NIN01] Validate the api defined for the Mining Operations endpoint.

  [STORY-NIN] The ESI GET request gives a result with the current complete list as obtained from the Esi service.
  [STORY-NIN] The backend should get the ESI data and aggregate it by system and date.
  [STORY-NIN] For each system-date pair we generate a single Mining Operation that will contain the list of resources mined for that combination.
  [STORY-NIN] Replace Mining Resource by the new EsiType and the grouping on the Stack.

  Background: Authentication preparation
    Given a environment prepared for capsuleer 93813310
    Given a environment prepared with predefined token

  @NIN01 @NIN01.01
  Scenario: [NIN01.01] Call the ESI service and validate we get a valid response with some items.
    When the endpoint 'esi/miningoperations' is activated
    Then the esi response has 6 items

  @NIN01 @NIN01.02
  Scenario: [NIN01.02] Aggregate the records obtained from ESI by system and then by date.
    When the endpoint 'capsuleer/miningoperations' is activated
    And the list of items is processed we get 3 MiningOperations

  @NIN01 @NIN01.03
  Scenario: [NIN01.03] Validate that one of the resources returned has the correct contents.
    When the endpoint 'capsuleer/miningoperations' is activated
    And the MiningOperation record at position 1 has next contents
      | jsonClass          | id                  | date       | solarSystemId | resourceCount |
      | MiningOperationDto | 2024-02-25/30003538 | 2024-02-25 | 30003538      | 2             |

  @NIN01 @NIN01.04
  Scenario: [NIN01.04] Check that the Mining Resources are properly generated.
    When the endpoint 'capsuleer/miningoperations' is activated
    Then the MiningOperation record at position 1 is the target
    Then the MiningOperation has 2 resources
    And the MiningResource at position 1 has next contents
      | jsonClass         | id                        | date       | quantity | solarSystemId                                                                                | typeId                                                                                  |
      | MiningResourceDto | 2024-02-25/30003538-17464 | 2024-02-25 | 10000    | https://esi.evetech.net/latest/universe/systems/30003538/?datasource=tranquility&language=en | https://esi.evetech.net/latest/universe/types/17464/?datasource=tranquility&language=en |
