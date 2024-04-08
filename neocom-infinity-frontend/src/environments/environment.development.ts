// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    enrironmentName: 'development',
    production: false,
    showexceptions: true,
    copyright: '© 2019,2024 Dimensinfin Industries',
    appTitle: 'NeoCom Infinity',
    appName: require('../../package.json').name,
    appVersion: require('../../package.json').version + " dev",
    loginLinkData: {
        esi_host: "https://login.eveonline.com/v2/oauth/authorize/?",
        parameters: {
            response_type: "code",
            client_id: "365122836bbb4f6d875aa5548426abf1",
            state: "LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0="
        },
        callback_url: "http://localhost:5201/app/loginValidation",
        scope: "publicData esi-location.read_ship_type.v1 esi-wallet.read_character_wallet.v1 esi-search.search_structures.v1 esi-universe.read_structures.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-fittings.read_fittings.v1 esi-fittings.write_fittings.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-corporations.read_blueprints.v1 esi-industry.read_character_mining.v1 esi-characterstats.read.v1"
    },
    platform: 'Angular 13.4.0 - RxJs 7.5.0',
    appSignature: "S0000.0020.0001",

    esiData: 'https://esi.evetech.net/latest/',
    serverName: 'http://localhost:5272',

    mockStatus: true,
}

import 'zone.js/dist/zone';  // Included with Angular CLI.
import 'zone.js/dist/zone-error';  // Included with Angular CLI.
