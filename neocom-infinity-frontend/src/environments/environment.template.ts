export const environment = {
    environment: '$ENVIRONMENT',
    production: '$PRODUCTION',
    showexceptions: true,
    copyright: '© 2019,2024 Dimensinfin Industries',
    appTitle: 'NeoCom Infinity',
    appName: require('../../package.json').name,
    appVersion: '$VERSION',
    loginLinkData: {
        esi_host: "$OAUTHPROXY/v2/oauth/authorize/?",
        parameters: {
            response_type: "code",
            client_id: "$ESI_CLIENTID",
            state: "$STATE"
        },
        callback_url: "$CALLBACK_URL",
        scope: "publicData esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-skills.read_skills.v1 esi-skills.read_skillqueue.v1 esi-wallet.read_character_wallet.v1 esi-wallet.read_corporation_wallet.v1 esi-search.search_structures.v1 esi-clones.read_clones.v1 esi-universe.read_structures.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-fittings.read_fittings.v1 esi-fittings.write_fittings.v1 esi-markets.structure_markets.v1 esi-corporations.read_structures.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-wallet.read_corporation_wallets.v1 esi-assets.read_corporation_assets.v1 esi-corporations.read_blueprints.v1 esi-industry.read_corporation_jobs.v1 esi-industry.read_character_mining.v1 esi-characterstats.read.v1"
    },
    platform: 'Angular 13.4.0 - RxJs 7.5.0',
    appSignature: "S0000.0020.0001",

    esiData: '$ESIPROXY'
}
