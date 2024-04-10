@NIB03 @Character
Feature: [NIB03] This feature set includes the new endpoints under the /pilot.

  [STORY] There is an endpoint to get the public data available for a Pilot.
  [STORY] There is another authenticated endpoint to get the Pilot information.

  Background:
    Given "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS45MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.E7gm6lbTZj07pFI-s5yz7InzAPtgYKM2VyZR5z_5j81KGJ4OJVFT_Z-_0n3TKPpz1wbL6TYwbQ07zSQE9hfGcg" authorization token
    Given the next list of cookies
      | name            | payload                                                                                                                                                                                                                                                                                                                                                                                |
      | NEOCOM-INFINITY | eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS45MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.E7gm6lbTZj07pFI-s5yz7InzAPtgYKM2VyZR5z_5j81KGJ4OJVFT_Z-_0n3TKPpz1wbL6TYwbQ07zSQE9hfGcg |
    Given a clean Credentials repository
    Given this list of Credentials stored at the repository
      | uniqueCredential     | accountId | accountName  | corporationId | dataSource  | accessToken                                                                            | refreshToken                                                                                                                                                                                     | scope                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | walletBalance | assetsCount | miningResourcesEstimatedValue |
      | tranquility.93813310 | 93813310  | Adam Antinoo | 98384726      | tranquility | lfS7LIBbjLKnglJsujkNERgbwgOE0dCDiudhCdyrBxbxRp1xtFYzTRMxY2G2EssiS44UvvdMfRrXiLtn0SW9Zw | oCHpz8dm7MJNZ6PYqRvpWU6IkaD_Z5PsNx9SkI54UkvBY92yIUqEpIiFv03nxnLLnx-w_uTBBmsITYxM7WqzjUio4pXTJJN-GUGb-YNBfe0YNia_fl-NUNlmIGCwIMQCQhLpDZEUmECKUt7Do4T9ZW7FimJrhJUyw5xumUPN-d64oeY7Nd-UO4mc-By8i3aQ | publicData esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-mail.read_mail.v1 esi-skills.read_skills.v1 esi-skills.read_skillqueue.v1 esi-wallet.read_character_wallet.v1 esi-wallet.read_corporation_wallet.v1 esi-search.search_structures.v1 esi-clones.read_clones.v1 esi-universe.read_structures.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-fittings.read_fittings.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-clones.read_implants.v1 esi-wallet.read_corporation_wallets.v1 esi-characters.read_notifications.v1 esi-corporations.read_divisions.v1 esi-assets.read_corporation_assets.v1 esi-corporations.read_blueprints.v1 esi-contracts.read_corporation_contracts.v1 esi-industry.read_corporation_jobs.v1 esi-markets.read_corporation_orders.v1 esi-industry.read_character_mining.v1 esi-industry.read_corporation_mining.v1 | 0.0           | 0           | 0.0                           |

  @NIB03.01 @Character
  Scenario: [NIB03.01] Search for the requested Pilot and return a well formed Pilot data response.
    When the Get Pilot Data request with pilot 93813310
    Then there is a valid response with return code of "200 OK"
    And the resulting PilotV1 has the next contents
      | pilotId  | corporationId | name        | birthday                 | gender | securityStatus | totalSkillpoints | walletBalance   | currentShipName       | currentShipTypeName |
      | 93813310 | 98384726      | Beth Ripley | 2012-07-05T21:53:15.000Z | female | 0.0            | 8404419          | 2.76586637596E9 | Zach Zender's Velator | Velator             |

#  @NIB03.03 @Character
#  Scenario: [NIB03.03] Get the list of fittings for the authenticated pilot. Endpoint version 1.
#    Given a request to the "Get Pilot Fittings" endpoint with the next data
#      | pilotId  |
#      | 93813310 |
#    And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.eJvBC2144s7sKv5rxSUVEjNbP2BpQJlJhmTOu4AJ9eJj9so_WcrAthbvwgYM4BqyBSNZAjw7bVegieWqx8IX8Q" authorization token
#    When the "Get Pilot Fittings" request is processed
#    Then the response status code is "OK"
#    And the response has 9 fittings
#    And the first fitting has the next data
#      | fittingId | name          | hullClass |
#      | 60320161  | VM Clearer A1 | Frigate   |
