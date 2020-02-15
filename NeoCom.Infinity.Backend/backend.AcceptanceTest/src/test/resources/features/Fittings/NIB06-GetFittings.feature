@NIB06
Feature: [NIB06] Get the fitting information for the Pilot or for the Pilot's Corporation.

  Retrieve the list of fittings along with the modules and items that compose a Fitting. The list of fittings can be the list
  belonging to the Pilot ot the list belonging to the Corporation.

  @NIB06.01 @Fitting
  Scenario: [NIB06.01] Get the list of fittings for the authenticated pilot.
    Given a request to the "Get Pilot Fittings" endpoint with the next data
      | pilotId  |
      | 93813310 |
    And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.eJvBC2144s7sKv5rxSUVEjNbP2BpQJlJhmTOu4AJ9eJj9so_WcrAthbvwgYM4BqyBSNZAjw7bVegieWqx8IX8Q" authorization token
    When the "Get Pilot Fittings" request is processed
    Then the response status code is "OK"
    And the response has 9 fittings
    And the first fitting has the next data
      | fittingId | name          | hullClass |
      | 60320161  | VM Clearer A1 | Frigate   |

  @NIB06.02 @Fitting
  Scenario: [NIB06.02] The pilot authentication fails so the result is the expected exception.
    Given a request to the "Get Pilot Fittings" endpoint with the next data
      | pilotId  |
      | 93813399 |
    And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.eJvBC2144s7sKv5rxSUVEjNbP2BpQJlJhmTOu4AJ9eJj9so_WcrAthbvwgYM4BqyBSNZAjw7bVegieWqx8IX8Q" authorization token
    When the "Get Pilot Fittings" request is processed
    Then the response status code is "FORBIDDEN"
    And the exception is of type "NeoComAuthorizationException"

  @NIB06.03 @Fitting
  Scenario: [NIB06.03] The pilot authenticated has no fittings. This should raise an exception.
    Given a request to the "Get Pilot Fittings" endpoint with the next data
      | pilotId  |
      | 93813311 |
    And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.eJvBC2144s7sKv5rxSUVEjNbP2BpQJlJhmTOu4AJ9eJj9so_WcrAthbvwgYM4BqyBSNZAjw7bVegieWqx8IX8Q" authorization token
    When the "Get Pilot Fittings" request is processed
    Then the response status code is "NOT_FOUND"
    And the exception is of type "NeoComNotFoundException"
