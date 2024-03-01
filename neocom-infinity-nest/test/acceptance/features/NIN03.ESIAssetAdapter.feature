@AssetAdapter
Feature: Test the Asset Adapter to download and response with list os assets from the Esi.

    The Asset Adapter will access the Esi provider with the capsuleer credentials and download the complete list of assets.
    That data will be used by other subscribed services for their operation.

    Downloaded assets will be sorted by the item_id.

    @NIN03.01
    Scenario: [NIN03.01] Get the baseline of assets. This is the first request of the series and should report a checked list.
        Given pilot information card with the next data
            | pilotId | token |
            | 10001   | 1     |
        When the AssetAdapter "character/assets" endpoint is called
        Then the number os assets downloaded is 3
            And the asset data downloaded is
                | id            | typeId | quantity |
                | 1012451140001 | 1403   | 100      |
                | 1012512980002 | 1404   | 1000     |
                | 1012512980003 | 1404   | 2000     |

