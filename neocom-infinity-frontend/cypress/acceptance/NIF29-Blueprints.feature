@NIF29 @Blueprints
Feature: [EPIC-0.29] NIF Page review and change to show the list of blueprints.

  [STORY-NIF] Replace current page with blueprints with a new backend request and then classify them by Category.
  [STORY-NIF] Create a Render for the different categories.
  [STORY-NIF] Update the render for Blueprints.

  Background: Industry authentication access
    Given the application NeoCom-Infinity-Frontend
    Given a clean cookie repository
    Given a valid NEOCOM-INFINITY cookie
    Given a valid JWT Token on the session storage
    Given a valid Credential on the session storage

  # - P A G E   S T R U C T U R E
  @NIF29.01
  Scenario: [NIF29.01] Show the list of all categories found for the character blueprints.
    Given the route "Available Blueprints By Category" is activated
    # Then the page "Available Blueprints By Category" has 4 panels
    # - Check the panel contents visible on the page
    Given the target is the panel of type "blueprint-categories"
    Given the target has the title "BLUEPRINT CATEGORIES"
    Then the target has 1 "blueprint-category"
