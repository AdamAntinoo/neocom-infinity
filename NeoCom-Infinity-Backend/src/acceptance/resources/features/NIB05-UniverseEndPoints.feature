@NIB05
Feature: [NIB05] This set of scenarios will test the public universe api access through the backend server.

  This set of scenarios will test the public universe api access through the backend server. All those endpoint will not have
  authenticated because they access ESI endpoints that do not require authorization.

  @NIB05.01 @Universe
  Scenario: [NIB05.01] Access the Eve Online server status.
    When the Get Server Status request with datasource "Tranquility" is processed
    Then there is a valid response with return code of "200 OK"
    And the resulting Server Info data has the next contents
      | players | server_version | start_time           |
      | 25003   | 1589237        | 2019-10-22T11:04:50Z |

#  @NIB05.02 @Universe
#  Scenario: [NIB05.02] Access the NeoCom item data. Endpoint version 1.
#    When the Get NeoItem with item id "28429" request is processed
#    Then there is a valid response with return code of "200 OK"
#    And the resulting NeoItem data has the next contents
#      | pilotId  | corporationId | corporation                                        | pilotPublicData | raceData | ancestryData | bloodlineData |
#      | 93813310 | 98384726      | http://localhost:5240/api/v1/corporations/98384726 | <exists>        | <exists> | <exists>     | <exists>      |

  @NIB05.03 @Universe
  Scenario: [NIB05.03] Access the EsiItem data. Endpoint version 2.
    When the Get EsiItem with item id 28429 request is processed
    Then there is a valid response with return code of "200 OK"
    And the resulting EsiItem data has the next contents
      | typeId | name                | groupName | categoryName | industryGroup | hullGroup   | urlforItem                                    |
      | 28429  | Compressed Scordite | Scordite  | Asteroid     | OREMATERIALS  | NOT-APPLIES | https://image.eveonline.com/Type/28429_64.png |

