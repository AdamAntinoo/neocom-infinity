@NIB01 @Authorization
Feature: [NIB01] Validate the authorization token from ESI OAuth2 service and other authorization related tasks.

  Check that we arrive to this endpoint after requesting an authorization token from ESI OAuth2 authentication service.
  Once we arrive to this endpoint we should check that the source is the expected source identifier and that we
  can create a Credential from the token received by exchanging that token by a refresh token.
  There is another endpoint to store already valid credentials and returning a valid JWT token to be used later to get access
  to the characters data.

  @NIB01.01 @Authorization
  Scenario: [NIB01.01] Detect the correct state at the endpoint.
    Given a request to the "Validate Authorization Token" endpoint with the next data
      | code          | state                                                            | dataSource |
      | -not-applies- | LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0= |            |
    And the state field matches "LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0="
    When the "Validate Authorization Token" request is processed
    Then the response status code is 200
    And the "Validate Authorization Token" response contains a valid Credential

  @NIB01.02 @Authorization
  Scenario: [NIB01.02] Validate that the JWT token has the correct contents.
    Given a request to the "Validate Authorization Token" endpoint with the next data
      | code         | state                                                            | dataSource  |
      | -VALID-CODE- | LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0= | tranquility |
    When the "Validate Authorization Token" request is processed
    Then the JWT generated token has the next contents
      | sub                       | corporationId | accountName               | iss                     | uniqueId             | pilotId  |
      | ESI OAuth2 Authentication | 98384726      | Testing Character Account | NeoCom.Infinity.Backend | tranquility.93813310 | 93813310 |

  @NIB01.03 @Authorization
  Scenario: [NIB01.03] Validate and store a new external Credential at the repository.
    Given the next Credential data
      | uniqueCredential     | accountId | accountName  | corporationId | dataSource  | accessToken                                                                            | refreshToken                                                                                                                                                                                     | scope                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | walletBalance | assetsCount | miningResourcesEstimatedValue |
      | tranquility.92002067 | 92002067  | Adam Antinoo | 92002067      | tranquility | lfS7LIBbjLKnglJsujkNERgbwgOE0dCDiudhCdyrBxbxRp1xtFYzTRMxY2G2EssiS44UvvdMfRrXiLtn0SW9Zw | oCHpz8dm7MJNZ6PYqRvpWU6IkaD_Z5PsNx9SkI54UkvBY92yIUqEpIiFv03nxnLLnx-w_uTBBmsITYxM7WqzjUio4pXTJJN-GUGb-YNBfe0YNia_fl-NUNlmIGCwIMQCQhLpDZEUmECKUt7Do4T9ZW7FimJrhJUyw5xumUPN-d64oeY7Nd-UO4mc-By8i3aQ | publicData esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-mail.read_mail.v1 esi-skills.read_skills.v1 esi-skills.read_skillqueue.v1 esi-wallet.read_character_wallet.v1 esi-wallet.read_corporation_wallet.v1 esi-search.search_structures.v1 esi-clones.read_clones.v1 esi-universe.read_structures.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-fittings.read_fittings.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-clones.read_implants.v1 esi-wallet.read_corporation_wallets.v1 esi-characters.read_notifications.v1 esi-corporations.read_divisions.v1 esi-assets.read_corporation_assets.v1 esi-corporations.read_blueprints.v1 esi-contracts.read_corporation_contracts.v1 esi-industry.read_corporation_jobs.v1 esi-markets.read_corporation_orders.v1 esi-industry.read_character_mining.v1 esi-industry.read_corporation_mining.v1 | 0.0           | 0           | 0.0                           |
    When the "Store Credential" request is processed
    Then the response status code is 200
    And the JWT generated token has the next contents
      | sub                       | corporationId | accountName  | iss                     | uniqueId             | pilotId  |
      | ESI OAuth2 Authentication | 92002067      | Adam Antinoo | NeoCom.Infinity.Backend | tranquility.92002067 | 92002067 |
    And there is a Credential record with id "tranquility.92002067" at the repository

  @NIB01.04 @Authorization
  Scenario: [NIB01.04] Validate that an existing Credential is updated with the new data.
    Given the next Credential data
      | uniqueCredential     | accountId | accountName             | corporationId | dataSource  | accessToken                                                                            | refreshToken                                                                                                                                                                                     | scope                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | walletBalance | assetsCount | miningResourcesEstimatedValue |
      | tranquility.92002067 | 92002067  | Adam Antinoo Remastered | 98384726      | tranquility | lfS7LIBbjLKnglJsujkNERgbwgOE0dCDiudhCdyrBxbxRp1xtFYzTRMxY2G2EssiS44UvvdMfRrXiLtn0SW9Zw | oCHpz8dm7MJNZ6PYqRvpWU6IkaD_Z5PsNx9SkI54UkvBY92yIUqEpIiFv03nxnLLnx-w_uTBBmsITYxM7WqzjUio4pXTJJN-GUGb-YNBfe0YNia_fl-NUNlmIGCwIMQCQhLpDZEUmECKUt7Do4T9ZW7FimJrhJUyw5xumUPN-d64oeY7Nd-UO4mc-By8i3aQ | publicData esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-mail.read_mail.v1 esi-skills.read_skills.v1 esi-skills.read_skillqueue.v1 esi-wallet.read_character_wallet.v1 esi-wallet.read_corporation_wallet.v1 esi-search.search_structures.v1 esi-clones.read_clones.v1 esi-universe.read_structures.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-fittings.read_fittings.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-clones.read_implants.v1 esi-wallet.read_corporation_wallets.v1 esi-characters.read_notifications.v1 esi-corporations.read_divisions.v1 esi-assets.read_corporation_assets.v1 esi-corporations.read_blueprints.v1 esi-contracts.read_corporation_contracts.v1 esi-industry.read_corporation_jobs.v1 esi-markets.read_corporation_orders.v1 esi-industry.read_character_mining.v1 esi-industry.read_corporation_mining.v1 | 0.0           | 0           | 0.0                           |
    When the "Store Credential" request is processed
    Then the response status code is 200
    And the JWT generated token has the next contents
      | sub                       | corporationId | accountName             | iss                     | uniqueId             | pilotId  |
      | ESI OAuth2 Authentication | 98384726      | Adam Antinoo Remastered | NeoCom.Infinity.Backend | tranquility.92002067 | 92002067 |
    And there is a Credential record with id "tranquility.92002067" at the repository
