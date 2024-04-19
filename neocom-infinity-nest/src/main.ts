import * as cookieParser from 'cookie-parser'
const fs = require('fs')
import { NestFactory } from '@nestjs/core'
import { AppModule } from './app.module'
import { ConfigService } from '@nestjs/config'

async function bootstrap() {
    const START_YELLOW = "\x1b[33m\x1b[1m"
    const START_GREEN = "\x1b[32m\x1b[1m"
    const END_BOLD = "\x1b[0m"

    const app = await NestFactory.create(AppModule)
    app.use(cookieParser())
    const configService = app.get(ConfigService)
    const port = configService.get<number>('PORT') || 3000
    const backendproxy = configService.get<string>('BACKEND_HOST') || 'http://esi-data-tst:5271'

    await app.listen(port)
    const filename = process.env.BANNER
    fs.readFile(filename, 'utf8', function (err, data) {
        if (err) throw err
        console.log("Nest JS Server version: " + START_GREEN + "10.3.2" + END_BOLD)
        console.log("Running environment: " + START_YELLOW + process.env.NODE_ENV + END_BOLD)
        console.log("Version: " + START_YELLOW + process.env.VERSION + END_BOLD)
        console.log("Listening on port: " + START_YELLOW + port + END_BOLD)
        console.log("Backend URL path: " + START_GREEN + backendproxy + END_BOLD)
        console.log("Esi Universe URL path: " + START_GREEN + configService.get<string>('ESI_UNIVERSE_HOST') + END_BOLD)
        console.log("Fuzz URL path: " + START_GREEN + configService.get<string>('FUZZ_UNIVERSE_HOST') + END_BOLD)

        console.log(data)
        console.log("Server Ready for Connections")
    })
}
bootstrap()
