# PROJECT STRUCTURE
The general project srtructure should follow the Angular and the other tools (Cucumber, Cypress) guidelines. This means that the structure proposed should fit into an already structure.

The new design will use clean architecture guidelines. This starts by defining three main areas where to compose the code:
* **app** - The use cases and UX definitions. Most of the code goes into this area where all the UX components should be declared. Core logic and transformations alos go on this area. Communications to any non business element should be done though ports that are declared on this area but implemented on the *infrastructure* area.
* **infrastructure** - The place for inbound and outbound adapters. All elements related to the implmentation of the project should go at this place like services and configuration.
* **domain** - The place for the business logic data elements to be used inside the application. Most adapters should be able to generate this domain objects from their internal data representations specific for the external system being used.
## 1 Cypress
Cypress goes into is own directory at the top level. The **support/step_definitions** directory is mandatory to store the steps until the configuration allows to use other directories for the code.

## 2 Application
The application is composed of pages, panels and business logic.

Business locig usually is related to navigation and UX interactions, but will also include transformations and theis data management like the lazy evaluation of objects by access request.

Modules should not be defined inside the **modules** directory that is now deprecated. Most or all pages and panels should be declared into a hierarchical set of modules. The key modules that are globally accessed are:
* **renders** - Contains the panels that render a domain class. See theis section documentation about flavours and versioning.
* **shared** - Mostly panels that are used outside their module and thus are commonplace.

Thre is no provision for core code that is used by other elements and that inherits functionality. Herence is deprecated and should be aboided if possible to reduce component complexity and hidden dependencies. Delegation is better suited if there are no injectable dependencies.

## 3 Domain
Domain classes should be classified for easy management. This kind of classes should be versioned to simplify project evolution. There are two main types of domain classes; DTO related to the transfer of data from the adapters that is now depecated since this should be managed internally by the adapters, and domain that really represent the business data structure. The name nomenclature proposed is:
````
V1.Name.domain.ts
````
### 3.1 HyperLinking
One of the key desing elements on this application is the heavy dependency on backend information that can be delayed for lazy evaluation based on the asynchronous way of working of Angular.

Many backend results point to EveItems or Locations that may be cached on the server and expanded on result but that will increase the size of the responses. The other way is to replace the content y a hyper link and let the front end resolve that link when accessing the contents.

This way the server will not expend time fetching all Eve definitions when most of that data is not going to be accessed or its presentation is subject to user interactions. By replacing the access by a link to the access we can delay that access to be done directly by the frontend when the user needs to interact with the information.

This is solved because the server should return the Esi link to retrieve the real data. That data can be managed on the application as a Promise because Angular can deal with promises at the UX level.
