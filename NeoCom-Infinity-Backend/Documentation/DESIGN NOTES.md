# DESIGN NOTES
## Cucumber data table conversion
@DataTableType
public Author authorEntry(Map<String, String> entry) {
return new Author(
entry.get("firstName"),
entry.get("lastName"),
entry.get("famousBook"));
}

## API Contracts
Still not defined any contract. There is few documentation about the 
API so it is difficult to generate a contract.

## Database management
Using the manual change control created for Android databases that were
not supporting LiquiBase.

## Sonar tools
Nos configured

## Heroku Upload
Completed because the heroku repository is only tied to the submodule
backend.App. There should be recipes to do uploads from master
local branches.

## Local Code Coverage
Using JaCoCo configuration the report is better than the one generated
by using the IntelliJ option.

## Configuration for enumerated fields
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "card_status", nullable = false)
    @Type(type = "pgsql_enum")

## Scheduler
On Android it is easy to register the jobs when the credentials
are read but that is not the case on SpringBoot.

On SpringBoot the time base should trigger a Credential scan and
for each valid credential it should check that the corresponding
jobs are registered on the queue.

Optimizations should be accounted when the system is stable on
the ongoing developments.

The Job registration service automatically controls job duplication
with the 'equals()' code method. So two jobs with the same characteristics
will not be scheduled multiple times. This is valid if the Credential
object has not changed. If the Credential is changed (!equals) then
the job can be submitted a second time.

So jobs themselves should be able to identify that the object that 
generated it is no longer available and then remove itself from
the schedule list.

### Scheduler Job Lifecycle
1. When the Credential is created at the repository we should create
a unique hash value that conforms to the fields that make this credential
unique to the effects of the job. For example a change on the refresh
should not affect the job because it is related to the same character
or corporation.

2. The Scheduler creates and register the jobs by first time. Later it will
try to register the jobs again but the equality will not allow to
store another identical job on the set so the job is not
duplicated.

3. When the job is run the hash unique identifier is matched against 
the original objects hash identifier. If the hash matches then the
job runs. If the hash does not match the job is removed from
the queue.

## Schedule job registration
There are two possible solutions to this feature. The registration is done
only when the credential is created. If the system restarts than the
schedule gets empty.

One safe way that will cover all cases (new, delete, change) is to run a 
process to scan the credentials and generate the list of jobs to schedule
every schedule cycle.

This way the credentials are tested and if valid then will generate the 
list of jobs to be launched. If the job is not already registered
then it will. If already on the list the process will do nothing.

## Job running and queing
When a jobit is scheduled for run because it is the right time it can happen 
that a previous version of the same job is still on the queue because other 
jobs before have used the time and it is still waiting. This can happen
with the lengthy asset processing operations.

So the new job should not be reinserted on the queue even it is the time 
until any other of their previous copies has benn completed. This way if
a asset download job it is scheduled but the previous download is not completed
then it will wait another cycle to match their schedule and be scheduled.
