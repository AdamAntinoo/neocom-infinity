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
  Scenario: [NIF10.01]-Validate the structure and contents for the Mining Operations page. It has a single panel.
    Given the page "Mining Operations" is activated
    Then the page "Mining Operations" has 3 panels
    # - Check the panel contents visible on the page
        Given the target is the panel of type "mining-operations"
       Then field named "exceptionTitle" with label "TITLE" has contents "Rendering Dashboard Page. No Credential Found."
