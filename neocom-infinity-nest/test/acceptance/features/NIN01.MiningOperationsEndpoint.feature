@NIN01 @MiningOperation @Endpoint
Feature: [NIN01]-Validate the api defined for the Mining Operations endpoint.

  [STORY] When the GET request arrives the result should be the current complete list as ontained from the Esi service.
  [STORY] Since data on the Esi endpoint will be removed after 30 days may be it will be necessary to persist old information. This is not defined.
  [STORY] There is a filter option for the GET endpoint that will only retrieve the current date mined resources.
  [STORY] Mining Operation data should be pushed to the server. To be defined.

  @NIN01.01
  Scenario: [NIN01.01]-Get the Esi mined resources and transform them into the MiningOperation data expected by the frontend.
    Given a environment prepared for capsuleer 93813310
    When the endpoint 'capsuleer/miningoperations' is activated
    And the response has 4 items

  @NIN01.02
  Scenario: [NIN01.02]-Validate that one of the resources returned has the correct contents.
    Given a environment prepared for capsuleer 93813310
    When the endpoint 'capsuleer/miningoperations' is activated
    And the Mined Resources record at position 1 has next contents
      | jsonClass       | date       | quantity | solarSystem | typeId |
      | MiningOperation | 2024-02-23 | 210      |             |        |
