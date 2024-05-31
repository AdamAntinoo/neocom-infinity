@NIF29 @Blueprints
Feature: [EPIC-0.29] NIF Page review and change to show the list of blueprints.

  [STORY-NIF] Replace current page with blueprints with a new backend request and then classify them by Category.
  [STORY-NIF] Create a Render for the different categories.
  [STORY-NIF] Update the render for Blueprints.

  Background: Industry authentication access
    Given the application NeoCom-Infinity-Frontend
    Given a clean cookie repository
    Given a valid NEOCOM-INFINITY cookie
    Given a valid ESI-DATA-SERVICES cookie
    Given a valid JWT Token on the session storage
    Given a valid Credential on the session storage

  # - P A G E   S T R U C T U R E
  @NIF29.01
  Scenario: [NIF29.01] Validate the structure and initial contents of the page.
    Given the route "Available Blueprints By Category" is activated
    Then the page "Available Blueprints By Category" has 4 panels
    # - Check the panel contents visible on the page
    Given the target is the panel of type "blueprint-categories"
    Given the target has the title "BLUEPRINT CATEGORIES"
    Then the target has 1 "blueprint-category"

  @NIF29.02
  Scenario: [NIF29.02] Check that the content of a category match the specification.
    Given the route "Available Blueprints By Category" is activated
    # - Check the category contents
    Given the target is the panel of type "blueprint-categories"
    When the target the "blueprint-category" with id "25"
    Then field named "name" with label "CATEGORY NAME" has contents "Asteroid"

  @NIF29.03
  Scenario: [NIF29.03] Check that the categorie is not expanded.
    Given the route "Available Blueprints By Category" is activated
    # - Check the category contents
    Given the target is the panel of type "blueprint-categories"
    Then the target has 1 "blueprint-category"
    Then the target has 0 "blueprint"

  @NIF29.04
  Scenario: [NIF29.04] Check that the category is expanded.
    Given the route "Available Blueprints By Category" is activated
    # - Check the category contents
    Given the target is the panel of type "blueprint-categories"
    Then the target has 1 "blueprint-category"
    Given the target the "blueprint-category" with id "25"
    When the target is clicked
    Given the target is the panel of type "blueprint-categories"
    Then the target has 12 "blueprint"

  @NIF29.05
  Scenario: [NIF29.05] Check that content of the blueprint matched the specification.
    Given the route "Available Blueprints By Category" is activated
    # - Check the category contents
    Given the target is the panel of type "blueprint-categories"
    Then the target has 1 "blueprint-category"
    Given the target the "blueprint-category" with id "25"
    When the target is clicked
    Given the target is the panel of type "blueprint-categories"
    When the target the "blueprint" with id "1005458706867"
    Then field named "blueprintName" with label "BLUEPRINT NAME" has contents "blueprintname"
