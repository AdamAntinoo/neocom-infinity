@NIB02 @Corporation
Feature: [NIB02] NeoCom Infinity is bases on the cooperative and corporation access. Define the Corporation visible data sets.

    Corporation data will follow a HATEOAS design and the HAL use of links to connect all data that belongs to corporations.
    Corporations identifiers are obtained when a Pilot is registered.
    Corporations are persisted at the repository to refresh their data periodically.
    Corporations store a set of Fittings that are shared among the capsuleers.

    @NIB02.01 @Corporation
    Scenario: [NIB02.01] When authenticating a Pilot a Credential and a Corporation are persisted.
        Given a request to the "Validate Authorization Token" endpoint with the next data
            | code          | state                                                            | dataSource |
            | -not-applies- | LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0= |            |
        And the state field matches "LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0="
        When the Validate Authorization Token request is processed
        Then the response status code is 200
        And the Validate Authorization Token response contains a Credential
        And the current world Credential has the next values:
            | uniqueCredential     | accountId | accountName               | corporationId | dataSource  | accessToken                                                                            | refreshToken                                                                                                                                                                                     | scope                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | walletBalance | assetsCount | miningResourcesEstimatedValue |
            | tranquility.93813310 | 93813310  | Testing Character Account | 98384726      | tranquility | lfS7LIBbjLKnglJsujkNERgbwgOE0dCDiudhCdyrBxbxRp1xtFYzTRMxY2G2EssiS44UvvdMfRrXiLtn0SW9Zw | oCHpz8dm7MJNZ6PYqRvpWU6IkaD_Z5PsNx9SkI54UkvBY92yIUqEpIiFv03nxnLLnx-w_uTBBmsITYxM7WqzjUio4pXTJJN-GUGb-YNBfe0YNia_fl-NUNlmIGCwIMQCQhLpDZEUmECKUt7Do4T9ZW7FimJrhJUyw5xumUPN-d64oeY7Nd-UO4mc-By8i3aQ | publicData esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-mail.read_mail.v1 esi-skills.read_skills.v1 esi-skills.read_skillqueue.v1 esi-wallet.read_character_wallet.v1 esi-wallet.read_corporation_wallet.v1 esi-search.search_structures.v1 esi-clones.read_clones.v1 esi-universe.read_structures.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-fittings.read_fittings.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-clones.read_implants.v1 esi-wallet.read_corporation_wallets.v1 esi-characters.read_notifications.v1 esi-corporations.read_divisions.v1 esi-assets.read_corporation_assets.v1 esi-corporations.read_blueprints.v1 esi-contracts.read_corporation_contracts.v1 esi-industry.read_corporation_jobs.v1 esi-markets.read_corporation_orders.v1 esi-industry.read_character_mining.v1 esi-industry.read_corporation_mining.v1 | 0.0           | 0           | 0.0                           |


    @NIB02.02 @Corporation
    Scenario: [NIB02.02] The authorization token corporation identifier does not match the requested corporation.
        Given a request to the "Get Corporation Data" endpoint with the next data
            | corporationId |
            | 123456        |
        And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTM4MTMzMTAsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.g4VqcRxThHb9g0Ln24yix8zbu7kIplA4oIOuU5LM53v0pvEtrotWaBAF8zS9zELmm2_10QJhhdSdamSI9ntjdw" authorization token
        When the "Get Corporation Data" request is processed
        Then the response status code is 403
#    And the exception message is "The corporation requested is not authorized to the requester."

    @NIB02.03 @Corporation
    Scenario: [NIB02.03] The authorization matches and the response is a valid Corporation data block.
        Given a request to the "Get Corporation Data" endpoint with the next data
            | corporationId |
            | 93813310      |
        And "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTM4MTMzMTAsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.g4VqcRxThHb9g0Ln24yix8zbu7kIplA4oIOuU5LM53v0pvEtrotWaBAF8zS9zELmm2_10QJhhdSdamSI9ntjdw" authorization token
        When the "Get Corporation Data" request is processed
        Then the response status code is 200
#    And the exception message is "The corporation requested is not authorized to the requester."

    @NIB02.04 @Corporation
    Scenario: [NIB02.04] Given a set of few assets validate the resulting hierarchy with details deep checks. Use the simplest
    asset configuration that is a single asset inside a corporation container at the first office.
        Given a request to the "Get Corporation Assets By Location" endpoint with the next data
            | corporationId |
            | 98384726      |
        And authorization token contained in file "/Corporation/Authorization.Bearer.txt"
        When the "Get Corporation Assets By Location" request is processed
        Then the response status code is 200
        And the resulting list of locations has "2" elements
        And the resulting list has the next data
            | jsonClass              | containerType | contentsCount | region | constellation | system   | station                                               |
            | LocationAssetContainer | SPACE         | 1             | Domain | Liela         | Avair    | Avair VII - Moon 25 - Theology Council Tribunal       |
            | LocationAssetContainer | SPACE         | 1             | Devoid | Ryra          | Esescama | Esescama VIII - Moon 3 - Imperial Armaments Warehouse |
