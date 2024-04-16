@NIN03 @Universe @Endpoint
Feature: [NIN03] Create aggregated data models for the EsiType and complementaty data.

  [STORY-NIN] Create on additional endpoint to access the EsiType structure removing hierarchy data for Group and Category.
  [STORY-NIN] New endpoint to get Market Data for a type at a Region.

  Background: Authentication preparation
    Given a environment prepared for capsuleer 93813310
    Given a environment prepared with predefined token

  @NIN03 @NIN03.01
  Scenario: [NIN01.01] Create on additional endpoint to access the EsiType structure removing hierarchy data for Group and Category.
    Given a request for EsiType with type 17464
    When the endpoint 'esi/esitype' is activated
    Then the esi response has a EsiType with the next fields
      | typeId | typeName         | iconId | groupId | groupName | categoryId | categoryName | volume | marketDataLink                              |
      | 17464  | Massive Scordite | 1356   | 460     | Scordite  | 25         | Asteroid     | 0.15   | /esi/v1/fuzzworks/marketData/17464/30000142 |

  @NIN03 @NIN03.02
  Scenario: [NIN01.02] New endpoint to get Market Data for a type at a Region.
    Given a request for EsiType with type 17464
    Given a request for Region 30000142
    When the endpoint 'esi/marketdata' is activated
    Then the esi response has a MarketData with the next fields
      | buy.price | buy.orders | sell.price | sell.orders |
      | 13.95     | 6.0        | 34.75      | 4           |
