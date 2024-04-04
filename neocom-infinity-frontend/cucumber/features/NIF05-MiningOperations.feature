@NIF05 @DashboardHome
Feature: [NIF05]-Fetch mining data along the time.

  - Use the ESI Data Provoder to retrieve mining information from a character and obtain that data at different times.
  - Provide that information as collected since there is no need for data transformation.

  @NIF05 @NIF05.01
  Scenario: [NIF05.01]-Get the initial set of mining data. This is controlled by the character being used for retrieval.
    Given a valid credential with the next data
      | uniqueId             | accountId | accountName   | corporationId |
      | tranquility/93813310 | 93813310  | Perico Tuerto | 1427661573    |
    When the ESI endpoint "Get Mining Data" is called
    Then the result is "200 OK"
    And there are 4 mining records
    And mining records content is
      | date       | quantity | solar_system_id | type_id |
      | 2024-02-23 | 210      | 30003541        | 17453   |
      | 2024-02-23 | 34243      | 30003538        | 17464   |
