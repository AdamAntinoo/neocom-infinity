# Build library
To publish a new library version execute the following commands:
* Update the version number on the package.json
* npm run build
* npm publish

# Command to search for circular dependencies or invalid public-api imports
./node_modules/madge/bin/cli.js --circular ./src/**

## Changelog
* [0.6.0] Add blueprint and related classes. Moved constants and link generators to this public library.
* [0.4.3] Add additional interfaces to the model
* [0.4.2] Make DTO fields publis and remove the need for methods or builders unless required to construct them. Better use Constructors.

## ToDo
Unit tests for:
* LocationLink
* TypeLink
