# PROJECT NEOCOM-INFINITY NOTES
## Project Status
## Project Considerations
Unit Testing should be mandatory and should not depend on active service mocks. Data access should be implemented on the unit tests and no wiremocks or other services should be required to be available.

On special cases the tests should access data sources. On such cases there should be classified under E2E tests and for running those tests there should be a command to start/stop data provider services depending on the application under test.

## Configurations
There are some soures for configurations.
* The main environment properties file that is controlled by Angular and accesible to the application as a global set of properties. This is done with file replacement on the *angular.json* environment configuration.
* Node server configuration. This is only required for the main frontend application node server and this is controlled by the *config* dependency with files stored on the */config* directory.

### Development Configuration setup
To run the fonrtend application while developing there should be some data sources active.
