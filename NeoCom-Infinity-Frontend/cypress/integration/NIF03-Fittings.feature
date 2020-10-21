@NIF03 @FittingsDashboard
Feature: [NIF03]-Display the Fittings management page. Allow to select between pilot and corporation fittings.

    This page shows a title for the function covered that is the Fittings display. There is an active tab that allows to select between the pilot or the corporation fittings to be displayed. Fittings are then classified by the type of ship that they apply to, then by the ship class and finally the fitting node.

    # @NIF03 @NIF03.01
    # Scenario: [NIF03.01]-Check the general structure for the page. Verify all panels are ready.
    #     When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
    #     Given one instance of AppInfoPanel
    #     Given one instance of ServerInfoPanel
    #     Given one instance of PilotPublicDataPanel
    #     Given one instance of CorporationPublicDataPanel
    #     #     # Given one instance of CorporationDetailsDataRender
    #     #     # Given one instance of CorporationCeoRender
    #     #     # Given one instance of AllianceDataRender
    #     #     # Given one instance of PilotRender
    #     Given one instance of ActionBarPanel
    #     Then there is a "action-bar" with the next fields
    #         | title             | subtitle                                                 |
    #         | > FITTING MANAGER | Shows the list Fittings available to the selected Pilot. |
    #     Given one instance of TabContainerPanel
    #     Then there is a "tab-container-panel" with the next fields
    #         | tab-count | active-tab-name |
    #         | 2         | Pilot           |
    #     Given one instance of ViewerPanel

    # @NIF03 @NIF03.02
    # Scenario: [NIF03.02]-Check the contents for the viewer panel. Verify that the correct name and number of fittings are properly classified.
    #     When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
    #     Given one instance of ViewerPanel
    #     Then there is a viewer-panel with "4" instances of "v1-group-container"
    #     Then there is a panel of type v1-group-container with name "Frigate" and stack counter of "1"
    #     When we click on the v1-group-container with name "Frigate"
    #     Then there is a panel of type v1-group-container with name "Venture" and stack counter of "1"
    #     Then there is a panel of type v1-group-container with name "Industrial" and stack counter of "4"
    #     When we click on the v1-group-container with name "Industrial"
    #     Then there is a panel of type v1-group-container with name "Mammoth" and stack counter of "1"
    #     Then there is a panel of type v1-group-container with name "Nereus" and stack counter of "1"
    #     Then there is a panel of type v1-group-container with name "Epithal" and stack counter of "2"
    #     Then there is a panel of type v1-group-container with name "Iteron Mark V" and stack counter of "1"
    #     Then there is a panel of type v1-group-container with name "Deep Space Transport" and stack counter of "1"
    #     When we click on the v1-group-container with name "Deep Space Transport"
    #     Then there is a panel of type v1-group-container with name "Occator" and stack counter of "2"
    #     Then there is a panel of type v1-group-container with name "Strategic Cruiser" and stack counter of "1"
    #     When we click on the v1-group-container with name "Strategic Cruiser"
    #     Then there is a panel of type v1-group-container with name "Legion" and stack counter of "1"

    @NIF03 @NIF03.03
    Scenario: [NIF03.03]-Reach the Fitting level and validate the rendering for the fitting data.
        When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
        Given one instance of ViewerPanel
        Then there is a viewer-panel with "4" instances of "v1-group-container"
        When we click on the v1-group-container with name "Industrial"
        Then there is a panel of type v1-group-container with name "Epithal" and stack counter of "2"
        When we click on the v1-group-container with name "Epithal"
        Then there is a viewer-panel with "2" instances of "v1-fitting"
        Given at least one instance of V1Fitting
        Then there is a "v1-fitting" with the next fields
            | neocom-name | neocom-classname |
            | EI Oruga N2 | FITTING          |
        # And the border color of the "v1-fitting" is "panel-white"


#     Given at least one instance of V1GroupContainer
#     And there is a "v1-group-container" with the next fields
#         | neocom-icon                                 | hullClass | neocom-classname | stack-counter |
#         | /assets/res-fitting/drawable/frigate_64.png | Frigate   | SHIP GROUP       | 1             |

# @NIF03 @NIF03.02
# Scenario: [NIF03.02]-The expandable Groups has the expandable arrow and functionality.
#     When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
#     Then the node-container has a expandable arrow indicator pointing right
#     And the v1-group-container title has the glow attribute
#     And the border color of the "v1-group-container" is "panel-white"
#     # And the opacity interaction for the "v1-group-container" is "disabled"

# @NIF03 @NIF03.03
# Scenario: [NIF03.03]-Add more render nodes when the Group is expanded.
#     When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
#     And there is a click on the first node-container
#     And the node-container has a expandable arrow indicator pointing down
#     And there is a viewer-panel with "5" instances of "v1-group-container"
#     And the background color has changed to a shade of the border color "panel-white"

# @NIF03 @NIF03.04
# Scenario: [NIF03.04]-When the ship group is selected then we should see the list of fittings for that ship type.
#     When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
#     And there is a click on the v1-group-container with name "Industrial"
#     Then the selected panel has a stack counter of 2
#     And there is a click on the v1-group-container with name "Epithal"
#     Then the selected panel has a stack counter of 3
#     # And there is a viewer-panel with "3" instances of "v1-fitting"
#     # And teh selected panel is set to the v1-fitting with text "EI Oruga N2"
#     # And the selected panel has the styles "panel-orange"
#     # And the selected panel is a "v1-fitting" with the next fields
#     #     | neocom-icon                                 | neocom-selectable-name |
#     #     | https://image.eveonline.com/Type/655_64.png | EI Oruga N2            |
