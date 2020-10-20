@NIB02 @Corporation
Feature: [NIB02] Features produces by the Corporation controller. Access to the Corporation data and to the assets if different
  ways.

  Retrieve the Corporation data to be accessed on first access. Check that the caller is authorized and that the authorization
  allows the access to the selected Corporation.
  Get Corporation ship build yards and the other location data from the list of Corporation assets.
  Get the Corporation assets organized on a hierarchy classified by asset location.

  @NIB02.01 @Corporation
  Scenario: [NIB02.01] The authorization token does not exist on the request.
    Given a request to the "Get Corporation Data" endpoint with the next data
      | corporationId |
      | 123456        |
    And "<null>" authorization token
    When the "Get Corporation Data" request is processed
    Then the response status code is 403

  @NIB02.02 @Corporation
  Scenario: [NIB02.02] The authorization token corporation identifier does not match the requested corporation.
    Given a request to the "Get Corporation Data" endpoint with the next data
      | corporationId |
      | 123456        |
    And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTM4MTMzMTAsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.g4VqcRxThHb9g0Ln24yix8zbu7kIplA4oIOuU5LM53v0pvEtrotWaBAF8zS9zELmm2_10QJhhdSdamSI9ntjdw" authorization token
    When the "Get Corporation Data" request is processed
    Then the response status code is 403
#    And the exception message is "The corporation requested is not authorized to the requester."

  @NIB02.03 @Corporation
  Scenario: [NIB02.03] The authorization matches and the response is a valid Corporation data block.
    Given a request to the "Get Corporation Data" endpoint with the next data
      | corporationId |
      | 93813310      |
    And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTM4MTMzMTAsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.g4VqcRxThHb9g0Ln24yix8zbu7kIplA4oIOuU5LM53v0pvEtrotWaBAF8zS9zELmm2_10QJhhdSdamSI9ntjdw" authorization token
    When the "Get Corporation Data" request is processed
    Then the response status code is 200
#    And the exception message is "The corporation requested is not authorized to the requester."

  @NIB02.04 @Corporation
  Scenario: [NIB02.04] Given a set of few assets validate the resulting hierarchy with details deep checks. Use the simplest
  asset configuration that is a single asset inside a corporation container at the first office.
    Given a request to the "Get Corporation Assets By Location" endpoint with the next data
      | corporationId |
      | 98384726      |
    And authorization token contained in file "/Corporation/Authorization.Bearer.txt"
    When the "Get Corporation Assets By Location" request is processed
    Then the response status code is 200
    And the resulting list of locations has "2" elements
    And the resulting list has the next data
      | jsonClass              | containerType | contentsCount | region | constellation | system   | station                                               |
      | LocationAssetContainer | SPACE         | 1             | Domain | Liela         | Avair    | Avair VII - Moon 25 - Theology Council Tribunal       |
      | LocationAssetContainer | SPACE         | 1             | Devoid | Ryra          | Esescama | Esescama VIII - Moon 3 - Imperial Armaments Warehouse |
