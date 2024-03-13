# package.json
    "acc:NIF10": "cypress run --spec ./cypress/acceptance/NIF10-MiningOperations.feature",
   "serve:dev": "ng serve --proxy-config proxy.conf.json --port 5200 ",


## Removed packages
    "@ngx-translate/core": "^13.0.0",
    "wait-on": "5.0.0",

## Node Server dependencies
    "child-process": "1.0.2",
    "compression": "1.7.4",
    "config": "3.3.1",
   "express": "4.17.1",
    "express-http-proxy": "1.6.0",
    "fs": "0.0.1-security",
    "npm": "10.2.4",
    "path": "0.12.7",
    "request": "2.88.2",

## sass
    "sass": "^1.71.1",
    "sass-loader": "10.0.1",

## Development
   "@types/chai": "4.2.11",
    "@types/jasminewd2": "2.0.3",
    "chai": "4.2.0",
    "chromedriver": "81.0.0",
    "codelyzer": "6.0.0",
    "jasmine-spec-reporter": "4.2.1",
    "karma-coverage-istanbul-reporter": "2.1.0",
    "nyc": "15.0.1",
    "protractor": "7.0.0",
    "tslint": "6.1.0",


# tsconfig.json

this vas replaced


{
  "compilerOptions": {
    "target": "es5",
    "lib": [
      "es5",
      "dom"
    ],
    "types": [
      "cypress",
      "node"
    ]
  },
  "include": [
    "**/*.ts"
  ],
  "module": "commonjs",
  "moduleResolution": "node",
  "preserveValueImports": false
}


# angular.json
       "lint": {
          "builder": "@angular-devkit/build-angular:tslint",
          "options": {
            "tsConfig": [
              "tsconfig.app.json",
              "tsconfig.spec.json",
              "e2e/tsconfig.json"
            ],
            "exclude": [
              "**/node_modules/**"
            ]
          }
        },
        "e2e": {
          "builder": "@angular-devkit/build-angular:protractor",
          "options": {
            "protractorConfig": "e2e/protractor.conf.js",
            "devServerTarget": "NeoComInfinityFrontend:serve"
          },
          "configurations": {
            "production": {
              "devServerTarget": "NeoComInfinityFrontend:serve:production"
            }
          }
        }
