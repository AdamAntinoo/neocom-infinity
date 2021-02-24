@NIF02 @DashboardHome
Feature: [NIF02]-Display the character dashboard page.

    [STORY] The Pilot Dashboard is a page that requires authentication. If valid then it should display the Application Header.
    [STORY] The Header has 2 panels. One with the application name and front end version and another with the backend and ESI services status.
    [STORY] The Pilot Dashboard has a PIlot selection panel list with all the Pilots that are linked to the current logged account.

    Background: The Pilot Dashboard Page initialization
        Given the application NeoCom-Infinity-Frontend
        Given a clean cookie repository
        Given a valid NEOCOM-INFINITY cookie
        Given a valid JWT Token on the session storage
        Given a valid Credential on the session storage
        When the page "Pilot Dashboard" is activated

    # @NIF02 @NIF02.01
    # Scenario: [NIF02.01]-Check that the Dashboard Home Page has the correct header panels.
    #         Given the page "Pilot Dashboard" is activated
    # Then the page "Pilot Dashboard" has 2 sections
    #     Then the section "header" has 2 panels

    # @NIF02 @NIF02.02
    # Scenario: [NIF02.02]-The application info panel has the name and the front end version.
    #      Given the target is the panel of type "app-info"
    #     And field named "app-name" has contents "NEOCOM-INFINITY"
    #     And field named "app-version" has contents "0.20.0 dev"

    # @NIF02 @NIF02.03
    # Scenario: [NIF02.03]-The server status panel contains field information about the ESI backend status and the backend status.
    #     Given the target is the panel of type "server-info"
    #     And field named "serverName" has contents "Tranquility"
    #     And field named "serverStatus" has contents "ONLINE"
    #     And field named "capsuleers" has contents "26,172"
    #     And field named "backendVersion" has contents "0.20.0"
    #     And field named "sdeVersion" has contents "20201231-APP"
    #     And field named "nextDowntime" should exist

    @NIF02 @NIF02.04
    Scenario: [NIF02.04]-The Pilot selection bar has only one Pilot and we should check the rendered fields.
        Given the target is the panel of type "pilot-list"
        Then the target has the title "PILOTO/S"
        And the target has 1 "pilot"
        Given the target the "pilot" with id "93813310"
        Then field named "pilotName" with label "NOMBRE" has contents "Perico Tuerto"
        And field named "pilotIdentifier" with label "IDENTIFICADOR" has contents "[#93813310]"
        And field named "pilotAncestry" with label "RAZA/ANCESTROS" has contents "Amarr - Border Runners - Ni-Kunni"
        And field named "pilotGender" with label "GENERO" has contents "MALE"
        And field named "totalSkillPoints" with label "SKILL POINTS" has contents "8,404,419"
        And field named "pilotWalletBalance" with label "BALANCE" has contents "2,766 MISK"
        And field named "currentShip" with label "SHIP" has contents "Zach Zender's Velator"

# This page is where to show the Corporation data along with the Pilot data. Also there is a toolbar where to select the feature to work with. There are a set of dashboard pages, each one for a different feature plus this one that will not show any feature activated.



# # @NIF02 @NIF02.02
# # Scenario: [NIF02.02]-The corporation data for the logged character is shown below the header panel.
# #     When the DashBoardPage is activated with the request id "DASHBOARD-HOME-SUCCESS"
# #     Then there is a "corporation-public-data-panel" with corporation contents
# #         | corporation-header | corporation-ceo-header | alliance-header |
# #         | CORPORATION        | CORPORATION CEO        | ALLIANCE        |

# # And there is a "corporation-render" with variant "-HEADER-" with the next fields
# #     | corporation-name | corporation-id | corporation-ticker | corporation-members-count |
# #     | Planet-Express   | [#1427661573]  | [PLAM]             | 8 MEMBERS                 |
# # And there is a "corporationceo-panel"
# # And there is a "alliance-panel"
# # And there is a "pilotpublicdata-panel"
# # And there is a "pilotprivatedata-panel"


# @NIF02 @NIF02.05
# Scenario: [NIF02.05]-Check that there is a tab list with no tab selected.
#     When the DashBoardPage is activated with the request id "DASHBOARD-HOME-SUCCESS"
#     Then there is a tab-container-panel with 2 tabs
#     And there is no tab selected

# @NIF02 @NIF02.06
# Scenario: [NIF02.06]-If the Fittings tab is selected then we move to the Fittings Dashboard.
#     When the DashBoardPage is activated with the request id "DASHBOARD-HOME-SUCCESS"
#     Then there is a tab-container-panel with 2 tabs
#     And if the Fittings tab is selected we land on the FittingsDashboard page
