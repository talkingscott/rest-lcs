# rest-ics

A simple web application (REST service) that allows a user to request the longest common substring of a given list of strings.

## Build and Run

You must have Java 8 and Maven installed.  To "just run", run

```
./mvnw spring-boot:run
```

To build and run, run

```
./mvnw clean package
java -jar target/rest-lcs-0.0.1-SNAPSHOT.jar
```

Because tests are run, and this is a Spring Boot app, there is a lot of output along the way.

## Using

The app will be listening on port 8080.  It expects POST requests to /lcs.  The POST body must be JSON of the form shown in this example.

```
{
    "setOfStrings": [
        {"value": "comcast"},
        {"value": "communicate"},
        {"value": "broadcast"}
    ]
}
```

[Postman](https://www.postman.com/) is a useful tool for creating POST requests.
