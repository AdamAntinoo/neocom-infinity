# PLANETARY INTERACTION
## Advanced Planetary Search
When dealing with the PI logistics and management we cannot only count with the
data for a single Pilot. There are also the data for the extractions that can be
done on other planets that belong to the other Pilots of the same Eve Online
account.

This completely break the authentication mechanics and the esi data boundaries. A single
eve login allows only to access the data for a single character and only the
game elements visible to that account.

Se before we can get the list of Planetary Resource production on the complete
planet set for a paying account we have to solve the problem to share the planetary
data between a set of isolated Pilot connections.

At the server side we store the Credential that should be used to access
the server side esi data available to an authenticated pilot esi connection.
This credential identifier is automatically accessible from the frontend calls because
the JWT token, but this only allows the api to access data for the session authenticated
pilot. If we try to read any other resource we cannot give the right Credential
since that data is not available on the client side.

So the only solution is that the backend api will be able to deal directly with this
Account concept and that some set of Planetary backend endpoint allow to send a
unique AccountSet identifier that will extent the data access to the eso servers not only
to the current authenticated Pilot but to the list of Pilots bounded to this
AccountSet instance (this allows it to be 3 or more).

The AccountSet will know how to get the Credential from the server repository
to get planetary information for all the related Pilots and unify the results will
all the records retrieved for each one of the AccountSet components.

## Planetary Interaction Extended Authentication
So Planetary Interaction will not receive the pilot identifier related to the endpoint query
access but a completely new concept that is the AccountSet. This account set is defined
on the frontend authentication data received upon login.

When the Pilot is not associated to any AccountSet then this identifier is not defined
or contains the same identifier as the pilot id.

When the endpoint receives that new identifier then it checks if the data matches
one of the persisted AccountSet available. If none matches then the results are the 
data available to the logged Pilot.

If there are an AccountSet for the identifier received then all the Credentials found
are used to retrieve the Planetary Interaction data from the esi server and the data is processed
back to the client frontend.
