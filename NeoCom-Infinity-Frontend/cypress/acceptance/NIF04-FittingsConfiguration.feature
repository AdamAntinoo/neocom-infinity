@NIF04 @FittingsConfiguration
Feature: [NIF04] The Fitting Build Configuration page allows the user to select a Build Configuration for any of the available Fittings.

    [STORY] The Fitting Build Configuration page has 3 panels. One for the order summary, another for the saved order configuration and the third for the user selected new configuration.

    Background: Application landing page
        Given the application NeoCom-Infinity
        Given the page "Fitting Build Configuration" is activated with parameters 60320161

    # - P A G E   S T R U C T U R E
    @NIF04.01
    Scenario: [NIF04.01]-Check that the Fitting Build Configuration page shows the required titles and structure.
        # - Check the parameters
        # Then the loading panel shows "Downloading Fitting Configuration..."
        When the loading panel completes
        Then the target has the identity 60320161
        # - Check the number of panels
        And the page "Fitting Build Configuration" has 3 panels
        # - Check page structure and titles
        # Given the target is the panel of type "fitting-summary"
        # Then  the target has the title "FITTING BUILD SUMMARY"
        Given the target is the panel of type "fitting-saved-configuration"
        Then  the target has the title "FITTING SAVED CONFIGURATION"
        Given the target is the panel of type "fitting-target-configuration"
        Then  the target has the title "FITTING TARGET CONFIGURATION"
