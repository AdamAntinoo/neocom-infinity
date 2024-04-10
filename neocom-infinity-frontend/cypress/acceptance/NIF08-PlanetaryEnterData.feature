@NIF08 @PlanetaryEnterData
Feature: [NIF08] The Planetary Enter Data page allows to select a sstem, then a planet and enter/edit the resouce levels seen on the game.

    [STORY] To enter new Planet data the first step is to select the system where to select the planet. There is a system
    selection combo box that will help that selection.

    Background: Planetary Enter Data landing page
        Given the application NeoCom-Infinity

    @NIF08.01
    Scenario: [NIF08.01]-When entering the page there is a single panel with the list of Regions and a field to filter the systems by name.
        Given the page "Planetary Enter Data Page" is activated
        Then the page has the title "PLANETARY RESOURCE RESEARCH ENGINE"
        And the page "Planetary Enter Data Page" has 1 panels
        Given the target is the panel of type "system-selection"
        And the target panel has a form field named "systemFilter" with label "Sistema:" and empty
        And the target has 13 "region"

    @NIF08.02
    Scenario: [NIF08.02]-If the length of the sarch field reaches the the list of regions is replaced by a list of systems matching that filter in thir names.
        Given the page "Planetary Enter Data Page" is activated
        Then the page has the title "PLANETARY RESOURCE RESEARCH ENGINE"
        And the page "Planetary Enter Data Page" has 1 panels
        Given the target is the panel of type "system-selection"
        Given "tan" is set on form field "systemFilter"
        And the target has 5 "system"
