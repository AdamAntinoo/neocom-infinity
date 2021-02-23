@NIB05
Feature: [NIB05] This set of scenarios will test the public universe api access through the backend server.

  [STORY] When the front end requests the server status this is to a public endpoint and the returned data includes the backend server version and
  the SDE database version with some calculations about the uptime of the server and the time to the next downtime.

  This set of scenarios will test the public universe api access through the backend server. All those endpoint will not have
  authenticated because they access ESI endpoints that do not require authorization.

  @NIB05.01 @Universe
  Scenario: [NIB05.01] Access the Eve Online server status.
    When the Get Server Status request is processed
    Then there is a valid response with return code of "200 OK"
    And the resulting Server Info data has the next contents
      | server      | players | backendVersion | SDEVersion   | start_time               | startAgo                               | nextDowntime                                    |
      | tranquility | 26172   | 0.20.0         | 20201231-APP | 2021-02-23T11:04:11.000Z | 3 hours, 28 minutes and 33 seconds ago | Downtime in 19 hours, 27 minutes and 15 seconds |

  @NIB05.03 @Universe
  Scenario: [NIB05.03] Access the EsiItem data. Endpoint version 2.
    When the Get EsiItem with item id 28429 request is processed
    Then there is a valid response with return code of "200 OK"
    And the resulting EsiItem data has the next contents
      | typeId | name                | groupName | categoryName | industryGroup | hullGroup   | urlforItem                                    |
      | 28429  | Compressed Scordite | Scordite  | Asteroid     | OREMATERIALS  | NOT-APPLIES | https://image.eveonline.com/Type/28429_64.png |
