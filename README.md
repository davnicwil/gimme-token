gimme-token
===========

A lightweight access token service, built in Java on top of DropWizard.

build
=====

required: java >= 6, maven 3.2.3 (other versions may work)

`cd gimmetoken`
this is the parent project directory

`mvn clean package`
builds all the modules and packages the app into a runnable jar `../gimmetoken-app/target/gimmetoken-app-1.0.jar`

run
===

To run, just run the app jar

```
cd ../gimmetoken-app
java -jar gimmetoken-app-1.0.jar server config.yml
```

The app is a dropwizrd app, and the  `server config.yml` arguments run it as an HTTP server with the configurations defined in config.yml.

The committed config.yml contains some sensible defaults. To change these just amend the config.yml file and re-run the app with the command above (no need to rebuild).

```
http:
  port: 9090            // the port the server will listen on, amend as necessary
  adminPort: 9091       // the port that built-in dropwizard app admin api will listen on, not related to the custom service functionality, see dropwizard docs for the functionality available

tokenAdminEnabled: true                   // enable/disable the app's token admin resource (not related to the built in dropwizrd admin resource - this is a custom api for viewing and purging the access tokens)
tokenLength: 64                           // the generated access token length (in characters)
tokenValueExpiryDurationMillis: 30000     // the expiry length of the generated access tokens (in milliseconds)
```

use
===

The api

```
/token
POST
payload: a single string, the key to associate with the token
example payload "dave"
response: if payload is formatted correctly, 200 with JSON containing the key and generated token value 
example JSON response: {
    "key": "dave",
    "value": "HasdasdCPHll7nWyiPTLVRN6sRQPVa4pHXYGRtfjVKIxaLJ2bWpUyufApKxl6mUTHSNA8V"
}

/token/authorise
POST
payload: the key and token value returned from the /token endpoint
example payload: {
    "key": "dave",
    "value": "HasdasdCPHll7nWyiPTLVRN6sRQPVa4pHXYGRtfjVKIxaLJ2bWpUyufApKxl6mUTHSNA8V"
}
response: 200 if payload contains the correct access token value for the supplied key, and the access token is non expired
          401 if payload conatins the incorrect access token for the supplied key, if the supplied key is not recognised, or if the token value is correct but expired
```

The admin api

```
/admin/active
GET
response: 200 JSON array of keys with currently active (non expired) tokens
example response: ["dave", "laura", "tom"] (where tokens for these users were generated less than configured tokenValueExpiryDurationMillis ago)

/admin/active
GET
response: 200 JSON array of keys with expired tokens
example response: ["kate", "mike"] (where tokens for these users were generated more than configured tokenValueExpiryDurationMillis ago)

/admin/purge-expired
GET
response: 204
purpose: removes all expired key,value pairs from memory for expired tokens. 
By providing this endpoint the responsibility for deciding when/how regularly to purge is left to the service consumer,
to keep gimme-token as lightweight as possible and avoid building in purging policies and implementations.
For example it may be, depending on the consumer app, that memory never needs to be freed up in practice 
because of a maximum number of users (keys),
or it could be that it needs to be freed regularly because there are a massive number of users, 
but in practice they won't all be in use concurrently and memory is highly constrained. 
Gimmetoken avoids building speculative generic solutions for such situations to reduce complexity.
```
