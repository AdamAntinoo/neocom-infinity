export const environment = {
    production: true,
    showexceptions: true,
    copyright: '© 2019,2024 Dimensinfin Industries',
    appTitle: 'NeoCom Infinity',
    appName: require('../../package.json').name,
    appVersion: require('../../package.json').version + " dev",
    serverName: '',
    loginLink: "https://login.eveonline.com/v2/oauth/authorize/?response_type=code&client_id=98eb8d31c5d24649ba4f7eb015596fbd&state=LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0=&redirect_uri=https%3A%2F%2Fhttp://localhost:32000/%2Fapp%2FloginValidation&scope=publicData%20esi-location.read_location.v1%20esi-location.read_ship_type.v1%20esi-skills.read_skills.v1%20esi-skills.read_skillqueue.v1%20esi-wallet.read_character_wallet.v1%20esi-search.search_structures.v1%20esi-universe.read_structures.v1%20esi-location.read_online.v1",
    loginLinkData: {
        host: "https://login.eveonline.com/v2/oauth/authorize/",
        parameters: "?response_type=code&client_id=98eb8d31c5d24649ba4f7eb015596fbd&state=LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0=",
        redirect_uri: {
            callback_host: "neocom-frontend-dev.herokuapp.com",
            callback_location: "%2Fapp%2FloginValidation"
        },
        scope: "publicData%20esi-location.read_location.v1%20esi-location.read_ship_type.v1%20esi-skills.read_skills.v1%20esi-skills.read_skillqueue.v1%20esi-wallet.read_character_wallet.v1%20esi-search.search_structures.v1%20esi-universe.read_structures.v1%20esi-location.read_online.v1"
    },
    platform: 'Angular 8.2.3 - RxJs 6.4.0 - Rollbar 2.13',
    appSignature: "S0000.0020.0000",

    esiData: 'https://esi.evetech.net/latest/',
    LoginRequest: 'https://login.eveonline.com/v2/oauth/authorize/?response_type=code&client_id=eacaa9cd36594189877544d851753734&state=LU5FT0NPTS5JTkZJTklUWS1ERVZFTE9QTUVOVC1WQUxJRCBTVEFURSBTVFJJTkct&redirect_uri=http%3A%2F%2Fneocom.infinity.local%2Fapp%2FloginValidation'
};
