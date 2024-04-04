@NIF09 @IndustryDashboard
Feature: [NIF09]-Validate and check the Industry section pages that start with a functionality selection dashboard.

    [STORY] When the Industry landing page is reached there are the standard application header and a panel with buttons to access the
    different industry functionalities.

    Background: Industry authentication access
        Given the application NeoCom-Infinity-Frontend
        Given a clean cookie repository
        Given a valid NEOCOM-INFINITY cookie
        Given a valid JWT Token on the session storage
        Given a valid Credential on the session storage

    # - D A S H B O A R D   P A G E   S T R U C T U R E
    @NIF09.01
    Scenario: [NIF09.01]-Validate the structure and contents for the Industry Dashboard page.
        Given the page "Industry Dashboard" is activated
        Then the page "Industry Dashboard" has 2 sections
        And the section "header" has 3 panels
        And the section "industry-dashboard" has 1 panels
        Given the target is the panel of type "pilot-list"
        Then the target has 1 "pilot"
        Given the target is the section named "industry-dashboard"
        Then the target has 1 "pilot"
        When the page "Industry Dashboard" is activated
        Then the target has 1 "feature-button"
        Given the target the "feature-button" with id "blueprint-analysis"
        Then the Feature has the label "Gestion de Blueprints"
        When the Feature with label "Gestion de Blueprints" is clicked the destination is the Page "Blueprint Manufacture CostIndex"
