@NIF07 @Blueprint
Feature: [NIF07] The analisys for the list of blueprints available to a Pilot. Obtains the cost index by using the manufacture cost
and the module market value.

    [STORY][FRONTEND] The new Industry section should have a page with all the available Blueprints with some manufacture cost and
     a profit index. This section is authenticated.

    Background: manufacture landing page
        Given the application NeoCom-Infinity

    # - R E S O U R C E   S E A R C H   P A G E
    @NIF07.01
    Scenario: [NIF07.01]-When the Industry Manufacture Blueprints page is entered there is a panel with the list of blueprints available to the Pilot.
        When activate the page Industry Manufacture Blueprints Page
        Then the page "Blueprint Manufacture CostIndex" has 1 panels
        Given the target is the panel of type "available-blueprints"
        Then the target has the title "AVAILABLE BLUEPRINTS"
