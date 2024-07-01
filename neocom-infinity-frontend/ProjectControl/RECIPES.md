# PROJECT RECIPES

# NPM
## Useful commands
npm outdated  -- get the ,list of outdated packages


## [READ ROUTE PARAMETERS]
* To read the route parameters to extract the identifiers the route should be observed.

this.sub = this.route.params.subscribe(params => {
       this.id = +params['id'];
}

## Color Theming
* Color should be defined at the model level because this is the object accessible to the NodeContainer at render time. Then the NodeContainer creates the panel and inside this the render to the node type. Both know the node element so if the color is there then the NodeContainer can render the right color.

## [GIT]
-- CHECKOUT A FILE FROM A COMMIT
git checkout 315e21e99c0121e5679f58b386799d2d800531a1 NeoCom.Infinity.Frontend/e2e/src/features/NIF02-DashboardHome.feature


## [FRONTEND]
Create acceptance support functions to search for panels and other elements during the testing. Use the promise pattern
or the direct access pattern so all code can use the same set of asserts.

## [ANGULAR SNIPPETS]
[OBSERVABLE]
return  new Observable((observer) => {
            observer.next(this.item.target)
            observer.complete()
    })

[FILTER]
    this._filters.push(new Filter()
      .setName('has contents')
      .setDescription('Filter Especialidades empty of Medico')
      .setFilter((_test: Especialidad): boolean => {
        if (_test.getContentSize() < 1) return true;
        else return false;
      }));

    this.filters.push({
      name: 'appointment < now',
      description: 'Filter appointment in the past',
      filter: (_target: Cita): boolean => {
        return isBefore(_target.getFecha(), new Date());
      }
    });

      let filterResult = filter.filterFunction(_target);

[OPEN EXTERNAL URL]
window.open(externalUrl, '_self');
window.location()

[GIT]
-- Connect the local repository to another cloud repository and then update and connect all branches.
git remote remove origin
git remote add origin <remote_repo_url>
git push --all origin
If you want to set all of your branches to automatically use this remote repo when you use git pull, add --set-upstream to the push:

git push --all --set-upstream origin
