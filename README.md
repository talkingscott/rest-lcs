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

The python script requires the `requests` package, which you can install with `pip`.  It will implicitly run the executable `python` from your path if you run it directly.  Explicitly specify the executable if you want a different python, e.g. `/usr/local/bin/python3 test-client.py`.

The script runs a fixed set of tests against http://localhost:8080/lcs.  You must have the app running on the local machine for the tests to work.

### Web Page

Start the app, then point a browser at [http://localhost:8080/](http://localhost:8080/).  Enter the strings you want to calculate the LCS of, separated by commas, then click the *Calculate* button.  The request and response JSON will be displayed.

## Don't Have Java?

There is a python version of the web app in the `python_server` directory.  It requires the `bottle` package, which can be installed with `pip`.  Like the Java version, it listens on port 8080, comes with the test web page, and works with the python test client.  However, there are no unit tests.
