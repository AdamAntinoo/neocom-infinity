@DeltaCalculator
Feature: Define the scenarios and use cases for the Delta Calculator.
    The Delta Calculator will have as input two sets of Assets and return a new list with the differences in resources from the initial input and the second input.
    Differences should be calculated as:
    - If the asset is on the initial and not on the second then the assets has been removed and should not be on the result list.
    - If the asset id matches between asset lists then a new asset is calculated with the difference between the initial and the second.
    - If the asset id only appears on the second list then this is a new asst and goes to the output list as is.

    Scenario types:
        - Type 01. The assets on the initial match the id with the assets on the second list. The result has the same input assets with the substraction of the asset contents.
        - Type 02. There is an asset on the initial that is not on the second. The number of assets descends.
        - Type 03. There is an asset on the second that is not on the initial. The number of assets increases.

    @NIN02.01
    Scenario: [NIN02.01] Describe the output when the delta calculator receives a type 01 configuration.
        Given a base asset list of type "A"
        Given a new asset list of type "A"
        When both list are entered to the Delta Calculator
        Then the output asset list has 3 assets
            And the asset at position 1 has the next values
                | id | quantity |
                | 1  | 1000     |
            And the asset list returned has the next contents
                | position | quantity |
                | 1        | 0        |
                | 2        | 0        |
                | 3        | 0        |
    @NIN02.01
    Scenario: [NIN02.02] Describe the output when the delta calculator receives a type 01 configuration.
        Given a base asset list of type "B"
        Given a new asset list of type "B"
        When both list are entered to the Delta Calculator
