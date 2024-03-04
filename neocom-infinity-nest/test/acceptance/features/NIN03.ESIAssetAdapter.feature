@AssetAdapter
Feature: Test the Asset Adapter to download and response with list os assets from the Esi.

    The Asset Adapter will access the Esi provider with the capsuleer credentials and download the complete list of assets.
    That data will be used by other subscribed services for their operation.

    Downloaded assets will be sorted by the item_id.

    @NIN03.01
    Scenario: [NIN03.01] Get the selected capsuleer complete list of assets.
        Given pilot information card with the next data
            | pilotId |
            | 10001   |
        When the AssetAdapter "character/assets" endpoint is called
        Then the number os assets downloaded is 3
            And the asset data downloaded is
                | id            | typeId | quantity |
                | 1012451140001 | 1403   | 2000     |
                | 1012512980002 | 1404   | 1000     |
                | 1012512980003 | 1404   | 3000     |

    @NIN03.02
    Scenario: [NIN03.02] Be sure that the assets are ordered by item_id.
        Given pilot information card with the next data
            | pilotId |
            | 10001   |
        When the AssetAdapter "character/assets" endpoint is called
        Then the number os assets downloaded is 3
            And the asset data downloaded is
                | id            | typeId | quantity |
                | 1012451140001 | 1403   | 2000     |
                | 1012512980002 | 1404   | 1000     |
                | 1012512980003 | 1404   | 3000     |

