@NIB04 @IndustryFitting
Feature: [STORY][BACKEND] To start the industrial process to build a Fitting we need as starting point a new set of endpoints to manage Fitting
  Build Configurations.

  With a Fitting identifier we can get the list of links to the different actions possible or vies for that Fitting.
  The initial views available are the current Saved Fitting Configuration and the Target Fitting Configuration.
  Following the initial Saven Fitting Configuration we get a list of BUY actions required to complete the fitting by buting element on the nearest
  region market.

  Background:
    Given "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS45MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.E7gm6lbTZj07pFI-s5yz7InzAPtgYKM2VyZR5z_5j81KGJ4OJVFT_Z-_0n3TKPpz1wbL6TYwbQ07zSQE9hfGcg" authorization token
    Given a clean Credentials repository
    Given this list of Credentials stored at the repository
      | uniqueCredential     | accountId | accountName               | corporationId | dataSource  | accessToken                                                                            | refreshToken                                                                                                                                                                                     | scope                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | walletBalance | assetsCount | miningResourcesEstimatedValue |
      | tranquility.93813310 | 93813310  | Testing Character Account | 98384726      | tranquility | P940P9FpVhR8oq2V96D7pbcLzndNWTsAVgVAMt0HE5tJT15zg83MMqfsZhW1yf1XoFn9_IQJN5LrIa3NA90Ifw | 52HSB2sQiYBOrvaPidnxvnc-DIgT7DP5gUoCEOCW4v61dBfHOrCplfuwma0En0eZsLff2L6OJ6csIDTEQhqDmr0iVB6XmuNloTYhTT2Lx-x15j37Oo91jRrbHiC414DMX2nDPz-JGAdPLDtOzG2-4ofHR61rvw7sGY8Z1CnAgdGexAN6M4ZX93D_UWBEvlFd | publicData esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-mail.read_mail.v1 esi-skills.read_skills.v1 esi-skills.read_skillqueue.v1 esi-wallet.read_character_wallet.v1 esi-wallet.read_corporation_wallet.v1 esi-search.search_structures.v1 esi-clones.read_clones.v1 esi-universe.read_structures.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-fittings.read_fittings.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-clones.read_implants.v1 esi-wallet.read_corporation_wallets.v1 esi-characters.read_notifications.v1 esi-corporations.read_divisions.v1 esi-assets.read_corporation_assets.v1 esi-corporations.read_blueprints.v1 esi-contracts.read_corporation_contracts.v1 esi-industry.read_corporation_jobs.v1 esi-markets.read_corporation_orders.v1 esi-industry.read_character_mining.v1 esi-industry.read_corporation_mining.v1 | 0.0           | 0           | 0.0                           |

  @NIB04.01 @IndustryFitting
  Scenario: [NIB04.01] Get the list of configurations available for a selected fitting.
    When the Get Fitting Configurations request with fitting identifier 60320161 is processed
    Then there is a valid response with return code of "200 OK"
    And the resulting Fitting Configurations contents
      | savedBuildData                                                                                       | targetBuildData                                                                                       |
      | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/savedConfiguration | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/targetConfiguration |

  @NIB04.02 @IndustryFitting
  Scenario: [NIB04.02] Request the saved fitting configuration and check the list of returned values.
    When the Get Fitting Saved Configuration request with fitting identifier 60320161 is processed
    Then there is a valid response with return code of "200 OK"
    And the resulting Fitting Build Configuration data has the next contents
      | fittingBuildId       | fittingInfo | contents | contents.size |
      | FB.93813310-60320161 | <exists>    | <exists> | 7             |
    And the resulting Fitting Info data has the next contents
      | fitting  | hull     | hullAction |
      | <exists> | <exists> | <exists>   |


#    And a target Fitting V2 with contents
#      | savedBuildData                                                                                       | targetBuildData                                                                                       |
#      | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/savedConfiguration | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/targetConfiguration |
#    And a target Fitting V2 with contents
#      | savedBuildData                                                                                       | targetBuildData                                                                                       |
#      | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/savedConfiguration | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/targetConfiguration |
#    And a target EsiItem with contents
#      | savedBuildData                                                                                       | targetBuildData                                                                                       |
#      | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/savedConfiguration | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/targetConfiguration |
#    And a target BuildAction of type "BUY" with contents
#      | savedBuildData                                                                                       | targetBuildData                                                                                       |
#      | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/savedConfiguration | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/targetConfiguration |
#
#    And the resulting Fitting Build Content with id "" data has the next contents
#      | savedBuildData                                                                                       | targetBuildData                                                                                       |
#      | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/savedConfiguration | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/targetConfiguration |
#    And a target FittingItem with contents
#      | savedBuildData                                                                                       | targetBuildData                                                                                       |
#      | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/savedConfiguration | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/targetConfiguration |
#    And a target BuildAction of type "BUY" with contents
#      | savedBuildData                                                                                       | targetBuildData                                                                                       |
#      | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/savedConfiguration | http://localhost:5240/api/v1/neocom/industry/fittings/buildConfiguration/60320161/targetConfiguration |
