# Change Management
## Version 
## Version v0.20
* Update the development ports and scripts to be used for operating the frontend application.

* Updated the project configuration to a most recent Angular version.
* Changed the java shell script to start the mock server by a docker container with the Api Simulator instance.
* The Pilot should belong to a Corporation. There are two types os corporations, the NPC game corporations that do not belong to
any Alliance and use created Corporations. I have to check that both have CEO. A Corporation may also belong to an Alliance. This
two dependencies can now be represented by HAL links instead of a huge set of downloaded data. This should simplify the data
management but requires a more complex client data processing to access data through links in an asynchronous way.
* Added a new servive to convert HATEOAS instances into typescript class instances.
* Reviewed the way to store the backend authentication token. Most of the application page access should be protected by a guardian
that should check for a valid Credential existence.
* Cypress acceptance tests reworked to the new step common use and the way to check for rendering along with data contents.
