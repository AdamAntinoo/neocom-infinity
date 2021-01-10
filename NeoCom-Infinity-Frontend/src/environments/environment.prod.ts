export const environment = {
    production: true,
    mockStatus: false,
    showexceptions: false,
    serverName: "https://neocom-backend.herokuapp.com",
    copyright: '© 2019,2021 Dimensinfin Industries',
    appName: require('../../package.json').name,
    appVersion: require('../../package.json').version,
    apiVersion1: '/api/v1/neocom',
    apiVersion2: '/api/v2/neocom',
    esiData : 'https://esi.evetech.net/latest/',

    appSignature: "S000.016.001-20191023",
    platform: 'Angular 8.2.3 - RxJs 6.4.0 - Rollbar 2.13',
    ESIDataSource: 'Tranquility',
    LoginRequest: 'https://login.eveonline.com/v2/oauth/authorize/?response_type=code&client_id=eacaa9cd36594189877544d851753734&state=LU5FT0NPTS5JTkZJTklUWS1ERVZFTE9QTUVOVC1WQUxJRCBTVEFURSBTVFJJTkct&redirect_uri=http%3A%2F%2Fneocom.infinity.local%2Fapp%2FloginValidation',
    // - C O N S T A N T S
    CREDENTIAL_KEY: '-CREDENTIAL-KEY-',
    VALID_STATE: 'LU5FT0NPTS5JTkZJTklUWS1ERVZFTE9QTUVOVC1WQUxJRCBTVEFURSBTVFJJTkct'
};
