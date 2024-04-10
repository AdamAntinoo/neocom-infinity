@NIB10 @Market
Feature: [FEATURE] New endpoints to retrieve processed and digested market data. Some endpoints may not require authentication.

  [STORY] There is a new endpoint to get the compacted market data. This endpoint depends on the Pilot configuration about the preferred
  market and other settings no needs to be authenticated to identify the target pilot.
  [STORY] Items have a HAL link to the optional market Data that is served by a open endpoint that does not require authentication but
  that required the region identifier as an additional parameter

  @NIB10.01 @Market
  Scenario: [NIB10.01] When requesting an Advanced Component to a common market service the response matches the expected data.
    Given "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS45MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.E7gm6lbTZj07pFI-s5yz7InzAPtgYKM2VyZR5z_5j81KGJ4OJVFT_Z-_0n3TKPpz1wbL6TYwbQ07zSQE9hfGcg" authorization token
    Given a clean Credentials repository
    Given this list of Credentials stored at the repository
      | uniqueCredential     | accountId | accountName               | corporationId | dataSource  | accessToken                                                                            | refreshToken                                                                                                                                                                                     | scope                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | walletBalance | assetsCount | miningResourcesEstimatedValue |
      | tranquility.93813310 | 93813310  | Testing Character Account | 98384726      | tranquility | P940P9FpVhR8oq2V96D7pbcLzndNWTsAVgVAMt0HE5tJT15zg83MMqfsZhW1yf1XoFn9_IQJN5LrIa3NA90Ifw | 52HSB2sQiYBOrvaPidnxvnc-DIgT7DP5gUoCEOCW4v61dBfHOrCplfuwma0En0eZsLff2L6OJ6csIDTEQhqDmr0iVB6XmuNloTYhTT2Lx-x15j37Oo91jRrbHiC414DMX2nDPz-JGAdPLDtOzG2-4ofHR61rvw7sGY8Z1CnAgdGexAN6M4ZX93D_UWBEvlFd | publicData esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-mail.read_mail.v1 esi-skills.read_skills.v1 esi-skills.read_skillqueue.v1 esi-wallet.read_character_wallet.v1 esi-wallet.read_corporation_wallet.v1 esi-search.search_structures.v1 esi-clones.read_clones.v1 esi-universe.read_structures.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-fittings.read_fittings.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-clones.read_implants.v1 esi-wallet.read_corporation_wallets.v1 esi-characters.read_notifications.v1 esi-corporations.read_divisions.v1 esi-assets.read_corporation_assets.v1 esi-corporations.read_blueprints.v1 esi-contracts.read_corporation_contracts.v1 esi-industry.read_corporation_jobs.v1 esi-markets.read_corporation_orders.v1 esi-industry.read_character_mining.v1 esi-industry.read_corporation_mining.v1 | 0.0           | 0           | 0.0                           |
    When the Get Market Consolidated request with item type 11535
    Then there is a valid response with return code of "200 OK"
    And the resulting Market Data contents
      | typeId | sellDeep | sellAverage | marketWidth |
      | 11535  | 11679    | 39588.0     | 11000.0     |
    And the resulting Market Data has a Best Sell Order
    And the resulting Market Data Best Sell Order Station matches a Station with data
      | locationId | regionId | regionName | constellationId | constellationName | systemId | systemName | stationId | stationName                                |
      | 60008494   | 10000043 | Domain     | 20000322        | Throne Worlds     | 30002187 | Amarr      | 60008494  | Amarr VIII (Oris) - Emperor Family Academy |
    And the resulting Market Data Best Sell Order contains
      | typeId | price   | orderId    | volumeRemain | volumeTotal |
      | 11535  | 39000.0 | 5880091755 | 1366         | 1366        |
    And the resulting Market Data has a Best Buy Order
    And the resulting Market Data has 5 entries on the Sell Orders list

  @NIB10.02 @Market
  Scenario: [NIB10.02] Validate the market data returned when the open endpoint is called with a region and a type identifier.
    When the Get Universe Market Consolidated request with region identifier 10000043 and item type 11535
    Then there is a valid response with return code of "200 OK"
    And the resulting Market Data contents
      | typeId | sellDeep | sellAverage | marketWidth |
      | 11535  | 11679    | 39588.0     | 11000.0     |
    And the resulting Market Data has a Best Sell Order
    And the resulting Market Data Best Sell Order Station matches a Station with data
      | locationId | regionId | regionName | constellationId | constellationName | systemId | systemName | stationId | stationName                                |
      | 60008494   | 10000043 | Domain     | 20000322        | Throne Worlds     | 30002187 | Amarr      | 60008494  | Amarr VIII (Oris) - Emperor Family Academy |
    And the resulting Market Data Best Sell Order contains
      | typeId | price   | orderId    | volumeRemain | volumeTotal |
      | 11535  | 39000.0 | 5880091755 | 1366         | 1366        |
    And the resulting Market Data has a Best Buy Order
    And the resulting Market Data has 5 entries on the Sell Orders list
