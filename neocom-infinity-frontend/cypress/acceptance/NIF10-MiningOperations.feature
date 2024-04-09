@NIF10 @MiningOperations
Feature: [NIF10.01]Show the list of all mining operations available for a capsuleer.

  [STORY] When the Mining Operations Page is open then there is a panel that shows all the last capsuleer mining operations.

  Background: Industry authentication access
    Given the application NeoCom-Infinity-Frontend
    Given a clean cookie repository
    Given a valid NEOCOM-INFINITY cookie
    Given a valid JWT Token on the session storage
    Given a valid Credential on the session storage

  # - P A G E   S T R U C T U R E
  @NIF10.01
  Scenario: [NIF10.01]Show the list of all mining operations available for a capsuleer.
    Given the page "Mining Operations" is activated
    Then the page "Mining Operations" has 4 panels
    # - Check the panel contents visible on the page
    Given the target is the panel of type "mining-operations"
    Given the target has the title "MINING OPERATIONS"
    Then the target has 4 "mining-operation"

  @NIF10.02
  Scenario: [NIF10.02]-Check the contents of a single mining operation
    Given the page "Mining Operations" is activated
    Given the target the "mining-operation" with id "2024-02-23-30003541-17453"
    Then field named "quantity" with label "QUANTITY" has contents "210"
