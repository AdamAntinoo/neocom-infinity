@NIF07 @Blueprint
Feature: [NIF07] The analisys for the list of blueprints available to a Pilot. Obtains the cost index by using the manufacture cost
    and the module market value.

    [STORY][FRONTEND] The new Industry section should have a page with all the available Blueprints with some manufacture cost and
    a profit index. This section is authenticated.

    Background: Industry Manufacture autehntication access
        Given the application NeoCom-Infinity-Frontend
        Given a clean cookie repository
        Given a valid NEOCOM-INFINITY cookie
        Given a valid JWT Token on the session storage
        Given a valid Credential on the session storage

    # - R E S O U R C E   S E A R C H   P A G E
    @NIF07.01
    Scenario: [NIF07.01]-When the Industry Manufacture Blueprints page is entered there is a panel with the list of blueprints available to the Pilot.
        Given the page "Blueprint Manufacture CostIndex" is activated
        Then the page "Blueprint Manufacture CostIndex" has 2 sections
        Then the section "header" has 2 panels
        # - Select the content section
        Given the target is the section named "industry-dashboard"
        Then the section has the title "INDUSTRIA -> ANALISIS BLUEPRINTS"
        And the section "industry-dashboard" has 2 panels
        # - Select the panel with the list of blueprints
        Given the target is the panel of type "available-blueprints"
        Then the panel loading message contains "DESCARGANDO BLUEPRINTS..."
        When the panel loaging message completes
        Then the target has 5 "processed-blueprint"

    @NIF07.02
    Scenario: [NIF07.02]-On this page there is a list of Blueprints. Validate the content information for any of the blueprints.
        Given the page "Blueprint Manufacture CostIndex" is activated
        Given the target is the panel of type "available-blueprints"
        Given the target the "processed-blueprint" with id "BCI:93813310:31741"
        Then field named "blueprintName" with label "BLUEPRINT" has contents "Small Kinetic Shield Reinforcer I Blueprint"
        And field named "moduleProduced" with label "OUTPUT" has contents "Small Cargohold Optimization I"
        And field named "manufactureCost" with label "COSTE FABRICACION" has contents "9,037 ISK"
        And field named "outputPrice" with label "PRECIO VENTA" has contents "10,880 ISK"
        And field named "costIndex" with label "INDICE" has contents "108"
