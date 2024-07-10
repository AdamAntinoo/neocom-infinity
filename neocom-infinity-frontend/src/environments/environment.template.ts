export const environment = {
    environment: '$ENVIRONMENT',
    production: '$PRODUCTION',
    showexceptions: true,
    copyright: '© 2019,2024 Dimensinfin Industries',
    appTitle: 'NeoCom Infinity',
    appName: require('../../package.json').name,
    appVersion: '$VERSION',
    loginLinkData: {
        esi_host: "$OAUTHPROXY/$ESI_AUTHORIZATION_URL_PREFIX/authorize/?",
        parameters: {
            response_type: "code",
            client_id: "$ESI_CLIENT_ID",
            state: "$ESI_STATE_BASE64"
        },
        callback_url: "$ESI_CALLBACK_URL",
        scope: "$ESI_SCOPE"
    },
    platform: 'Angular 13.4.0 - RxJs 7.5.0',
    appSignature: "S0000.0029.0001",

    esiData: '$ESIPROXY'
}
