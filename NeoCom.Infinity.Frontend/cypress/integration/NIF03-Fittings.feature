@NIF03 @FittingsDashboard
Feature: [NIF03]-Display the Fittings management page. Allow to select between pilot and corporation fittings.

    This page shows a title for the function covered that is the Fittings display. There is an active tab that allows to select between the pilot or the corporation fittings to be displayed. Fittings are then classified by the type of ship that they apply to, then by the ship class and finally the fitting node.

    Background: Check the existence of the right panels on the page Fittings Dashboard Page
    @NIF03 @NIF03.01
    Scenario: [NIF03.01]-Check that the fittings data section has the function title and shows the right set of fittings.
        When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
        Given one instance of AppInfoPanel
        Given one instance of ServerInfoPanel
        # Given one instance of CorporationPublicDataPanel
        # Given one instance of CorporationDetailsDataRender
        # Given one instance of CorporationCeoRender
        # Given one instance of AllianceDataRender
        # Given one instance of PilotPublicDataPanel
        # Given one instance of PilotRender
        Given one instance of ActionBarPanel
        Then there is a "action-bar" with the next fields
            | title             | subtitle                                                 |
            | > FITTING MANAGER | Shows the list Fittings available to the selected Pilot. |
        Given one instance of TabContainerPanel
        Then there is a "tab-container-panel" with the next fields
            | tab-count | active-tab-name |
            | 2         | Pilot           |
        Given one instance of ViewerPanel
        Then there is a viewer-panel with "4" instances of "v1-group-container"
        Given at least one instance of V1GroupContainer
        And there is a "v1-group-container" with the next fields
            | neocom-icon                                 | hullClass | neocom-classname | stack-counter |
            | /assets/res-fitting/drawable/frigate_64.png | Frigate   | SHIP GROUP       | 1             |
  
    @NIF03 @NIF03.02
    Scenario: [NIF03.02]-The expandable Groups has the expandable arrow and functionality.
        When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
        Then the node-container has a expandable arrow indicator pointing right
        And the v1-group-container title has the glow attribute
        And the border color of the "v1-group-container" is "WHITE"
        # And the opacity interaction for the "v1-group-container" is "disabled"

    @NIF03 @NIF03.03
    Scenario: [NIF03.03]-Add more render nodes when the Group is expanded.
        When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
        And there is a click on the first node-container
        # Then the v1-group-container expands to render its contents
        And the node-container has a expandable arrow indicator pointing down
        # And the background color has changed to a shade of the border color
        And there is a viewer-panel with "5" instances of "v1-group-container"
