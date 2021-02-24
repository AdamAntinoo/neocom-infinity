@NIF02 @DashboardHome
Feature: [NIF02]-Display the character dashboard page.

    [STORY] The Pilot Dashboard is a page that requires authentication. If valid then it should display the Application Header.
    [STORY] The Header has 2 panels. One with the application name and front end version and another with the backend and ESI services status.

    @NIF02 @NIF02.01
    Scenario: [NIF02.01]-Check that the Dashboard Home Page has the correct header panels.
        Given the application NeoCom-Infinity-Frontend
        Given a clean cookie repository
        Given a valid NEOCOM-INFINITY cookie
        Given a valid JWT Token on the session storage
        Given a valid Credential on the session storage
        When the page "Pilot Dashboard" is activated
        Then the page "Pilot Dashboard" has 2 sections
        Then the section "header" has 2 panels

    @NIF02 @NIF02.02
    Scenario: [NIF02.02]-The application info panel has the name and the front end version.
        Given the application NeoCom-Infinity-Frontend
        Given a clean cookie repository
        Given a valid NEOCOM-INFINITY cookie
        Given a valid JWT Token on the session storage
        Given a valid Credential on the session storage
        When the page "Pilot Dashboard" is activated
        Given the target is the panel of type "app-info"
        And field named "app-name" has contents "NEOCOM-INFINITY"
        And field named "app-version" has contents "0.20.0 dev"

    @NIF02 @NIF02.03
    Scenario: [NIF02.03]-The server status panel contains field information about the ESI backend status and the backend status.
        Given the application NeoCom-Infinity-Frontend
        Given a clean cookie repository
        Given a valid NEOCOM-INFINITY cookie
        Given a valid JWT Token on the session storage
        Given a valid Credential on the session storage
        When the page "Pilot Dashboard" is activated
        Given the target is the panel of type "server-info"
        And field named "serverName" has contents "Tranquility"
        And field named "serverStatus" has contents "ONLINE"
        And field named "capsuleers" has contents "26,172"
        And field named "backendVersion" has contents "0.20.0"
        And field named "sdeVersion" has contents "20201231-APP"
        And field named "nextDowntime" should exist

# This page is where to show the Corporation data along with the Pilot data. Also there is a toolbar where to select the feature to work with. There are a set of dashboard pages, each one for a different feature plus this one that will not show any feature activated.

# Background: Prepare the environment for the page Dashboard Page
#     Given one instance of AppInfoPanel
#     Given one instance of ServerInfoPanel
#     Given one instance of PilotPublicDataPanel
#     Given one instance of PilotRenderPanel
# #     Given one Dashboard Home Page
# #     Given the next authentication token
# #         | jwtToken                                                                                                                                                                                                                                                                                                                                                                |
# #         | eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTQyNzY2MTU3MywiYWNjb3VudE5hbWUiOiJBZGFtIEFudGlub28iLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkvOTIwMDIwNjciLCJwaWxvdElkIjo5MjAwMjA2N30.6JgBvtHyhvD8aY8-I4075tb433mYMpn9sNeYCkIO28LbhqVR4CZ-x1t_sk4IOLLtzSN07bF4c7ZceWw_ta4Brw |
# # Given a valid credential with the next data
# #     | uniqueId             | accountId | accountName  | corporationId |
# #     | tranquility/92002067 | 92002067  | Adam Antinoo | 1427661573    |

#     Then there is a "appinfo-panel" with the next fields
#         | app-name        | app-version | app-copyright                      |
#         | NEOCOM.INFINITY | 0.19.0 dev  | © 2019,2020 Dimensinfin Industries |
#     And there is a "serverinfo-panel" with the next fields
#         | server-name | server-status | server-capsuleers | server-laststart         |
#         | Tranquility | ONLINE        | 16,523            | 2019-10-23T11:02:41.000Z |

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

# @NIF02 @NIF02.04
# Scenario: [NIF02.04]-Validate the contents for the Pilot header panel.
#     When the DashBoardPage is activated with the request id "DASHBOARD-HOME-SUCCESS"
#     Then there is a "pilot-public-data-panel" with the next fields
#         | pilot-header |
#         | PILOT        |
#     Then there is a "pilot-render" with the next fields
#         | pilot-name  | pilot-id   | pilot-race                  | pilot-sex |
#         | Beth Ripley | [92223647] | Minmatar - Workers - Brutor | FEMALE    |

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
