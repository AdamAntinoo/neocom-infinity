@NIF06 @PlanetaryResearch
Feature: [NIF06] The Planetary Research page allow to seach for the best planet to get a selected list of resources.

    [STORY] The Planet Search page has some different panels. Not all of them are visible on entering the page. The initial one is
    the list of eve systems that have any planet data on the repository.

    Background: Planetary landing page
        Given the application NeoCom-Infinity
        Given the page "Planetary Dashboard Page" is activated

    # - R E S O U R C E   S E A R C H   P A G E
    @NIF06.01
    Scenario: [NIF06.01]-When the Resource Research page is entered the only visible panel is the list of known systems.
        Given the page "Resource Research Page" is activated
        Then the page has the title "PLANETARY RESOURCE RESEARCH ENGINE"
        Then the target has 1 columns
        Given the target is the panel of type "known-systems"
        Then the target has the title "KNOWN SYSTEMS"

    @NIF06.02
    Scenario: [NIF06.02]-The list of known systems has some elements and contains the planet identification and the number of planets known.
        Given the page "Resource Research Page" is activated
        Given the target is the panel of type "known-systems"
        Then the target has the title "KNOWN SYSTEMS"
        Then the target has 2 "planetary-system"
        Given the target the "planetary-system" with id 30002059
        Then field named "systemName" with label "SYSTEM NAME" and value "Auner"
        And field named "at" with label "LOCATION" and value "Metropolis > Tiat"
        And field named "security" with label "SECURITY" and value "E - 0.4"
        And field named "planetCount" with label "PLANETS" and value 3

    @NIF06.03
    Scenario: [NIF06.03]-If one system element is clicked then there is another panel with the planets for that system.
        Given the page "Resource Research Page" is activated
        Given the target is the panel of type "known-systems"
        Given the target the "planetary-system" with id 30002059
        When the target is clicked
        Given the page "Resource Research Page" is activated
        Then the target has 3 columns
        Given the target is the panel of type "known-systems"
        Then the target has the title "KNOWN SYSTEMS"
        Given the target is the panel of type "system-planets"
        Then the target has the title "AUNER PLANETS"
        Given the target is the panel of type "selected-planets"
        Then the target has the title "SELECTED PLANETS"

    @NIF06.04
    Scenario: [NIF06.04]-Hovering over a system will change its render.
    # @NIF06.08
    # Scenario: [NIF06.08]-When a system is selected and the planets are shown then that system is visually resalted.
    @NIF06.05
    Scenario: [NIF06.05]-Each planet block contains the name, type and the resources generated.
    # @NIF06.06
    # Scenario: [NIF06.06]-Planets with no data registered on the database show a button to go to the data registration page.
    # @NIF06.07
    # Scenario: [NIF06.07]-Planets with no data registered have a different rendering and are dimmed.
    @NIF06.08
    Scenario: [NIF06.08]-Planets with data can be dragged to the list of selected planets.
