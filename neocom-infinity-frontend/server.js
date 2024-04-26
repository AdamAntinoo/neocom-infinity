// - D E P E N D E N C I E S
const express = require('express')
const path = require('path')
const compression = require('compression')
const config = require('config')
const proxy = require('express-http-proxy')
const fs = require('fs')
const { promisify } = require('util')
const exec = promisify(require('child_process').exec)

// - S E R V E R   O P T I O N S
const app = express()
app.use(compression())
var options = {
  dotfiles: 'ignore',
  etag: true,
  extensions: ['htm', 'html'],
  index: false,
  maxAge: '1d',
  lastModified: true,
  redirect: false,
  setHeaders: function (res, path, stat) {
    res.set('x-timestamp', Date.now())
    res.set('x-app-name', 'NeoCom.Infinity.Frontend')
    res.set('Access-Control-Allow-Origin', '*')
    res.set('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept')
    res.set('Access-Control-Allow-Methods', 'OPTIONS, GET, POST, PUT, DELETE')
  }
}

// - S E R V E R   C O N F I G U R A T I O N
app.locals.appname = config.get('settings.appname')
app.locals.port = process.env.PORT || config.get('settings.port')
app.locals.applicationhome = config.get('settings.applicationhome')
app.locals.backendproxy = config.get('settings.backendproxy')
app.locals.nestproxy = config.get('settings.nestproxy')
app.locals.apppath = config.get('settings.apppath')

// - L O G G I N G
app.use(function (req, res, next) {
  res.header('Access-Control-Allow-Origin', '*')
  res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept')
  res.header('Access-Control-Allow-Methods', 'OPTIONS, GET, POST, PUT, DELETE')
  if ('OPTIONS' === req.method) {
    res.sendStatus(200)
  } else {
    console.log(req.method + " " + req.url)
    next()
  }
})

// - H O M E   P A G E
app.get('/', function (req, res) {
  console.log('App: ' + __dirname + app.locals.applicationhome + req.url)
  res.sendFile(path.join(__dirname + app.locals.applicationhome + '/index.html'))
})

// - S T A T I C
app.use(express.static(__dirname + app.locals.apppath, options))
app.get('*.*', function (req, res) {
  console.log("Static: " + __dirname + app.locals.applicationhome + req.url)
  res.status(200).sendFile(path.join(__dirname + app.locals.applicationhome + req.url))
})

// - A C T U A T O R   M O U N T P O I N T
app.use('/actuator', proxy(app.locals.backendproxy, {
  proxyReqPathResolver: function (req) {
    console.log('Backend: ' + app.locals.backendproxy + 'actuator' + req.url)
    return app.locals.backendproxy + 'actuator' + req.url
  }
}))

// - A P I   M O U N T P O I N T
app.use('/api/v3', proxy(app.locals.nestproxy, {
  proxyReqPathResolver: function (req) {
    console.log('Nestend: ' + app.locals.nestproxy + 'api/v3' + req.url)
    return app.locals.nestproxy + 'api/v3' + req.url
  }
}))
app.use('/esi/v1', proxy(app.locals.nestproxy, {
  proxyReqPathResolver: function (req) {
    console.log('Nestend: ' + app.locals.nestproxy + 'esi/v1' + req.url)
    return app.locals.nestproxy + 'esi/v1' + req.url
  }
}))
app.use('/api', proxy(app.locals.backendproxy, {
  proxyReqPathResolver: function (req) {
    console.log('Backend: ' + app.locals.backendproxy + 'api' + req.url)
    return app.locals.backendproxy + 'api' + req.url
  }
}))

// - A P P   M O U N T P O I N T
app.get('/app/*', function (req, res) {
  console.log('App: ' + __dirname + app.locals.applicationhome + req.url)
  res.sendFile(path.join(__dirname + app.locals.applicationhome + '/index.html'))
})

const START_YELLOW = "\x1b[33m\x1b[1m"
const START_GREEN = "\x1b[32m\x1b[1m"
const END_BOLD = "\x1b[0m"
// - L I S T E N
app.listen(process.env.PORT || app.locals.port || 3000, function () {
  exec('echo `gitversion /showvariable MajorMinorPatch`-`gitversion /showvariable CommitsSinceVersionSource`'
    , (err, stdout, stderr) => {
      console.log("Node Express server version: v20.11.1")
      console.log("Running environment: " + START_YELLOW + process.env.NODE_ENV + END_BOLD)
      console.log("Current build: " + START_YELLOW + stdout.replace("\n", "") + END_BOLD)
      console.log("Listening on port: " + START_YELLOW + app.locals.port + END_BOLD)
      console.log("Serving application: " + START_YELLOW + app.locals.appname + END_BOLD)
      console.log("Application URL path: " + START_GREEN + "http://localhost:" + app.locals.port + '/' + END_BOLD)
      // console.log("Backend URL path: " + START_GREEN + app.locals.backendproxy + END_BOLD)
      console.log("Proxy redirection for " + START_YELLOW + "/actuator" + END_BOLD + ": " +
        'Backend: ' + START_GREEN + app.locals.backendproxy + 'actuator' + END_BOLD)
      console.log("Proxy redirection for " + START_YELLOW + "/api/vn" + END_BOLD + ": " +
        'Backend: ' + START_GREEN + app.locals.backendproxy + 'api' + END_BOLD)
      console.log("Proxy redirection for " + START_YELLOW + "/api/v3" + END_BOLD + ": " +
        'Backend: ' + START_GREEN + app.locals.nestproxy + 'api/v3' + END_BOLD)
      console.log("Proxy redirection for " + START_YELLOW + "/esi/v1" + END_BOLD + ": " +
        'Backend: ' + START_GREEN + app.locals.nestproxy + 'esi/v1' + END_BOLD)
      const filename = 'app-banner.txt'

      console.log('>>> Additional properties:')
      console.log('>Env>Login Link: ' + process.env.LOGIN_LINK)
      console.log('>Env>Port: ' + process.env.PORT)
      console.log('>Env>Image Name: ' + process.env.IMAGE_NAME)
      console.log('>Env>Semantic Version: ' + process.env.SEMVER)
      console.log('>Env>Tag Version: ' + process.env.TAGVERSION)

      fs.readFile(filename, 'utf8', function (err, data) {
        if (err) throw err
        console.log(data)
        console.log(START_GREEN + ">>> Server Ready for Connections <<<" + END_BOLD)
      })
    })
})
