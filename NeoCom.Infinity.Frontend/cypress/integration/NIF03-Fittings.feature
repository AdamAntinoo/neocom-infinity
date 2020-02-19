@NIF03 @FittingsDashboard
Feature: [NIF03]-Display the Fittings management page. Allow to select between pilot and corporation fittings.

    This page shows a title for the function covered that is the Fittings display. There is an active tab that allows to select between the pilot or the corporation fittings to be displayed. Fittings are then classified by the type of ship that they apply to, then by the ship class and finally the fitting node.

    Background: Check the existence of the right panels on the page Fittings Dashboard Page
        Given one instance of ServerInfoPanel
    # Given one instance of CorporationPublicDataPanel
    # Given one instance of CorporationDetailsDataRender
    # Given one instance of CorporationCeoRender
    # Given one instance of AllianceDataRender
    # Given one instance of PilotPublicDataPanel
    # Given one instance of PilotRender

    @NIF03 @NIF03.01
    Scenario: [NIF03.01]-Check that the fittings data section has the function title and shows the right set of fittings.
        When the PilotFittingsPage is activated with the request id "PILOT-FITTINGS"
        Given one instance of AppInfoPanel
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
        # Given one instance of V1GroupContainer
        And there is a "v1-group-container" with the next fields
            | neocom-icon                                 | hullClass | neocom-classname | stack-counter |
            | /assets/res-fitting/drawable/frigate_64.png | Frigate   | SHIP GROUP       | 1             |
