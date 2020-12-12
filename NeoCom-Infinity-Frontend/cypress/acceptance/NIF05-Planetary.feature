@NIF05 @Planetary
Feature: [NIF05] The Planetary is a new addon that does not require authentication and allows to select the best planetary planets.

    [STORY] When the Planetary landing page is reached there are the standard application header and a panel with buttons to access
    the different planetary functionalities.

    Background: Planetary landing page
        Given the application NeoCom-Infinity
        Given the page "Planetary Dashboard Page" is activated

    # - D A S H B O A R D   P A G E   S T R U C T U R E
    @NIF05.01
    Scenario: [NIF05.01]-Validate that there are two buttons.
        Then the target has 2 "feature"

    @NIF05.02
    Scenario: [NIF05.02]-Check that each button changes to the correct page.
        Given the page "Planetary Dashboard Page" is activated
        When the Feature with label "Enter Planet Data" is clicked the destination is the Page "Enter Planet Data Page"
        And the page "Enter Planet Data Page" has 1 panels
        Given the page "Planetary Dashboard Page" is activated
        When the Feature with label "Resource Search" is clicked the destination is the Page "Resource Research Page"
        And the page "Resource Research Page" has 1 panels
