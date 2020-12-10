@NIF05 @Planetary
Feature: [NIF05] The Planetary is a new addon that does not require authentication and allows to select the best planetary planets.

    [STORY] When the Planetary landing page is reached there are the standard application header and a panel with buttons to access
    the different planetary functionalities.
    [STORY] The Planet Search page has some different panels. Not all of them are visible on entering the page. The initial one is
    the list of eve systems that have any planet data on the repository.

    Background: Planetary landing page
        Given the application NeoCom-Infinity
        Given the page "Planetary Dashboard Page" is activated

    # - D A S H B O A R D   P A G E   S T R U C T U R E
    @NIF05.01
    Scenario: [NIF05.01]-Validate that there are two buttons.
        Then the target has 2 "feature-button"

    # @NIF05.02
    # Scenario: [NIF05.02]-Check that each button changes to the correct page.
