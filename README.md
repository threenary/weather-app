# weather-app

This simple weather app consists of two basic components, on one hand the backend which is a simple Jersey REST API that provides weather data pulled from an open online weather service, and the upcomming client which will consume the created API to show data in a browser.

## Instructions

1. Clone the project
2. Update maven dependencies
```
mvn clean compile
```
3. Run the app
```
mvn exec:java
```
4. Ping some of the endpoints to grab weather data
```
http://localhost:8080/artificial/{city}
```
5. Within the client folder there is a simple index.html that will allow you test the rest calls
