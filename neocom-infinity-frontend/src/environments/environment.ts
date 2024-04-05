// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    production: false,
    showexceptions: true,
    copyright: '© 2019,2024 Dimensinfin Industries',
    appTitle: 'NeoCom Infinity',
    appName: require('../../package.json').name,
    appVersion: require('../../package.json').version + " dev",
    serverName: '',
    loginLink: "https://login.eveonline.com/v2/oauth/authorize/?response_type=code&client_id=365122836bbb4f6d875aa5548426abf1&state=LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0=&redirect_uri=http%3A%2F%2Flocalhost%3A532000%2Fapp%2FloginValidation&scope=publicData%20esi-wallet.read_character_wallet.v1%20esi-industry.read_character_jobs.v1%20esi-industry.read_character_mining.v1",
    platform: 'Angular 8.2.3 - RxJs 6.4.0 - Rollbar 2.13',
    appSignature: "S0000.0020.0000",

    esiData : 'https://esi.evetech.net/latest/',

    mockStatus: true,
    LoginRequest: 'https://login.eveonline.com/v2/oauth/authorize/?response_type=code&client_id=365122836bbb4f6d875aa5548426abf1&state=LU5FT0NPTS5JTkZJTklUWS1ERVZFTE9QTUVOVC1WQUxJRCBTVEFURSBTVFJJTkct&redirect_uri=http%3A%2F%2Flocalhost:32000%2Fapp%2FloginValidation'
};

import 'zone.js/dist/zone';  // Included with Angular CLI.
import 'zone.js/dist/zone-error';  // Included with Angular CLI.
