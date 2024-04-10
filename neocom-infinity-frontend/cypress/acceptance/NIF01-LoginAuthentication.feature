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
        And target has link pointing "https://login.eveonline.com/v2/oauth/authorize/?response_type=code&client_id=eacaa9cd36594189877544d851753734&state=LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0=&redirect_uri=http%3A%2F%2Flocalhost%3A5202%2Fapp%2FloginValidation&scope=publicData%20esi-corporations.read_structures.v1%20esi-characters.read_loyalty.v1%20esi-industry.read_character_jobs.v1%20esi-characters.read_corporation_roles.v1%20esi-industry.read_character_mining.v1"

    @NIF01.02
    Scenario: [NIF01.02]-Start the application and go to the landing page. If the authorization is valid then validate we land into the Dashboard.
        Given the application NeoCom-Infinity-Frontend
        Given a clean cookie repository
        Given a valid NEOCOM-INFINITY cookie
        Given a valid JWT Token on the session storage
        Given a valid Credential on the session storage
        # - Start the page
        Then the page "Start" is activated
        Then the page "Start" has 1 panels
        And field named "title-message" has contents "NeoCom / SSO EVE Online"
        And field named "validation-message" has contents "VALIDANDO AUTENTICACION DEL PILOTO..."
        When the loading panel completes
        Then the page is page "Pilot Dashboard"
