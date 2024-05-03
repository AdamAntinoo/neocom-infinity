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
    "target": "http://host.docker.internal:32200",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/nin/v1/*": {
    "target": "http://localhost:3000",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/esi/v1/*": {
    "target": "http://localhost:3000",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v1/neocom/validateAuthenticationState": {
    "target": "http://localhost:6000",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v1/neocom/*": {
    "target": "http://localhost:6000",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v2/neocom/*": {
    "target": "http://host.docker.internal:32100",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v1/universe/*": {
    "target": "http://host.docker.internal:32100",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v2/universe/*": {
    "target": "http://host.docker.internal:32100",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/v1/public/*": {
    "target": "http://localhost:6000",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  },
  "/api/*": {
    "target": "http://host.docker.internal:32100",
    "secure": false,
    "logLevel": "debug",
    "logProvider": logProvider,
    "timeout": 30000,
    "changeOrigin": true
  }
}
module.exports = PROXY_CONF
