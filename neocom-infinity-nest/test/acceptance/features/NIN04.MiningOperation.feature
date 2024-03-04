@MiningOperationsManager
Feature: Scenarios to create a new mining operation form the UX, then start monitoring the Esi assets source for updates and finally create a report of the mining operation ore processed.

    The mining operation is started from the UX when the used likes to start recording the collection of ore assets. The initial state is OPEN.
    During the mining operation we are processing the deltas of assets between a previous state and the new current state to detect the ore assets. When we get a delta with some ore assets the we start the operation and set the start datetime to the previous cycle timstamp and set the state to RUNNING.
    Because this delta processing is subject to external interferences at the end of the process there is an adjustment using the complete set of ore assets from the start point in time to the end point in time.
    End of operation is detected when two consecutive deltas do not provide more ore assets. At this point the Mining Operation is changed to the COMPLETE state.

    Background:
        Given an empty MiningOperations repository

    @NIN04.01
    Scenario: [NIN04.01] A new mining operation is started from the UX with the use case StartMiningOperation.
        When a use case "StartMiningOperation" is executed
        Then there is a new MiningOperation created by the MiningOperationsManager
            And the mining operation is persisted at the MiningOperationsRepository
            And the MiningOperation data is set to
                | id   | state | startdate | deltaAssetist |
                | <id> | OPEN  | undefined | []            |

