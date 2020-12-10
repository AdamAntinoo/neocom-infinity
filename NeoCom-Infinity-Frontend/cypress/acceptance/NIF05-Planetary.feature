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

    # Scenario: [NIF05.03]-Validate the rendering contents for each of the Planetary Features.

    # - R E S O U R C E   S E A R C H   P A G E
    @NIF05.04
    Scenario: [NIF05.04]-When the Resource Research page is entered the only visible panel is the list of known systems.
        Given the page "Resource Research Page" is activated
        Then the page has the title "PLANETARY RESOURCE RESEARCH ENGINE"
        Then the target has 1 columns
        Given the target is the panel of type "known-systems"
        Then the target has the title "KNOWN SYSTEMS"

    @NIF05.05
    Scenario: [NIF05.05]-The list of known systems has some elements and contains the planet identification and the number of planets known.
        Given the page "Resource Research Page" is activated
        Given the target is the panel of type "known-systems"
        Then the target has the title "KNOWN SYSTEMS"
        Then the target has 2 "planetary-system"
        Given the target the "planetary-system" with id 30002059
        Then field named "systemName" with label "SYSTEM NAME" and value "Auner"
        And field named "at" with label "LOCATION" and value "Metropolis > Tiat"
        And field named "security" with label "SECURITY" and value "E - 0.4"
        And field named "planetCount" with label "PLANETS" and value 3

    @NIF05.06
    Scenario: [NIF05.06]-If one system element is clicked then there is another panel with the planets for that system.
        Given the page "Resource Research Page" is activated
        Given the target is the panel of type "known-systems"
        Given the target the "planetary-system" with id 30002059
        When the target is clicked
        Given the page "Resource Research Page" is activated
        Then the target has 2 columns
        Given the target is the panel of type "known-systems"
        Then the target has the title "KNOWN SYSTEMS"
        Given the target is the panel of type "system-planets"
        Then the target has the title "AUNER PLANETS"

    @NIF05.07
    Scenario: [NIF05.07]-Hovering over a system will change its render.
    @NIF05.08
    Scenario: [NIF05.08]-When a system is selected and the planets are shown then that system is visually resalted.
   @NIF05.09
    Scenario: [NIF05.09]-Each planet block contains the name, type and the resources generated.
       @NIF05.10
    Scenario: [NIF05.10]-Planets with no data registered on the database show a button to go to the data registration page.
       @NIF05.11
    Scenario: [NIF05.11]-Planets with no data registered have a different rendering and are dimmed.
