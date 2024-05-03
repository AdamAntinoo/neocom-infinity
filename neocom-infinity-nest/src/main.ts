// eslint-disable-next-line @typescript-eslint/no-var-requires
const fs = require('fs')
import * as cookieParser from 'cookie-parser'
import { NestFactory } from '@nestjs/core'
import { AppModule } from './app.module'
import { ConfigService } from '@nestjs/config'

const START_YELLOW = '\x1b[33m\x1b[1m'
const START_GREEN = '\x1b[32m\x1b[1m'
const START_RED = '\x1b[31m\x1b[1m'
const END_ENHANCED = '\x1b[0m'
function printData(configService: ConfigService<unknown, boolean>) {
	const port = configService.get<number>('PORT') || 3000
	const logLevel = process.env.LOGLEVEL

	console.log('Nest JS Server version: ' + START_GREEN + '10.3.2' + END_ENHANCED)
	console.log('Reading environment: ' + START_YELLOW + process.env.ENVIRONMENT + END_ENHANCED)
	console.log('Running environment: ' + START_YELLOW + process.env.NODE_ENV + END_ENHANCED)
	console.log('Version: ' + START_YELLOW + process.env.VERSION + END_ENHANCED)
	console.log('LogLevel: ' + START_RED + logLevel + END_ENHANCED)
	console.log('Listening on port: ' + START_YELLOW + port + END_ENHANCED)
	console.log('ESI Secure URL path: ' + START_GREEN + configService.get<string>('ESI_BACKEND_HOST') + END_ENHANCED)
	console.log('ESI Universe URL path: ' + START_GREEN + configService.get<string>('ESI_UNIVERSE_HOST') + END_ENHANCED)
	console.log('Fuzz URL path: ' + START_GREEN + configService.get<string>('FUZZ_UNIVERSE_HOST') + END_ENHANCED)
}
async function bootstrap() {
	let app = undefined
	// - configure logging depending on the environment
	let logLevel = process.env.LOGLEVEL
	if (undefined == logLevel) logLevel = 'debug'
	if (logLevel == 'debug')
		app = await NestFactory.create(AppModule, {
			logger: ['fatal', 'error', 'warn', 'log', 'debug', 'verbose'],
		})
	if (logLevel == 'info')
		app = await NestFactory.create(AppModule, {
			logger: ['fatal', 'error', 'warn', 'log'],
		})
	if (logLevel == 'log')
		app = await NestFactory.create(AppModule, {
			logger: ['fatal', 'error', 'warn', 'log'],
		})
	if (logLevel == 'prod')
		app = await NestFactory.create(AppModule, {
			logger: ['fatal', 'error', 'warn'],
		})
	app.use(cookieParser())
	const configService = app.get(ConfigService)
	const port: number = configService.get('PORT') || 3000

	await app.listen(port)
	const bannerFile: string = configService.get('BANNER')
	if (undefined == bannerFile) {
		console.log('BANNER NOT DEFINED.')
		printData(configService)
		console.log(START_GREEN + '>>> Server Ready for Connections <<<' + END_ENHANCED)
	} else {
		fs.readFile(bannerFile, 'utf8', function (err, data) {
			if (err) throw err
			printData(configService)
			console.log(data)
			console.log(START_GREEN + '>>> Server Ready for Connections <<<' + END_ENHANCED)
		})
	}
}
bootstrap()
