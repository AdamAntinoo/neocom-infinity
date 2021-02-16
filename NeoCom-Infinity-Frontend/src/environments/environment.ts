// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    production: false,
    showexceptions: true,
    copyright: '© 2019,2021 Dimensinfin Industries',
    appTitle: 'NeoCom Infinity',
    appName: require('../../package.json').name,
    appVersion: require('../../package.json').version + " dev",
    apiVersion1: '/api/v1/neocom',
    apiVersion2: '/api/v2/neocom',
    esiData : 'https://esi.evetech.net/latest/',

    mockStatus: true,
    serverName: '',
    appSignature: "S000.016.001-20191023",
    platform: 'Angular 8.2.3 - RxJs 6.4.0 - Rollbar 2.13',
    ESIDataSource: 'Tranquility',
    LoginRequest: 'https://login.eveonline.com/v2/oauth/authorize/?response_type=code&client_id=eacaa9cd36594189877544d851753734&state=LU5FT0NPTS5JTkZJTklUWS1ERVZFTE9QTUVOVC1WQUxJRCBTVEFURSBTVFJJTkct&redirect_uri=http%3A%2F%2Fneocom.infinity.local%2Fapp%2FloginValidation'
};

import 'zone.js/dist/zone';  // Included with Angular CLI.
import 'zone.js/dist/zone-error';  // Included with Angular CLI.
