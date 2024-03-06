@MiningOperations
Feature: Scenarios to create a new mining operation form the UX, then start monitoring the Esi assets source for updates and finally create a report of the mining operation ore processed.

  - The mining operation is started from the UX when the used likes to start recording the collection of ore assets. The initial state is OPEN.
  - During the mining operation we are processing the deltas of assets between a previous state and the new current state to detect the ore assets. When we get a delta with some ore assets the we start the operation and set the start datetime to the previous cycle timstamp and set the state to RUNNING.
  - Because this delta processing is subject to external interferences at the end of the process there is an adjustment using the complete set of ore assets from the start point in time to the end point in time.
  - End of operation is detected when two consecutive deltas do not provide more ore assets. At this point the Mining Operation is changed to the COMPLETE state.
  - The delta processing is controlled by an scheduled job. This job periodically downloads the list of assets for a capsuleer, orders them, filters only the Ore assets and then calculates the delta that is added to the delta aggregate on the active mining operation.

  @NIN04.01
  Scenario: [NIN04.01] A new mining operation is started from the UX with the use case StartMiningOperation.
    Given an empty MiningOperations repository
    Then the number of MiningOperations persisted is 0
    When a use case "StartMiningOperation" is executed for character "10001"
    Then there is a new MiningOperation created by the StartMiningOperation use case
    And the MiningOperation data is set to
      | id   | characterId | state | startdate | deltaAssetList |
      | <id> | 10001       | OPEN  | undefined | []             |
    And the mining operation is persisted at the MiningOperationsRepository
    And the number of MiningOperations persisted is 1
    And a new scheduled job with name "10001"

  @NIN04.02
  Scenario: [NIN04.02] If a second mining operation is started and there is one running for the same pilot the result is the same current operation.

  @NIN04.03
  Scenario: [NIN04.03] When the scheduled job is created the current list of assets is ordered, filtered and stored on the mining operation.
    Given an empty MiningOperations repository
    When a use case "StartMiningOperation" is executed for character 10001
    Then a new scheduled job with name "10001"
    And scheduled job has a MiningOperation for character "10001"
    And the scheduled job gets the initial list of assets with count 3
