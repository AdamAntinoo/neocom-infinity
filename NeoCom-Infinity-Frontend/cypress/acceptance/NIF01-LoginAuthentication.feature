@NIF01 @Login
Feature: [NIF01]-Test the validation for a valid authentication and the flow to log into the application.

    [STORY][FRONTEND] The front end Landing page has the application name, the current version and a message while the application verifies if there is an authentication token.

    # - L A N D I N G
    @NIF01.01
    Scenario: [NIF01.01]-Start the application and go to the landing page. Check that there is no authentication and the resulting page contents.
        Given the application NeoCom-Infinity-Frontend
        Given a clean cookie repository
        # - Change the api simulator behavior
        Given response "404-NOT FOUND" for "Authentication Validate"
        # - Start the page
        Then the page "Start" is activated
        Then the page "Start" has 1 panels
        And field named "title-message" has contents "NeoCom / SSO EVE Online"
        And field named "validation-message" has contents "VALIDANDO AUTENTICACION DEL PILOTO..."
        When the loading panel completes
        Then target has an actionable image named "EVE-login-button"
        And field named "validation-message" should not exist

    @NIF01.02
    Scenario: [NIF01.02]-Start the application and go to the landing page. If the authorization is valid then validate we land into the Dashboard.
        Given the application NeoCom-Infinity-Frontend
        Given a clean cookie repository
        Given a valid Credential on the session storage
        # - Start the page
        Then the page "Start" is activated
        Then the page "Start" has 1 panels
        And field named "title-message" has contents "NeoCom / SSO EVE Online"
        And field named "validation-message" has contents "VALIDANDO AUTENTICACION DEL PILOTO..."
        When the loading panel completes
        Then the page is page "Dashboard"
