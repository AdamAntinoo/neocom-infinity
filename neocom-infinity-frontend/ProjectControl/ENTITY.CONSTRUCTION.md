# ENTITY CONSTRUCTION
Entities are business objects that are constructed from downloaded adapter data. Many of this objects have complex structures and dependencies to other additional entities making the download and composition proess a long process in some cases.

Supose we start with a clasic data element that is used every where and that has this hierarchical structure.

## EveType Case
EVE has a database of types. Each tipe is a game element or object that is attached to an instance that is called a *Stack*. A stack is a coolection of items of the same type and usually stored at a location (another hevy hierarchical element).

Eve defines a type with very few attributes:
* type_id - the unique type identifier.
* group_id - the type broup to what this type belongs. This is the first hierarchy level.
* market_group_id - the group on the market boards.
* name - the type name.
* ...

The Group we found being linked with the *group_id* has another set of attributes:
* group_id - the unique group identifier.
* category_id - another dependency this time to the category of the item. Second hierarchy level.
* name - the group name.
* ...

The Category also has additional attributes:
* category_id - the unique identifier.
* name - the category name.

So getting information about a single EveType requires 3 calls to the ESI Data Service, one for the Type, another for the Group and a third for the category.

Is we have hundreds of types when we get a list of blueprints this can become a 
heavy load to the ESI Data Service and most of them will result on the same contents. So to reduce this hierarchical explosion of ESI calls we should do at least two actions:
* The first is to cache locally the finite sets of hierarchy leaves. For example the list of Categories and the list of Groups.
* Second leave the resolution of the dependencies to the moment they are used, being replaced by a dummy and identifieble content while the system is downloading the real data in case it is not found at the cache.

For this we need to create an special field called HALLink (HiperAccessLink Link) that will be able to return the empty value while the real values is fetched and that will be able to locate the source for the real data that is contianing.

## HALLinks
A HAL Link should have an URL to where to get the resource and a reference for the Typescript type for that reference to to able to create a typescript instance of that type who has all the expected behavior.

There are two solutions for the moment when the HALLink is resolved. One is during the construction and transformation of the instance. This doen not resolve a problem when most of the new hierarchical data is not being used by the interface. With this solution we ara accessing data that is never used on the UI so it is not an efficient solution.

The second solution is to access that data only when required to be shown. With use of promises this can be left to the Angular presentation layes in a way that when the data is fetched it will be replaced on the navigator dom with no developed code.

## HALLinks and Promises
Using the scond solution means that the content of the HAL is not used inside the code but at the Angular presentation domain. We can replace hirarchical attributes by their corresponding Promise and leave Angular to resolve them when needed to be displayed on the dom.

If we continue to follow the example with the EsiType then we can define the class like:
````
class EsiType {
  public name : string
  public groupId : number
  private group : HALLink<EsiGroup>

  public getGroup() : Promise<EsiGroup> {
    return this.group.linkPromise()
  }
  public getGroupStatus() : boolean {
    return this.grpup.isPending()
  }
}

class component {
  private node : EsiType

  public isGroupPending() : boolean {
    return this.node.getGroupStatus()
  }
  public getGroup() : Promise<EsiGroup> {
    return this.node.getGroup()
  }
}
````

Then the output of the getGroup() method is a Promise that can be used on the 
presentation layer directly in the form:
````
<span *ngIf="isGroupPending(); else new">
  {{getGroup()}}
</span>
````

But unfortunately this is true if the output from the Promise is a sinple element that can be used directly on the UI. If the target is a class instance that has a set of fields then this is not possible and there is no way to access the link internal fields that are hiddden inside a Promise.

So promises should be resolved at the point when they are to be used on the UI. This delays the resolution of the hierarchy dependencies to the moment they are used but the current way to do that lazy evaluation is still not implemented.

On version 20 we are resolving the links when created.
