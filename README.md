# Payments API

The API was built using java 8, spring boot and its components. It follows REST API architecture style.

## Run the project

Pre-requisites:
```
Docker >= 18.06
```
Run the bellow code in root project folder to simply build an image:
```
DOCKER_BUILDKIT=1 docker build -t matheus/payments .
```
and then run it like this:
```
docker run -d -p 8080:8080 matheus/payments
```

## The Checkout

Import the file ```payments requests.postman_collection.json``` that is in root project folder into postman application.

There are three request resposible for create a credit card payment, a boleto payment and get all payments registered.

## Unit Tests

To run all unit tests project access the root project folder and execute:
```
mvn test
```