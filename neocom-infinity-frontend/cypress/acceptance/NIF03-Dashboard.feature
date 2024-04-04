@NIF01
Feature: [STORY] Describe the structure and contents for the Dashboard page. This is the landing page for a new Login.

    The Dashboard describes the current loged Pilot and the other entities related to the dependency hierarchy.
    There are panels for Pilot public data, Corporation and Alliance.
    The story should check the asynchronous access to the Universe available data and the rendering contents.

    # - D A S H B O A R D
    @NIF01.E.01
    Scenario: [NIF01.E.01]-If there is no Credential on the local session storage then the Dashboard page should show an informational message.
        Given the application NeoCom-Infinity-Frontend
        Given a valid Credential on the session storage
        # - Remove the Credential to force the exception.
        Given there is no Credential on session storage
        Then the page "Dashboard" is activated
        Then the page "Dashboard" has 1 panels
        # - Check the panel contents visible on the page
        Given the target is the panel of type "exception"
        Then field named "exceptionTitle" with label "TITLE" has contents "Rendering Dashboard Page. No Credential Found."
        And field named "exceptionMessage" with label "MESSAGE" has contents "Unable to display Pilot data. There is no credential available to access data."
