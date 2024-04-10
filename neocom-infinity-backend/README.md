# NeoCom.Infinity.Backend
## Steps to initialize the Credential for a call test
* When the 'validateAuthorizationToken' is called it requires a 'code' value that 
can be get a fresh one from the server Frontend test.

## New service to get the NeoItem updated data
The new service should return the full data for the NeoItem. It should use a cache for that 
information because once the market data is added to the item them it can change
frecuently.
The minimum cache time is 15 minutes for the HTTP responses and 60 minutes for the 
update of the market data.

[ADD A MODULE TO THE SERIALIZER]
	@Bean
	public JodaModule jodaModule() {
		return new JodaModule();
	}

[SERIALIZE A RESPONSE TO BE MATCHED AGAINST A FILE]
ObjectMapper objectMapper = new ObjectMapper();
Car car = new Car("yellow", "renault");
objectMapper.writeValue(new File("target/car.json"), car);

# POSTGRESQL DATABASE LOCALSERVER 5432
--- LOGIN CREATION
CREATE ROLE "NEOCOM" LOGIN ENCRYPTED PASSWORD 'md53488ca3baf270e25ad23c0efab03e45e'
  CREATEDB
   VALID UNTIL 'infinity';
GRANT pg_monitor TO "NEOCOM";
COMMENT ON ROLE "NEOCOM"
  IS 'Production user to access NeoCom.Infinity tables in local database storage.';

--- NEOCOM SCHEMA
CREATE SCHEMA "NEOCOM"
       AUTHORIZATION "NEOCOM";
COMMENT ON SCHEMA "NEOCOM"
  IS 'NeoCom.Infinity schema to allow the advanced storage of ESI Eve data downloaded in background by the back end process.';
GRANT ALL ON SCHEMA "NEOCOM" TO public;
REVOKE ALL ON SCHEMA "NEOCOM" FROM GROUP pg_monitor;


## HEROKU COMMANDS
### DATABASE CREATION
heroku apps:create neocominfinity

https://neocominfinity.herokuapp.com/ | https://git.heroku.com/neocominfinity.git


heroku apps:destroy neocominfinity --confirm neocominfinity

git push heroku master

heroku local web -f Procfile.windows

heroku config:set JAVA_OPTS=-Dserver.port=$PORT

### UPDATE PRODUCTION AND RECOMPILE
git push heroku master

### DUMP SOM LOGS TO A FILE
heroku logs > logs-20180530-1.logs

### ACTIVATE THE WEB SERVER
heroku ps:scale web=1

### STOP THE WEB SERVER
heroku ps:scale web=0

### RESET ALL THE CONTENTS OF THE DATABASE
heroku pg:reset --confirm neocom-backend

### RUN A SHELL
heroku run bash

### OPEN THE POSTGRES SHELL
heroku pg:psql

### CLOSE THE POSTGRES SHELL
\q<enter>

# RSYNC
rsync -rv --perms --delete "/cygdrive/d/Development/NeoComProjects/NeoComInfinity/NeoCom.Infinity 0.14.x/NeoCom.Infinity/pages" "/cygdrive/d/Development/IntelliJProjects/NeoCom WebUI/NeoCom.Infinity POC/NeoCom.Infinity"

while true; do rsync -avrz localdir user@host:path; sleep 5; done

C:\cygwin\bin\bash.exe -l -c "./full-path/to/script.sh"

cygrunsrv -I cron -p /usr/sbin/cron -a -D
cygrunsrv -I cron -p /usr/sbin/cron -a -n

net start cron

SOURCE
D:\Development\NeoComProjects\NeoComInfinity\NeoCom.Infinity 0.14.x\NeoCom.Infinity\src\app\modules\shared
/cygdrive/d/Development/NeoComProjects/NeoComInfinity/NeoCom.Infinity 0.14.x/NeoCom.Infinity/src/app/modules/shared

DESTINATION 1
D:\Development\POCProjects\A5POCWorkBench\src\app\modules\shared
/cygdrive/d/Development/POCProjects/A5POCWorkBench/src/app/modules/shared

SYNCH 01
rsync -rv --perms --delete "/cygdrive/d/Development/NeoComProjects/NeoComInfinity/NeoCom.Infinity 0.14.x/NeoCom.Infinity/src/app/modules/shared" "/cygdrive/d/Development/POCProjects/A5POCWorkBench/src/app/modules"


"C:\Users\Adam\Dropbox\Applications\Unison\unison 2.48.4 text.exe" "D:\Development\POCProjects\A6POCNeoCom\src\app\modules\shared" "D:\Development\NeoComProjects\NeoComInfinity\NeoCom.Infinity 0.14.x\NeoCom.Infinity\src\app\modules\shared"


