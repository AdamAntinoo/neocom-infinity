# FEATURE DEVELOPMENT

## How to start a new Page
The first task is to create the requirement test on the Cypress framework. Use the sample on section 02.
The next task is to create the Angular components. The templates for that should be reviewed to a new nomanclature.

During the construction of the components, some of them should be updated to represent the class dependencies that allow Renders to behave as renders and Panels to behave as panels.
This dependencies should also be reviewed and automated if possible so a new class will promtp for the missing required methods and fields.

## Ading backend endpoints
To create a new endpoint first we should locate the correct Adapter. Adapters are of new adoption so only available for new endpoints.

* Add the new endpoint to the SecuredServicesPort Port.
* Create the stub on the Port implementation.

* Create the neocom node clases that are going to be returned by this endpoint

The second task is to create the new page on the Angular framework.
