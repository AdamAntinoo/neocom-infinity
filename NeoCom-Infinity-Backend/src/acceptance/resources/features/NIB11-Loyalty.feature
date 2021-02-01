@NIB11 @Loyalty
Feature: [FEATURE] Endpoint to retrieve loyalty offers that can be sold easily and that generate profits. There are also endpoints to set the offer
  filtering configuration.

  [STORY] If the elements for a corporation on the Loyalty repository is less than 10 then start a scan process for all the loyalty offers searching for new profitable offers.
  [STORY] New endpoint to get the list of loyalty offers that pass the configured filters.

  @NIB11.01 @Loyalty
  Scenario: [NIB11.01] When requesting the loyalty offers for a selected corporation returns a list of offers.
    Given a clean LoyaltyOffers repository
    When the Get Loyalty Offers request with corporation 1000179
    Then there is a valid response with return code of "200 OK"
    And the resulting Loyalty Offer has the next contents
      | offerId | typeId | typeName                                                | corporationId | corporationName       | lpValue | iskCost  | lpCost | quantity | regionId | price     |
      | 4256    | 27127  | Inherent Implants 'Squire' Power Grid Management EG-601 | 1000179       | 24th Imperial Crusade | 1971    | 375000   | 375    | 1        | 10000043 | 1114000.0 |
      | 4184    | 13249  | Zainou 'Deadeye' Rapid Launch RL-1003                   | 1000179       | 24th Imperial Crusade | 1520    | 10875000 | 10875  | 1        | 10000043 | 2.741E7   |
    And the LoyaltyOffers repository has 2 records

  @NIB11.02 @Loyalty
  Scenario: [NIB11.02] The front end is allowed to change the loyalty service filter configuration. Allow market region change.
    Given a Loyalty Configuration with the next values
      | marketRegionId |
      | 10000002       |
    When the Update Loyalty Service Configuration request is processed
    Then there is a valid response with return code of "200 OK"
    And the resulting Loyalty Configuration has the next contents
      | marketRegionId |
      | 10000002       |

  @NIB11.03 @Loyalty
  Scenario: [NIB11.03] If the loyalty service configuration is changed then the results also change.
    Given a clean LoyaltyOffers repository
    When the Get Loyalty Offers request with corporation 1000179
    Then there is a valid response with return code of "200 OK"
    And the LoyaltyOffers repository has 2 records
    Given a Loyalty Configuration with the next values
      | marketRegionId |
      | 10000002       |
    When the Update Loyalty Service Configuration request is processed
    Then there is a valid response with return code of "200 OK"
    When the Get Loyalty Offers request with corporation 1000179
    Then there is a valid response with return code of "200 OK"
    And the LoyaltyOffers repository has 1 records
