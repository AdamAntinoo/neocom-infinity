import * as cookieParser from 'cookie-parser'
import fs from 'fs'
import { NestFactory } from '@nestjs/core'
import { AppModule } from './app.module'
import { ConfigService } from '@nestjs/config'

function printData(configService: ConfigService<unknown, boolean>) {
	const START_YELLOW = '\x1b[33m\x1b[1m'
	const START_GREEN = '\x1b[32m\x1b[1m'
	const END_BOLD = '\x1b[0m'

	const port = configService.get<number>('PORT') || 3000
	const backendproxy = configService.get<string>('BACKEND_HOST') || 'http://esi-data-tst:5271'

	console.log('Nest JS Server version: ' + START_GREEN + '10.3.2' + END_BOLD)
	console.log('Running environment: ' + START_YELLOW + process.env.NODE_ENV + END_BOLD)
	console.log('Version: ' + START_YELLOW + process.env.VERSION + END_BOLD)
	console.log('Listening on port: ' + START_YELLOW + port + END_BOLD)
	console.log('Backend URL path: ' + START_GREEN + backendproxy + END_BOLD)
	console.log('Esi Universe URL path: ' + START_GREEN + configService.get<string>('ESI_UNIVERSE_HOST') + END_BOLD)
	console.log('Fuzz URL path: ' + START_GREEN + configService.get<string>('FUZZ_UNIVERSE_HOST') + END_BOLD)

}
async function bootstrap() {
	const app = await NestFactory.create(AppModule)
	app.use(cookieParser())
	const configService = app.get(ConfigService)
	const port = configService.get<number>('PORT') || 3000

	await app.listen(port)
	// TODO - Get back the printing for the banner
	// const filename = process.env.BANNER
	// if (undefined == filename) {
	printData(configService)
	console.log('Server Ready for Connections')
	// } else
	// 	fs.readFile(filename, 'utf8', function (err, data) {
	// 		if (err) throw err
	// 		printData(configService)
	// 		console.log(data)
	// 		console.log('Server Ready for Connections')
	// 	})
}
bootstrap()
