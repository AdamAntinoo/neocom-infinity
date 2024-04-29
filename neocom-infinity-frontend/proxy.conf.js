var winston = require('winston') //(1)

function logProvider() { //(2)
  return winston.createLogger({
    level: 'debug',
    format: winston.format.combine(
      winston.format.splat(),
      winston.format.simple()
    ),
    transports: [new winston.transports.Console()],
  })
}
var PROXY_CONF = {
  "/api/v3/neocom/*": {
    "target": "http://localhost:5235",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/nin/v1/*": {
    "target": "http://localhost:5235",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/esi/v1/*": {
    "target": "http://localhost:5235",
    "secure": false,
    "logLevel": "debug",
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v1/neocom/validateAuthenticationState": {
    "target": "http://localhost:5235",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v1/neocom/*": {
    "target": "http://localhost:5225",
    "secure": false,
    "logLevel": "debug",
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v2/neocom/*": {
    "target": "http://localhost:5225",
    "secure": false,
    "logLevel": "debug",
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v1/universe/*": {
    "target": "http://localhost:5215",
    "secure": false,
    "logLevel": "debug",
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v2/universe/*": {
    "target": "http://localhost:5215",
    "secure": false,
    "logLevel": "debug",
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v1/public/*": {
    "target": "http://localhost:5215",
    "secure": false,
    "logLevel": "debug",
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/*": {
    "target": "http://localhost:5205",
    "secure": false,
    "logLevel": "debug",
    "timeout": 30000,
    "changeOrigin": true
  }
}
module.exports = PROXY_CONF
