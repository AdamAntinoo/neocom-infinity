@NIF01
Feature: [STORY] Describe the structure and contents for the Dashboard page. This is the landing page for a new Login.

    The Dashboard describes the current loged Pilot and the other entities related to the dependency hierarchy.
    There are panels for Pilot public data, Corporation and Alliance.
    The story should check the asynchronous access to the Universe available data and the rendering contents.

    # - D A S H B O A R D
    @NIF01.E.01
    Scenario: [NIF01.E.01]-If there is no Credential on the local session storage then the Dashboard page should show an informational message.
        Given the application NeoCom-Infinity-frontend
        Given a valid Credential on the session storage
        # - Remove the Credential to force the exception.
        Given there is no Credential on session storage
        Then the page "Dashboard" is activated
        Then the page "Dashboard" has 1 panels
        # - Check the panel contents visible on the page
        Given the target is the panel of type "exception"
        Then field named "exceptionTitle" with label "TITLE" has contents "Rendering Dashboard Page. No Credential Found."
        And field named "exceptionMessage" with label "MESSAGE" has contents "Unable to display Pilot data. There is no credential available to access data."

    # - P I L O T
    @NIF01.01
    Scenario: [NIF01.01]-There is a Pilot panel for the logged Pilot.
        Given the Dashboard page activation
        # - Count the pilot panels that should be present on the page.
        Then the target has 1 "pilot-panel"
        Given the target the "pilot-panel" with id "92223647"
        Then the target has the title "Current Pilot"

    @NIF01.02
    Scenario: [NIF01.02]-Validate the current Pilot contents and visual rendering.
        Given the Dashboard page activation
        # - Check for the downloading pilot data message
        # And the loading panel shows "Pending Pilot Data Download..."
        # When the loading panel completes
        # - Check the Pilot panel contents
        Given the target the "pilot-panel" with id "92223647"
        Then the target has 1 "pilot"
        Given the target the "pilot" with id "93813310"
        Then field named "pilotIdentifier" has contents "[93813310]"
        And field named "pilotName" has contents "Perico Tuerto"
        And field named "pilotAncestry" has contents "Amarr - Border Runners - Ni-Kunni"
        And field named "pilotGender" has contents "Male"
        # And field named "pilotWalletBalance" has contents "654.987 ISK"
        # And field named "pilotLastLocation" with label "PILOTLASTLOCATION" has contents "location"

    @NIF01.03
    Scenario: [NIF01.03]-Check the existence of the right Features at the dashboard page and that move to the right pages.
        Given the Dashboard page activation
        Then the target has 3 "feature-button"
        Given the target the "feature-button" with id "planetary-dashboard"
        Then the Feature has the label "Interacciones Planetarias"
