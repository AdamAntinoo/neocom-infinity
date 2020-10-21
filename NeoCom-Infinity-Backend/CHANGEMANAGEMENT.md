# CHANGE MANAGEMENT
## v0.20.0
* Upgraded the mock services to not be started from a console script but to be dockerized.
* Change database configuration. Use an external environment variable instead property configuration 
that should be changed and configured for each environment on a set of different files.
* Use HATEOAS to reduce the processing time and make the responses more usable. If the consumer
needs to access more data it can follow the link to the service. If not used the link is not activated
and many HTTP access can be saved.
