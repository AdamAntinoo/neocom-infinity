@NIB03 @Character
Feature: [STORY] This feature set includes the new endpoints under the /pilot.

  There is an endpoint to get the public data available for a Pilot.
  There are some endpoints to get Fittings. The list and a detailed processed data for fitting build.

  Background:
    Given "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS45MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.E7gm6lbTZj07pFI-s5yz7InzAPtgYKM2VyZR5z_5j81KGJ4OJVFT_Z-_0n3TKPpz1wbL6TYwbQ07zSQE9hfGcg" authorization token
#    Given an empty this list of Credentials stored at the repository
    Given a clean Credentials repository

  @NIB03.01 @Character
  Scenario: [NIB03.01] Search for the requested Pilot and return a well formed Pilot data response. Endpoint version 1.
    Given a request to the "Get Pilot Data" endpoint with the next data
      | pilotId  |
      | 93813310 |
    And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.eJvBC2144s7sKv5rxSUVEjNbP2BpQJlJhmTOu4AJ9eJj9so_WcrAthbvwgYM4BqyBSNZAjw7bVegieWqx8IX8Q" authorization token
    When the "Get Pilot Data" request is processed
    Then the response status code is 200
    And the response has a pilot block with the next data
      | pilotId  | corporationId | name          |
      | 93813310 | 98384726      | Perico Tuerto |

  @NIB03.02 @Character
  Scenario: [NIB03.02] When the endpoint is called we retrieve the public pilot data. Endpoint version 2.
    When the Get Pilot Public Data with pilot identifier "93813310" request is processed
    Then there is a valid response with return code of "200 OK"
    And the resulting Pilot data has the next contents
      | pilotId  | corporationId | corporation                                        | pilotPublicData | raceData | ancestryData | bloodlineData |
      | 93813310 | 98384726      | http://localhost:5240/api/v1/corporations/98384726 | <exists>        | <exists> | <exists>     | <exists>      |

  @NIB03.03 @Character
  Scenario: [NIB03.03] Get the list of fittings for the authenticated pilot. Endpoint version 1.
    Given a request to the "Get Pilot Fittings" endpoint with the next data
      | pilotId  |
      | 93813310 |
    And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.eJvBC2144s7sKv5rxSUVEjNbP2BpQJlJhmTOu4AJ9eJj9so_WcrAthbvwgYM4BqyBSNZAjw7bVegieWqx8IX8Q" authorization token
    When the "Get Pilot Fittings" request is processed
    Then the response status code is "OK"
    And the response has 9 fittings
    And the first fitting has the next data
      | fittingId | name          | hullClass |
      | 60320161  | VM Clearer A1 | Frigate   |

  @NIB03.04 @Character
  Scenario: [NIB03.04] Get the list of fittings for the authenticated pilot. Endpoint version 2.
    Given this list of Credentials stored at the repository
      | uniqueCredential     | accountId | accountName               | corporationId | dataSource  | accessToken                                                                            | refreshToken                                                                                                                                                                                     | scope                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | walletBalance | assetsCount | miningResourcesEstimatedValue |
      | tranquility.93813310 | 93813310  | Testing Character Account | 98384726      | tranquility | P940P9FpVhR8oq2V96D7pbcLzndNWTsAVgVAMt0HE5tJT15zg83MMqfsZhW1yf1XoFn9_IQJN5LrIa3NA90Ifw | 52HSB2sQiYBOrvaPidnxvnc-DIgT7DP5gUoCEOCW4v61dBfHOrCplfuwma0En0eZsLff2L6OJ6csIDTEQhqDmr0iVB6XmuNloTYhTT2Lx-x15j37Oo91jRrbHiC414DMX2nDPz-JGAdPLDtOzG2-4ofHR61rvw7sGY8Z1CnAgdGexAN6M4ZX93D_UWBEvlFd | publicData esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-mail.read_mail.v1 esi-skills.read_skills.v1 esi-skills.read_skillqueue.v1 esi-wallet.read_character_wallet.v1 esi-wallet.read_corporation_wallet.v1 esi-search.search_structures.v1 esi-clones.read_clones.v1 esi-universe.read_structures.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-fittings.read_fittings.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-clones.read_implants.v1 esi-wallet.read_corporation_wallets.v1 esi-characters.read_notifications.v1 esi-corporations.read_divisions.v1 esi-assets.read_corporation_assets.v1 esi-corporations.read_blueprints.v1 esi-contracts.read_corporation_contracts.v1 esi-industry.read_corporation_jobs.v1 esi-markets.read_corporation_orders.v1 esi-industry.read_character_mining.v1 esi-industry.read_corporation_mining.v1 | 0.0           | 0           | 0.0                           |
    When the Get Pilot Fittings request for pilot "93813310" is processed
    Then there is a valid response with return code of "200 OK"
    And the response has a list of 9 fittings
    And the fitting with id "28101737" has the next data
      | fittingId | fittingItemsCount | name               | hullClass  | shipHull | shipTypeId | shipHullClassName |
      | 28101737  | 16                | Nereus AntiGanking | Industrial | <exists> | 650        | Nereus            |
