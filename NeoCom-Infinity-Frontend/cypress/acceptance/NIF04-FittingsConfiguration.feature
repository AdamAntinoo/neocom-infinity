@NIF04 @FittingsConfiguration
Feature: [NIF04] The Fitting Build Configuration page allows the user to select a Build Configuration for any of the available Fittings.

    [STORY] The Fitting Build Configuration page has 3 panels. One for the order summary, another for the saved order configuration and the third for the user selected new configuration.

    Background: Application landing page
        Given the application NeoCom-Infinity
        Given the page "Fitting Build Configuration" is activated with parameters 60320161

    # - P A G E   S T R U C T U R E
    # @NIF04.01
    # Scenario: [NIF04.01]-Check that the Fitting Build Configuration page shows the required titles and structure.
    #     # - Check the parameters
    #     # Then the loading panel shows "Downloading Fitting Configuration..."
    #     When the loading panel completes
    #     Then the target has the identity 60320161
    #     # - Check the number of panels
    #     And the page "Fitting Build Configuration" has 3 panels
    #     # - Check page structure and titles
    #     # Given the target is the panel of type "fitting-summary"
    #     # Then  the target has the title "FITTING BUILD SUMMARY"
    #     Given the target is the panel of type "fitting-saved-configuration"
    #     Then  the target has the title "FITTING SAVED CONFIGURATION"
    #     Given the target is the panel of type "fitting-target-configuration"
    #     Then  the target has the title "FITTING TARGET CONFIGURATION"

    # - F I T T I N G   C O N F I G U R A T I O N
    @NIF04.02
    Scenario: [NIF04.02]-The Fitting Configuration panel of type Saved contains a user defined structure that is the Fitting Identification, the Hull and the Configuration grouped by module set or bay.
        Given the target is the panel of type "fitting-saved-configuration"
        # Then the target is tagged "white"
        Then the target has the title "FITTING SAVED CONFIGURATION"
        # - Validate the contents for section 1 - Info
        Given the target section "Fitting Info"
        # Then the target is tagged "orange"
        Then the target has the title "VM Clearer A1"
        And field named "shipType" with label "SHIP" has contents "Venture"
        And field named "hullClass" with label "HULL CLASS" has contents "FRIGATE"
        And field named "hullType" has contents "[32880]"
        And field named "hullTech" with label "TECH" has contents "Tech I"
        And image named "icon" has link "https://image.eveonline.com/Type/32880_64.png"
        And field named "sellStation" with label "STATION" has contents "Zehru IX - Moon 10 - Viziam Warehouse"
        And field named "sellPrice" with label "PRICE" has contents "325,000.00 ISK"
        And field named "hopCount" with label "HOPS" has contents "3 jumps - 6 mins."
        # - Validate the contents for section 2 - Components
        Given the target is the panel of type "fitting-saved-configuration"
        Given the target section "Fitting Components"
        Then the target has 4 groups
        # - High Slots
        Given the target the group identified "HIGH-GROUP"
        Then the target has the group title "High Slots"
        And the target has 2 "fitting-module"
        # - Mid Slots
        Given the target the group identified "MID-GROUP"
        Then the target has the group title "Mid Slots"
        # And the target has 3 "fitting-module"
        # - Low Slots
        Given the target the group identified "LOW-GROUP"
        Then the target has the group title "Low Slots"
        # And the target has 1 "fitting-module"
        # - Rig Slots
        Given the target the group identified "RIG-GROUP"
        Then the target has the group title "Rigs"
        # And the target has 1 "fitting-module"

# @NIF04.03
# Scenario: [NIF04.03]-validate the contents and structure for one of the Fitting contents.
#     Given the target is the panel of type "fitting-saved-configuration"
#     Given the target section "Fitting Components"
#     Given the target the "fitting-module" with id "9d673ab7-beb4-4f8c-8935-a92748dc8902"
#     Then image named "icon" has link "https://image.eveonline.com/Type/5245_64.png"
#     And field named "moduleName" with label "NAME" has contents "Particle Bore Compact Mining Laser"
#     And field named "sellStation" with label "STATION" has contents "Sehmosh VIII - Moon 22 - Caldari Business Tribunal Information Center"
#     And field named "sellPrice" with label "PRICE" has contents "16,010.0 ISK"
#     And field named "hopCount" with label "HOPS" has contents "4 jumps"
