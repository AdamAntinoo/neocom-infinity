# Build library
To publish a new library version execute the following commands:
* Update the version number on the package.json
* npm run build
* npm publish

# Command to search for circular dependencies or invalid public-api imports
./node_modules/madge/bin/cli.js --circular ./src/**

## Changelog
* [0.0.1] Created the Global Constants to be used to define cookies.
