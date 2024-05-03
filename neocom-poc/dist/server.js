const express = require("express")
const config = require("config")

const app = express()

// - S E R V E R   C O N F I G U R A T I O N
app.locals.appname = config.get("globals.appname")
app.locals.port = process.env.PORT || config.get("settings.port") || 5000
app.locals.publicproxy = process.env.PUBLICPROXY
app.locals.oauthproxy = process.env.OAUTHPROXY
app.locals.backendproxy = process.env.BACKENDPROXY
app.locals.nestproxy = process.env.NESTPROXY
app.locals.nodeenv = process.env.NODE_ENV || 'development'

console.log('Environment->' + JSON.stringify(process.env))

console.log('AppName->' + app.locals.appname)
console.log('Data->' + config.get("data.envvalue"))
console.log('PublicProxy->' + app.locals.publicproxy)
console.log('NODE_ENV->' + process.env.NODE_ENV)
console.log('Stage->' + app.locals.nodeenv)
console.log('Port->' + config.get("settings.port") || 5100)
console.log('Local Port->' + app.locals.port)
