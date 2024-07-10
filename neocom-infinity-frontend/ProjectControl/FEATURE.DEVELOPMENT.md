# FEATURE DEVELOPMENT

## How to start a new Page
The first task is to create the requirement test on the Cypress framework. Use the sample on section 02.
The next task is to create the Angular components. The templates for that should be reviewed to a new nomanclature.

During the construction of the components, some of them should be updated to represent the class dependencies that allow Renders to behave as renders and Panels to behave as panels.
This dependencies should also be reviewed and automated if possible so a new class will promtp for the missing required methods and fields.

## Adding backend endpoints
To create a new endpoint first we should locate the correct Adapter. Adapters are of new adoption so only available for new endpoints.

* Add the new endpoint to the SecuredServicesPort Port.
* Create the stub on the Port implementation.

* Create the neocom node clases that are going to be returned by this endpoint

The second task is to create the new page on the Angular framework.

## Data Modeling
UI data presentation suually requires variants fro many elements because any element can be a container of other elements and by that property be expandable on the UI. So elements
that can be presented on the UI can be of 2 types. Simple elements that represent an entity that can be simple or complex and complex elements that can be expanded to show other elements thart are dependent of the entity.

Container like entities have a face that is the presentation when the element is no expanded and that is a NeoCom node, and a list of contents that may even not be typified
since the expansion may add all those nodes to the list of contents applying to them the additionality policy.

## Bluprints Classified by Castegory
This resolves to a Category that has an Icon, a Name and a id that contains inside a set of blueprints.
Blueprint is a simple elements that may have references to other entities but not as a expandable dependency.

