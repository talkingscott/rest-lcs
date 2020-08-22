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

## Testing

There are three forms of testing provided.

* Unit tests within the code
* A python script in the root of the repo `test-client.py`
* A simple web page accessed at http://localhost:8080/

[Postman](https://www.postman.com/) is a useful tool for creating POST requests if the three provided methods don't cover your needs.

### Python Script

The python script requires the `requests` package, which you can install with pip.  It will implicitly run the executable `python3` from your path if you run it directly.  Explicitly specify the executable if you want a different python.

The script runs a fixed set of tests against http://localhost:8080/lcs.  You must have the app running on the local machine for the tests to work.

### Web Page

Start the app, then point a browser at [http://localhost:8080/](http://localhost:8080/).  Enter the strings you want to calculate the LCS of, separated by commas, then click the *Calculate* button.  The request and response JSON will be displayed.
